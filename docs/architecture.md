## High-Level Architecture

```mermaid
flowchart LR

A[Angular Dashboard] --> B[API Gateway]

B --> C[Customer Service]
B --> D[Risk Scoring Service]
B --> E[Alert Service]

F[Event Producer Service] --> G[(Kafka)]

G --> D

D --> H[(PostgreSQL)]
D --> I[(Redis Cache)]

E --> H

C --> H
