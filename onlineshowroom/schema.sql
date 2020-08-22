CREATE DATABASE showroom;

USE showroom;

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(100) NOT NULL,
  category_description TEXT NOT NULL
);

DROP TABLE IF EXISTS suppliers;

CREATE TABLE suppliers (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  supplier_name VARCHAR(100) NOT NULL,
  phone VARCHAR(100) NOT NULL,
  address TEXT NOT NULL
);

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  user_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  firstname VARCHAR(100) NOT NULL,
  lastname VARCHAR(100) NOT NULL,
  username VARCHAR(40) NOT NULL,
  email VARCHAR(40) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  phone VARCHAR(100) NOT NULL,
  `active` BIT(1) DEFAULT NULL
);

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  role_id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `role` VARCHAR(255) DEFAULT NULL
);

DROP TABLE IF EXISTS user_role;

CREATE TABLE user_role (
  `user_id` INT(11) NOT NULL,
  `role_id` INT(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  UNIQUE KEY `UK_it77eq964jhfqtu54081ebtio` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
);

DROP TABLE IF EXISTS products;

CREATE TABLE products (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `description` TEXT NOT NULL,
  `categoryId` INT(11) NOT NULL,
  `supplierId` INT(11) NOT NULL,
  FOREIGN KEY (categoryId) REFERENCES categories(id),
  FOREIGN KEY (supplierId) REFERENCES suppliers(id)
);

DROP TABLE IF EXISTS images;

CREATE TABLE images (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  file_name VARCHAR(128) DEFAULT NULL,
  file_data LONGBLOB,
  upload_date DATE,
  productId INT(11) NOT NULL,
  FOREIGN KEY (productId) REFERENCES products(id)
);



