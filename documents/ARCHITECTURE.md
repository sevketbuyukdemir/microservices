[← Return to home](../README.md)

# Architecture Patterns

Architecture patterns in this project focus on loose coupling, asynchronous communication, and scalability by relying on
event streaming and message queues instead of synchronous service-to-service calls.

- Client-Server Architecture Pattern
- Microservices Architecture Pattern
- Event-Driven Architecture Pattern
- Asynchronous Messaging Pattern (Kafka for Events, RabbitMQ for commands/tasks)
- Publish/Subscribe Pattern
- Eventual Consistency Pattern (Wait for success event, do not estimate.)
- Config Server Pattern
- Centralized Edge Gateway Pattern

# Design Patterns

Design patterns are applied to ensure data consistency, failure handling, and clear separation of responsibilities in a
distributed, event-driven microservices' environment.

- SAGA Pattern (Choreography-based)
- Compensating Transaction Pattern (Failure flows/events)
- Command–Event Separation (CQRS-style)
- Caching
    - Write Through (Create / Update / Delete)
    - Cache Aside (Read)
- Transactional Outbox Pattern
    - There is no [Debezium integration](https://github.com/sevketbuyukdemir/mysql-debezium-integration) yet, but you
      can find order_events table from Order Service database.

# Security

Security is enforced using OAuth2 Authorization Code Flow with PKCE.

- Authorization Code and PKCE (OAuth2 via KeyCloak)

> [!NOTE]
> Event-Driven Architecture (EDA) is a software design paradigm where system components communicate by producing and
> responding to events.

> [!NOTE]
> There is no HTTP communication between services in this demo project. If you add, then use Two-Tier Gateway Pattern. (
> Implement gateway for backend services.)
