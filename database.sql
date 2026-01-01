# Config server database
CREATE DATABASE IF NOT EXISTS configserver;
USE configserver;
CREATE USER IF NOT EXISTS 'config_server'@'%' IDENTIFIED BY 'config_server';
GRANT ALL PRIVILEGES ON configserver.* TO 'config_server'@'%';
FLUSH PRIVILEGES;
CREATE TABLE IF NOT EXISTS PROPERTIES (
    APPLICATION VARCHAR(100) NOT NULL,
    PROFILE VARCHAR(100) NOT NULL,
    LABEL VARCHAR(100) DEFAULT 'main',
    propertyKEY VARCHAR(200) NOT NULL,
    propertyVALUE TEXT,
    PRIMARY KEY (APPLICATION, PROFILE, LABEL, propertyKEY)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# product-service database
CREATE DATABASE IF NOT EXISTS productservice;
USE productservice;
CREATE USER IF NOT EXISTS 'product_service'@'%' IDENTIFIED BY 'product_service';
GRANT ALL PRIVILEGES ON productservice.* TO 'product_service'@'%';
FLUSH PRIVILEGES;
CREATE TABLE IF NOT EXISTS products (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `name` varchar(255) NOT NULL,
    `description`   TEXT,
    `price`   DECIMAL(10,2) NOT NULL,
    `currency`  CHAR(3) NOT NULL DEFAULT 'TRY',
    `active`    BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY (`id`),
    UNIQUE KEY `unique_product` (`name`),
    INDEX idx_products_active (active)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS product_categories (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS product_category_map (
    `product_id` bigint NOT NULL,
    `category_id` bigint NOT NULL,
    PRIMARY KEY (`product_id`,`category_id`),
    CONSTRAINT `product_id_category_foreign_key` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
    CONSTRAINT `category_id_foreign_key` FOREIGN KEY (`category_id`) REFERENCES `product_categories` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS product_attributes (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `attrType` VARCHAR(255) NOT NULL,
    `attrValue` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE TABLE IF NOT EXISTS product_attribute_map (
    `product_id` bigint NOT NULL,
    `attribute_id` bigint NOT NULL,
    PRIMARY KEY (`product_id`,`attribute_id`),
    CONSTRAINT `product_id_attribute_foreign_key` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
    CONSTRAINT `attribute_id_foreign_key` FOREIGN KEY (`attribute_id`) REFERENCES `product_attributes` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

# inventory-service database
CREATE DATABASE IF NOT EXISTS inventoryservice;
USE inventoryservice;
CREATE USER IF NOT EXISTS 'inventory_service'@'%' IDENTIFIED BY 'inventory_service';
GRANT ALL PRIVILEGES ON inventoryservice.* TO 'inventory_service'@'%';
FLUSH PRIVILEGES;
CREATE TABLE IF NOT EXISTS inventory (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_id` BIGINT NOT NULL,
    `available_stock` INT NOT NULL DEFAULT 0,
    `reserved_stock` INT NOT NULL DEFAULT 0,
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uq_product_inventory (product_id)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;
CREATE TABLE IF NOT EXISTS reservation (
    id BIGINT NOT NULL AUTO_INCREMENT,
    order_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uq_order_product (order_id, product_id)
);