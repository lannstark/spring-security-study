package com.lannstark.springsecuritystudy.config;

import com.lannstark.springsecuritystudy.service.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

/**
 * HandlerMethodArgumentResolver 는 조건에 맞는 경우 메소드가 있다면
 * HandlerMethodArgumentResolver 의 구현체가 지정한 값으로 해당 메소드의 파라미터로 넘길 수 있다.
 *
 * LoginUserArgumentResolver 를 스프링에서 인식할 수 있도록 WebMvcConfigurer 에 추가
 */
@RequiredArgsConstructor
@Component
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

	private final HttpSession httpSession;

	// 컨트롤러 메소드의 특정 파라미터를 지원하는지 판단
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isLoginUserAnnotation = parameter.getParameterAnnotation(LoginUser.class) != null;
		boolean isUserClass = SessionUser.class.equals(parameter.getParameterType());
		return isLoginUserAnnotation && isUserClass;
	}

	// 파라미터에 전달할 객체를 생성
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
								  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		// TODO: 2019-12-10 이렇게 저장하면 유저 한명에 대한 세션만 유지하는게 아닌가? 아 DB 쓰면 달라지려나?
		return httpSession.getAttribute("user");
	}

}
