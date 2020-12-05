

INSERT INTO `categories` (`id`, `type`, `description`) VALUES
(1, 'Shampoo', 'Hair Product'),
(2, 'Gel', 'Hair styling'),
(3, 'Cosmetics', 'Grooming products'),
(4, 'Color', 'Hair colour and dyes');

INSERT INTO `suppliers` (`id`, `name`, `phone`, `address`) VALUES
(1, 'Head & Shoulder', '00000000', 'USA'),
(2, 'Loreal', '1111111', 'France'),
(3, 'Garnier', '22222222', 'France'),
(4, 'Set Wet', '444444', 'India'),
(5, 'Layer', '555555', 'India'),
(6, 'Brylcreem', '777777', 'UK'),
(7, 'Gatsby', '8888888', 'Canada');


INSERT INTO `employees` (`id`, `firstname`, `lastname`, `username`, `email`, `password`, `phone`, `address`, `type`) VALUES
(1, 'john', 'cena', 'admin', 'admin@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', '0099887766', 'New York, USA', 'admin'),
(2, 'Martha', 'Jones', 'user', 'user@gmail.com', '7c4a8d09ca3762af61e59520943dc26494f8941b', '123456789', 'Seattle', 'employee');


INSERT INTO `products` (`id`, `categoryId`, `supplierId`, `name`, `price`, `quantity`, `description`) VALUES
(1, 1, 1, 'Clinical Solutions', 165, 47, 'Anti-dandruff shampoo'),
(2, 1, 1, 'Classic Clean', 120, 144, 'General shampoo'),
(3, 4, 2, 'HiColor', 190, 33, 'Red Color 50g box'),
(4, 2, 2, 'Wax', 65, 38, 'Hair wax'),
(5, 3, 3, 'Power Light', 70, 93, 'Freshness Cream'),
(6, 3, 3, 'Oil Clear', 160, 294, 'Face Wash'),
(7, 3, 6, 'Brylcreem (Red)', 300, 128, 'Light glossy hold'),
(8, 3, 1, 'Brylcreem (Green)', 105, 0, 'Anti-dandruff');



