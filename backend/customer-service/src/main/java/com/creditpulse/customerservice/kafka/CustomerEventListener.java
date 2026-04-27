package com.creditpulse.customerservice.kafka;

import com.creditpulse.customerservice.event.CustomerEvent;
import com.creditpulse.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerEventListener {

    private final CustomerService customerService;

    @KafkaListener(topics = "customer-events", groupId = "creditpulse-group")
    public void consume(CustomerEvent event) {
        log.info("Received event: {}", event);
        customerService.processCustomerEvent(event);
    }
}