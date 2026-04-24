# creditpulse-ai
I built CreditPulse AI, a real-time credit risk analytics platform using Angular, Java Spring Boot microservices, Kafka, Redis, and cloud-ready architecture to process customer account events and monitor portfolio risk.


# CreditPulse AI

CreditPulse AI is a real-time credit risk analytics platform built using Angular, Java Spring Boot microservices, Kafka, Redis, PostgreSQL, Docker, and cloud-ready architecture.

The platform simulates customer account events, processes them through Kafka, calculates credit risk scores, stores risk history, caches high-risk profiles, and displays portfolio risk insights through an Angular dashboard.

## Tech Stack

- Frontend: Angular, TypeScript, Angular Material, RxJS
- Backend: Java 17, Spring Boot, Spring Security
- Architecture: Microservices, Event-Driven Architecture
- Messaging: Apache Kafka
- Database: PostgreSQL
- Cache: Redis
- DevOps: Docker, Docker Compose, GitHub Actions
- Cloud Ready: AWS compatible architecture

## Core Features

- Customer risk profile management
- Real-time account event processing
- Kafka-based event streaming
- Risk score calculation
- High-risk customer alerts
- Redis caching for high-risk profiles
- Angular dashboard for portfolio monitoring

## Project Structure

```text
creditpulse-ai
 ├── backend
 │   ├── customer-service
 │   ├── event-producer-service
 │   ├── risk-scoring-service
 │   ├── alert-service
 │   └── api-gateway
 ├── frontend
 │   └── creditpulse-angular
 ├── docker
 │   └── docker-compose.yml
 ├── docs
 │   ├── architecture.md
 │   └── api-contracts.md
 └── README.md
