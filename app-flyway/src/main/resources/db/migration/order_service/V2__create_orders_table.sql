USE `order_service`;

CREATE TABLE `orders` (
    `id` BIGINT AUTO_INCREMENT NOT NULL,
    `customer_id` BIGINT NOT NULL,
    `state` VARCHAR(100) COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `rejection_reason` VARCHAR(100) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `total_price` DECIMAL(19, 2),
    `optlock` BIGINT DEFAULT 0,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;
