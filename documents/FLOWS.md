[← Return to home](../README.md)

# Flows

## Product Lifecycle

```mermaid
sequenceDiagram
    participant MO as MARKET_OWNER
    participant PS as product-service
    participant K as Kafka
    participant IS as inventory-service

    MO->>PS: Create Product (REST API)
    PS->>K: Produce PRODUCT_CREATED
    K->>IS: Consume PRODUCT_CREATED
    IS-->>IS: INVENTORY_CREATED

    MO->>PS: Update Product (REST API)
    PS->>K: Produce PRODUCT_UPDATED
    K-->>K: No Consumer

    MO->>PS: Delete Product (REST API)
    PS->>K: Produce PRODUCT_DELETED
    K->>IS: Consume PRODUCT_DELETED
    IS-->>IS: INVENTORY_DEACTIVATED
```

## Inventory & Stock Adjustments

```mermaid
sequenceDiagram
    participant MO as MARKET_OWNER
    participant IS as inventory-service
    participant K as Kafka

    MO->>IS: Adjust Inventory (REST API)
    IS->>K: Produce INVENTORY_ADJUSTED
    K-->>K: No Consumer

    MO->>IS: Adjust Stock (REST API)
    IS->>K: Produce STOCK_ADJUSTED
    K-->>K: No Consumer
```

## Order Creation – Inventory Reservation Failure

```mermaid
sequenceDiagram
    participant C as CONSUMER
    participant OS as order-service
    participant K as Kafka
    participant IS as inventory-service

    C->>OS: Create Order (REST API)
    OS->>K: Produce ORDER_INVENTORY_PENDING
    K->>IS: Consume ORDER_INVENTORY_PENDING
    IS->>K: Produce STOCK_RESERVATION_FAILED
    K->>OS: Consume STOCK_RESERVATION_FAILED
    OS-->>OS: ORDER_INVENTORY_REJECTED
```

## Order Creation – Inventory Reservation Success

```mermaid
sequenceDiagram
    participant C as CONSUMER
    participant OS as order-service
    participant K as Kafka
    participant IS as inventory-service
    participant MQ as RabbitMQ

    C->>OS: Create Order (REST API)
    OS->>K: Produce ORDER_INVENTORY_PENDING
    K->>IS: Consume ORDER_INVENTORY_PENDING
    IS->>K: Produce STOCK_RESERVED
    K->>OS: Consume STOCK_RESERVED
    OS->>MQ: Send Payment Message
```

## Payment Failure Flow

```mermaid
sequenceDiagram
    participant MQ as RabbitMQ
    participant PS as payment-service
    participant K as Kafka
    participant OS as order-service
    participant IS as inventory-service

    MQ->>PS: Process Payment
    PS->>K: Produce PAYMENT_FAILURE
    K->>OS: Consume PAYMENT_FAILURE
    OS-->>OS: ORDER_PAYMENT_REJECTED
    OS->>K: Produce STOCK_RELEASED
    K->>IS: Consume STOCK_RELEASED
```

## Payment Success & Order Completion

```mermaid
sequenceDiagram
    participant MQ as RabbitMQ
    participant PS as payment-service
    participant K as Kafka
    participant OS as order-service
    participant IS as inventory-service
    participant NS as notification-service

    MQ->>PS: Process Payment
    PS->>K: Produce PAYMENT_SUCCESS
    K->>OS: Consume PAYMENT_SUCCESS
    OS-->>OS: ORDER_COMPLETED
    OS->>K: Produce STOCK_DECREASED
    K->>IS: Consume STOCK_DECREASED
    IS->>K: Produce EMAIL_NOTIFICATION
    K->>NS: Consume EMAIL_NOTIFICATION
    NS-->>NS: EMAIL_SENT
```
