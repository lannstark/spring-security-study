package com.lannstark.springsecuritystudy.service;

import com.lannstark.springsecuritystudy.domain.Account;
import com.lannstark.springsecuritystudy.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Data Access Object 인터페이를 통해서 데이터를 읽어와
 * 유저 정보를 가지고 인증을 할 때 사용하는 인터페이스 : UserDetailService
 *
 * 모든 DB가 특정 DB로 귀결되지는 않음
 */
@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Account account = accountRepository.findByName(name);
		return User.builder()
				.username(account.getName())
				.password("{noop} 1234")
				.roles("USER")
				.build();
	}

}
