package com.creditpulse.customerservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CustomerRequest {

    @NotBlank(message = "Customer ID is required")
    private String customerId;

    @NotBlank(message = "Full name is required")
    private String fullName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Account status is required")
    private String accountStatus;

    @Min(value = 300, message = "Credit score must be at least 300")
    @Max(value = 850, message = "Credit score cannot exceed 850")
    private Integer creditScore;

    @PositiveOrZero(message = "Current balance cannot be negative")
    private Double currentBalance;

    @DecimalMin(value = "0.0", message = "Utilization cannot be negative")
    @DecimalMax(value = "100.0", message = "Utilization cannot exceed 100")
    private Double utilizationPercentage;
}