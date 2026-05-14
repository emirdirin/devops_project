package com.sau.devops_project2.repository;

import com.sau.devops_project2.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long>{
}