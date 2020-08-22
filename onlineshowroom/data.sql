INSERT  INTO `roles`(`role_id`,`role`) VALUES 
(1,'ADMIN'),
(2,'USER');

INSERT INTO `users` (`user_id`, `firstname`, `lastname`, `username`, `email`, `password`, `phone`, `active`) VALUES
(1, 'john', 'cena', 'admin', 'admin@gmail.com', '$2a$10$RZEFzUPZfZ6QSPZUsYZ8RuiNDG5Y8YYhOm4kp.R0aMoz0W/fb3Rym', '0099887766', ''),
(2, 'Martha', 'Jones', 'user', 'user@gmail.com', '$2a$10$RZEFzUPZfZ6QSPZUsYZ8RuiNDG5Y8YYhOm4kp.R0aMoz0W/fb3Rym', '123456789', '');

INSERT  INTO `user_role`(`user_id`,`role_id`) VALUES 
(1,1),
(2,2);

INSERT INTO `categories` (`id`, `category_name`, `category_description`) VALUES
(1, 'TV', 'TV Product'),
(2, 'Computer', 'Computer Product'),
(3, 'Mobile', 'Mobile Product'),
(4, 'Fridge', 'Fridge Product');

INSERT INTO `suppliers` (`id`, `supplier_name`, `phone`, `address`) VALUES
(1, 'Walton', '00000000', 'USA'),
(2, 'Samsung', '1111111', 'France'),
(3, 'Minister', '22222222', 'France'),
(4, 'LG', '444444', 'India');

INSERT INTO `products` (`id`, `categoryId`, `supplierId`, `name`, `price`, `quantity`, `description`) VALUES
(1, 1, 1, 'LED TV', 165, 47, 'Electronics'),
(2, 1, 1, 'Wide TV', 120, 144, 'Electronics'),
(3, 4, 2, 'BIg Fridge', 190, 33, 'Electronics'),
(4, 2, 2, 'Lenovo', 65, 38, 'Electronics'),
(5, 3, 3, 'Xiomi', 70, 93, 'Electronics'),
(6, 3, 3, 'Samsung', 160, 294, 'Electronics'),
(7, 3, 4, 'Smartphone', 300, 128, 'Electronics'),
(8, 4, 1, 'Small Fridge', 105, 0, 'Electronics');

