[← Return to home](../README.md)

# Technical Flows

## Product Lifecycle

```mermaid
sequenceDiagram
    participant MO as MarketOwner
    participant PS as ProductService
    participant K as Kafka
    participant IS as InventoryService

    MO->>PS: Create Product (REST API)
    PS->>K: Produce PRODUCT_CREATED
    K->>IS: Consume PRODUCT_CREATED
    IS-->>IS: Create inventory for product
    IS->>K: Produce INVENTORY_CREATED

    MO->>PS: Update Product (REST API)
    PS->>K: Produce PRODUCT_UPDATED
    K-->>K: No Consumer

    MO->>PS: Delete Product (REST API)
    PS->>K: Produce PRODUCT_DELETED
    K->>IS: Consume PRODUCT_DELETED
    IS-->>IS: Deactivate product inventory
    IS->>K: Produce INVENTORY_DEACTIVATED
```

## Inventory & Stock Adjustments

```mermaid
sequenceDiagram
    participant MO as MarketOwner
    participant IS as InventoryService
    participant K as Kafka

    MO->>IS: Adjust Inventory (REST API)
    IS-->>IS: Update inventory of product
    IS->>K: Produce INVENTORY_ADJUSTED
    K-->>K: No Consumer

    MO->>IS: Adjust Stock (REST API)
    IS-->>IS: Update stock of product
    IS->>K: Produce STOCK_ADJUSTED
    K-->>K: No Consumer
```

## Order Creation – Inventory Reservation Failure

```mermaid
sequenceDiagram
    participant C as CONSUMER
    participant OS as OrderService
    participant K as Kafka
    participant IS as InventoryService

    C->>OS: Create Order (REST API)
    OS->>K: Produce ORDER_INVENTORY_PENDING
    K->>IS: Consume ORDER_INVENTORY_PENDING
    IS-->>IS: Try reserve stock
    IS->>K: Produce STOCK_RESERVATION_FAILED
    K->>OS: Consume STOCK_RESERVATION_FAILED
    OS-->>OS: ORDER_INVENTORY_REJECTED
```

## Order Creation – Inventory Reservation Success

```mermaid
sequenceDiagram
    participant C as CONSUMER
    participant OS as OrderService
    participant K as Kafka
    participant IS as InventoryService
    participant MQ as RabbitMQ

    C->>OS: Create Order (REST API)
    OS->>K: Produce ORDER_INVENTORY_PENDING
    K->>IS: Consume ORDER_INVENTORY_PENDING
    IS-->>IS: Try reserve stock
    IS->>K: Produce STOCK_RESERVED
    K->>OS: Consume STOCK_RESERVED
    OS->>K: Produce ORDER_PAYMENT_PENDING
    OS->>MQ: Send Payment Message
```

## Payment Failure Flow

```mermaid
sequenceDiagram
    participant MQ as RabbitMQ
    participant PS as PaymentService
    participant K as Kafka
    participant OS as OrderService
    participant IS as InventoryService

    MQ->>PS: Process Payment
    PS->>K: Produce PAYMENT_FAILURE
    K->>OS: Consume PAYMENT_FAILURE
    OS->>K: Produce ORDER_PAYMENT_REJECTED
    K->>IS: Consume ORDER_PAYMENT_REJECTED
    IS-->>IS: Release stock
    IS->>K: Produce STOCK_RELEASED
    OS->>MQ: Send Notification message
```

## Payment Success & Order Completion

```mermaid
sequenceDiagram
    participant MQ as RabbitMQ
    participant PS as PaymentService
    participant K as Kafka
    participant OS as OrderService
    participant IS as InventoryService
    participant NS as NotificationService

    MQ->>PS: Process Payment
    PS->>K: Produce PAYMENT_SUCCESS
    K->>OS: Consume PAYMENT_SUCCESS
    OS-->>OS: Complete order
    OS->>K: Produce ORDER_COMPLETED
    K->>IS: Consume ORDER_COMPLETED
    IS-->>IS: Decrease reserved stock
    OS->>MQ: Send Notification message
```

## Notification Flow

```mermaid
sequenceDiagram
    participant MQ as RabbitMQ
    participant ES as EmailService

    MQ->>ES: Process Notification
    ES-->>ES: Send email to customer
```