package com.creditpulse.riskscoringservice.kafka;

import com.creditpulse.riskscoringservice.event.CustomerEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RiskEventListener {

    @KafkaListener(topics = "customer-events", groupId = "risk-scoring-group")
    public void consume(CustomerEvent event) {
        log.info("Risk scoring service received event: {}", event);
    }
}