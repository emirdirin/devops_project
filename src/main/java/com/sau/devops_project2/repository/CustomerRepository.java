package com.sau.devops_project2.repository;

import com.sau.devops_project2.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}