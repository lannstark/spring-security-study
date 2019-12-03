package com.lannstark.springsecuritystudy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

	/**
	 * login
	 * 누구나 들어올 수 있는 페이지
	 */
	@GetMapping("/")
	public String basicPage() {
		return "OK";
	}

	/**
	 * main
	 * 일반 회원만 들어올 수 있는 페이지
	 */
	@GetMapping("/main")
	public String mainPage(Principal principal) {
		return String.format("OK %s", principal.getName());
	}

	/**
	 * special
	 * 특별 회원만 들어올 수 있는 페이지
	 */
	@GetMapping("/special")
	public String specialPage(Principal principal) {
		return String.format("Special OK %s", principal.getName());
	}

}
