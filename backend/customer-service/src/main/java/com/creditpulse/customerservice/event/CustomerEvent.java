package com.creditpulse.customerservice.event;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvent {

    private String eventType; // CREATED / UPDATED
    private String customerId;
    private Integer creditScore;
    private Double currentBalance;
    private Double utilizationPercentage;
}