package com.lannstark.springsecuritystudy.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

	Account findByName(String name);

}
