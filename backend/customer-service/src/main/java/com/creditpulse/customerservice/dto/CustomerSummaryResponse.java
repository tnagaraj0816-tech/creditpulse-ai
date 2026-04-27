package com.creditpulse.customerservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerSummaryResponse {
    private long totalCustomers;
    private long lowRiskCustomers;
    private long mediumRiskCustomers;
    private long highRiskCustomers;
}