package com.creditpulse.riskscoringservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.creditpulse.riskscoringservice.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByCustomerId(String customerId);
}