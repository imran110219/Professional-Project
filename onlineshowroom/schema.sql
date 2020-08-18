CREATE DATABASE showroom;

USE showroom;

DROP TABLE IF EXISTS images;

CREATE TABLE images (
  id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  file_name VARCHAR(128) DEFAULT NULL,
  file_data LONGBLOB,
  upload_date DATE,
  productId INT(11) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS categories;

CREATE TABLE categories (
  id INT(11) NOT NULL,
  category_name VARCHAR(100) NOT NULL,
  category_description TEXT NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS employees;

CREATE TABLE `employees` (
  `id` INT(11) NOT NULL,
  `firstname` VARCHAR(100) NOT NULL,
  `lastname` VARCHAR(100) NOT NULL,
  `username` VARCHAR(40) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(100) NOT NULL,
  `address` TEXT NOT NULL,
  `type` ENUM('admin','employee') NOT NULL DEFAULT 'employee'
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS products;

CREATE TABLE products (
  `id` INT(11) NOT NULL,
  `categoryId` INT(11) NOT NULL,
  `supplierId` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `description` TEXT NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS suppliers;

CREATE TABLE `suppliers` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(100) NOT NULL,
  `address` TEXT NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;


ALTER TABLE `categories`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `products`
  ADD PRIMARY KEY (`id`),
  ADD KEY `categoryId` (`categoryId`),
  ADD KEY `supplierId` (`supplierId`);


ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);


ALTER TABLE `categories`
  MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;


ALTER TABLE `employees`
  MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;


ALTER TABLE `products`
  MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;


ALTER TABLE `suppliers`
  MODIFY `id` INT(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;


ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`categoryId`) REFERENCES `categories` (`id`),
  ADD CONSTRAINT `products_ibfk_2` FOREIGN KEY (`supplierId`) REFERENCES `suppliers` (`id`);
  
ALTER TABLE `images`
  ADD CONSTRAINT `images_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `products` (`id`);
