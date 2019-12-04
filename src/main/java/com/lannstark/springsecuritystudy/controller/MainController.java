package com.lannstark.springsecuritystudy.controller;

import com.lannstark.springsecuritystudy.domain.Account;
import com.lannstark.springsecuritystudy.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MainController {

	private final AccountRepository accountRepository;
	private final PasswordEncoder passwordEncoder;

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

	@PostMapping("/signup")
	public String signUp(@RequestParam String name, @RequestParam String password) {
		Account account = new Account(name, passwordEncoder.encode(password));
		accountRepository.save(account);
		return "OK";
	}

}
