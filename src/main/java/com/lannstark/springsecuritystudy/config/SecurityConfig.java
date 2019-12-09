package com.lannstark.springsecuritystudy.config;

import com.lannstark.springsecuritystudy.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * Spring Security 설정 파일
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// 1. 어떤 요청을 어떻게 허용할 것인가
//		http.authorizeRequests()
//				.mvcMatchers("/main").hasRole("USER")
//				.mvcMatchers("/special").hasRole("SPECIAL")
//				.anyRequest().permitAll();
//
//		// 2. 기본 form login 사용
//		http.formLogin();
//
//		// TODO: 2019-12-03 Basic Authentication 이란?
//		// 3. Basic Authentication(?)을 사용하겠다
//		http.httpBasic();
//
//		// 4. CSRF 면제 처리 POST 도 기본적으로 막혀있다.
//		http.csrf().disable();
//
//		// 5. X-Frame-Options 제외
//		http.headers()
//				.frameOptions().disable();
//	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

		http.headers().frameOptions().disable();

		http.authorizeRequests()
				.anyRequest().permitAll();

		http.logout()
				.logoutSuccessUrl("/");

		http.oauth2Login()
			.userInfoEndpoint()
				.userService(customOAuth2UserService);
	}
}
