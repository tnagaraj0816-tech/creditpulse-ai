package com.creditpulse.customerservice.service;

import com.creditpulse.customerservice.entity.Customer;
import com.creditpulse.customerservice.repository.CustomerRepository;
import com.creditpulse.customerservice.dto.CustomerRequest;
import com.creditpulse.customerservice.dto.CustomerSummaryResponse;
import com.creditpulse.customerservice.event.CustomerEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @CacheEvict(value = "customers", allEntries = true)
    public Customer createCustomer(CustomerRequest request) {
        Customer customer = Customer.builder()
                .customerId(request.getCustomerId())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .accountStatus(request.getAccountStatus())
                .creditScore(request.getCreditScore())
                .currentBalance(request.getCurrentBalance())
                .utilizationPercentage(request.getUtilizationPercentage())
                .riskCategory(calculateRiskCategory(
                        request.getCreditScore(),
                        request.getUtilizationPercentage(),
                        request.getCurrentBalance()
                ))
                .createdAt(LocalDateTime.now())
                .build();
        
        return customerRepository.save(customer);
    }

    @CacheEvict(value = "customers", allEntries = true)
    public void processCustomerEvent(CustomerEvent event) {
        Customer customer = customerRepository.findByCustomerId(event.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found with customerId: " + event.getCustomerId()));

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

        System.out.println("Updated customer risk from Kafka event. customerId: "
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

    public Customer getCustomerById(Long id) {
    return customerRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @CacheEvict(value = "customers", allEntries = true)
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    @Cacheable(value = "customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @CacheEvict(value = "customers", allEntries = true)
    public Customer updateCustomer(Long id, CustomerRequest request) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setCustomerId(request.getCustomerId());
        existingCustomer.setFullName(request.getFullName());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setAccountStatus(request.getAccountStatus());
        existingCustomer.setCreditScore(request.getCreditScore());
        existingCustomer.setCurrentBalance(request.getCurrentBalance());
        existingCustomer.setUtilizationPercentage(request.getUtilizationPercentage());
        existingCustomer.setRiskCategory(calculateRiskCategory(
                request.getCreditScore(),
                request.getUtilizationPercentage(),
                request.getCurrentBalance()
        ));

        return customerRepository.save(existingCustomer);
    }

    public CustomerSummaryResponse getCustomerSummary() {
        return CustomerSummaryResponse.builder()
                .totalCustomers(customerRepository.count())
                .lowRiskCustomers(customerRepository.countByRiskCategory("LOW"))
                .mediumRiskCustomers(customerRepository.countByRiskCategory("MEDIUM"))
                .highRiskCustomers(customerRepository.countByRiskCategory("HIGH"))
                .build();
    }
}