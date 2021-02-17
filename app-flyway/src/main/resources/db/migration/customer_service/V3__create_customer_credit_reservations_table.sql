USE `customer_service`;

CREATE TABLE `customer_credit_reservations` (
  `customer_id` BIGINT NOT NULL,
  `credit_reservations` DECIMAL(19,2),
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB;

ALTER TABLE `customer_credit_reservations`
    ADD CONSTRAINT `customer_credit_reservations_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (id);
