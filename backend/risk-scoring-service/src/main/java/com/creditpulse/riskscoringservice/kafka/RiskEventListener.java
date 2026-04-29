package com.creditpulse.riskscoringservice.kafka;

import com.creditpulse.riskscoringservice.event.CustomerEvent;
import com.creditpulse.riskscoringservice.service.RiskScoringService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiskEventListener {

    private final RiskScoringService riskScoringService;

    @KafkaListener(topics = "customer-events", groupId = "risk-scoring-group")
    public void consume(CustomerEvent event) {
        log.info("Risk scoring service received event: {}", event);
        riskScoringService.processRiskEvent(event);
    }
}