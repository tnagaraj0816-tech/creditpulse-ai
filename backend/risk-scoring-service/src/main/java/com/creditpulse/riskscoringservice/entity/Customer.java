package com.creditpulse.riskscoringservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerId;
    private String fullName;
    private String email;
    private String accountStatus;
    private Integer creditScore;
    private Double currentBalance;
    private Double utilizationPercentage;
    private String riskCategory;
    private LocalDateTime createdAt;
}