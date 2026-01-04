# Microservices

Microservices Architecture: General Concepts and Implementations in the Spring Ecosystem

1. [General Concepts & Technologies](documents/GENERAL_CONCEPTS_AND_TECHNOLOGIES.md)
2. [Local Development](documents/LOCAL_DEVELOPMENT.md)
3. [Services and Their External Ports](documents/SERVICES.md)
4. [Technical Flows](documents/FLOWS.md)

```mermaid
sequenceDiagram
    participant MO as MARKET_OWNER
    participant C as CONSUMER
    participant PS as product-service
    participant OS as order-service
    participant IS as inventory-service
    participant PayS as payment-service
    participant NS as notification-service
    participant K as Kafka
    participant MQ as RabbitMQ

    %% -------------------------------
    %% Product Lifecycle
    %% -------------------------------
    MO->>PS: Create Product (REST API)
    PS->>K: Produce PRODUCT_CREATED
    K->>IS: Consume PRODUCT_CREATED
    IS-->>IS: INVENTORY_CREATED

    MO->>PS: Update Product (REST API)
    PS->>K: Produce PRODUCT_UPDATED

    MO->>PS: Delete Product (REST API)
    PS->>K: Produce PRODUCT_DELETED
    K->>IS: Consume PRODUCT_DELETED
    IS-->>IS: INVENTORY_DEACTIVATED

    %% -------------------------------
    %% Inventory Adjustments
    %% -------------------------------
    MO->>IS: Adjust Inventory (REST API)
    IS->>K: Produce INVENTORY_ADJUSTED

    MO->>IS: Adjust Stock (REST API)
    IS->>K: Produce STOCK_ADJUSTED

    %% -------------------------------
    %% Order Creation
    %% -------------------------------
    C->>OS: Create Order (REST API)
    OS->>K: Produce ORDER_INVENTORY_PENDING
    K->>IS: Consume ORDER_INVENTORY_PENDING

    alt Stock Reservation Failed
        IS->>K: Produce STOCK_RESERVATION_FAILED
        K->>OS: Consume STOCK_RESERVATION_FAILED
        OS-->>OS: ORDER_INVENTORY_REJECTED
    else Stock Reserved
        IS->>K: Produce STOCK_RESERVED
        K->>OS: Consume STOCK_RESERVED

        %% -------------------------------
        %% Payment Processing (RabbitMQ)
        %% -------------------------------
        OS->>MQ: Send Payment Message
        MQ->>PayS: Process Payment

        alt Payment Failed
            PayS->>K: Produce PAYMENT_FAILURE
            K->>OS: Consume PAYMENT_FAILURE
            OS-->>OS: ORDER_PAYMENT_REJECTED
            OS->>K: Produce STOCK_RELEASED
            K->>IS: Consume STOCK_RELEASED
        else Payment Successful
            PayS->>K: Produce PAYMENT_SUCCESS
            K->>OS: Consume PAYMENT_SUCCESS
            OS-->>OS: ORDER_COMPLETED
            OS->>K: Produce STOCK_DECREASED
            K->>IS: Consume STOCK_DECREASED
            IS->>K: Produce EMAIL_NOTIFICATION
            K->>NS: Consume EMAIL_NOTIFICATION
            NS-->>NS: EMAIL_SENT
        end
    end
```





