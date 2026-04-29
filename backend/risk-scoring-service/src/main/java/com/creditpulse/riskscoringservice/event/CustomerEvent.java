package com.creditpulse.riskscoringservice.event;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEvent {
    private String eventType;
    private String customerId;
    private Integer creditScore;
    private Double currentBalance;
    private Double utilizationPercentage;
}