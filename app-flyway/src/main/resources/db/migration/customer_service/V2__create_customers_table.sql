USE `customer_service`;

CREATE TABLE `customers` (
  `id` BIGINT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(255) COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `credit_limit` DECIMAL(19, 2),
  `optlock` BIGINT DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
