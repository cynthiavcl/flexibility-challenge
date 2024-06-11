CREATE DATABASE `payments` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;


CREATE TABLE `province` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `payments`.`province`
(`id`,
`descripcion`)
VALUES
(1,
"Buenos Aires");


CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `surname` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `province_id` int DEFAULT NULL,
  `birth` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idProvince_idx` (`province_id`),
  CONSTRAINT `FK95nyta7b1ns77s1873t6655vo` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`),
  CONSTRAINT `province` FOREIGN KEY (`province_id`) REFERENCES `province` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `transaction` (
  `id` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `approved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idProduct_idx` (`product_id`),
  KEY `idCustomer_idx` (`customer_id`),
  CONSTRAINT `Customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `Product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

