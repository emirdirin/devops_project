package com.sau.devops_project2.repository;

import com.sau.devops_project2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}