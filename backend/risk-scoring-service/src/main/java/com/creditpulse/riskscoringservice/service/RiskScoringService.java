package com.creditpulse.riskscoringservice.service;

import com.creditpulse.riskscoringservice.entity.Customer;
import com.creditpulse.riskscoringservice.event.CustomerEvent;
import com.creditpulse.riskscoringservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RiskScoringService {

    private final CustomerRepository customerRepository;

    public void processRiskEvent(CustomerEvent event) {
        Customer customer = customerRepository.findByCustomerId(event.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found: " + event.getCustomerId()));

        String riskCategory = calculateRiskCategory(
                event.getCreditScore(),
                event.getUtilizationPercentage(),
                event.getCurrentBalance()
        );

        customer.setCreditScore(event.getCreditScore());
        customer.setCurrentBalance(event.getCurrentBalance());
        customer.setUtilizationPercentage(event.getUtilizationPercentage());
        customer.setRiskCategory(riskCategory);

        customerRepository.save(customer);

        System.out.println("Risk updated by risk-scoring-service for customerId: "
                + event.getCustomerId() + ", riskCategory: " + riskCategory);
    }

    private String calculateRiskCategory(Integer creditScore, Double utilizationPercentage, Double currentBalance) {
        if (creditScore == null || utilizationPercentage == null || currentBalance == null) {
            return "UNKNOWN";
        }

        if (creditScore < 600 || utilizationPercentage > 85 || currentBalance > 10000) {
            return "HIGH";
        } else if (creditScore < 700 || utilizationPercentage > 65 || currentBalance > 5000) {
            return "MEDIUM";
        } else {
            return "LOW";
        }
    }
}