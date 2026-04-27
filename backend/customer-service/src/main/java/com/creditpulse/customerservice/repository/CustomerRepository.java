package com.creditpulse.customerservice.repository;

import com.creditpulse.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    long countByRiskCategory(String riskCategory);
}