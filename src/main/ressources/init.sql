-- -----------------------------------------------------
-- Schema 'food_delivery_app'
-- -----------------------------------------------------
CREATE DATABASE IF NOT EXISTS food_delivery_app_db;

USE food_delivery_app_db;

-- -----------------------------------------------------
-- Table users
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS users ( 
user_id BIGINT AUTO_INCREMENT PRIMARY KEY, 
username VARCHAR(255) NOT NULL UNIQUE, 
user_password VARCHAR(255) NOT NULL, 
email VARCHAR(150) UNIQUE, 
role ENUM('CUSTOMER', 'COURIER', 'MANAGER') NOT NULL, 
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);

-- -----------------------------------------------------
-- Table customers
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS customers ( 
  customer_id BIGINT PRIMARY KEY, 
  address VARCHAR(255) NOT NULL, 
  phone_number VARCHAR(20), 
  FOREIGN KEY (customer_id) REFERENCES users(user_id) ON DELETE CASCADE,
  CONSTRAINT uq_customer_user UNIQUE (customer_id)
); 

-- -----------------------------------------------------
-- Table managers
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS managers ( 
  manager_id BIGINT PRIMARY KEY, 
  is_verified BOOLEAN DEFAULT FALSE, 
  FOREIGN KEY (manager_id) REFERENCES users(user_id) ON DELETE CASCADE,
  CONSTRAINT uq_manager_user UNIQUE (manager_id)
);

-- -----------------------------------------------------
-- Table couriers
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS couriers ( 
  courier_id BIGINT PRIMARY KEY, 
  vehicle_type ENUM('BIKE', 'CAR', 'SCOOTER', 'WALK') NOT NULL, 
  license_plate VARCHAR(20), 
  availability BOOLEAN DEFAULT FALSE, 
  FOREIGN KEY (courier_id) REFERENCES users(user_id) ON DELETE CASCADE,
  CONSTRAINT uq_courier_user UNIQUE (courier_id)
);

-- -----------------------------------------------------
-- Table food_establishments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS food_establishments ( 
establishment_id BIGINT AUTO_INCREMENT PRIMARY KEY, 
name VARCHAR(150) NOT NULL, address VARCHAR(255) NOT NULL, 
phone_number VARCHAR(20), 
open_hours VARCHAR(100), 
is_open BOOLEAN DEFAULT FALSE, 
rating DECIMAL(10,2) NOT NULL DEFAULT 0.00,
rating_count INT NOT NULL DEFAULT 0,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
); 

-- -----------------------------------------------------
-- Table restaurants
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS restaurants ( 
restaurant_id BIGINT PRIMARY KEY,
cuisine_type VARCHAR(100), 
FOREIGN KEY (restaurant_id) REFERENCES food_establishments(establishment_id) ON DELETE CASCADE 
); 

-- -----------------------------------------------------
-- Table supermarkets
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS supermarkets ( 
supermarket_id BIGINT PRIMARY KEY, 
bulkDiscounts BOOLEAN DEFAULT FALSE,
FOREIGN KEY (supermarket_id) REFERENCES food_establishments(establishment_id) ON DELETE CASCADE 
);

-- -----------------------------------------------------
-- Table manager_establishments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS manager_establishments (
    manager_id BIGINT NOT NULL,
    establishment_id BIGINT NOT NULL,
    PRIMARY KEY (manager_id, establishment_id),
    
    FOREIGN KEY (manager_id) REFERENCES managers(manager_id) ON DELETE CASCADE,
    FOREIGN KEY (establishment_id) REFERENCES food_establishments(establishment_id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table menu_items
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS menu_items ( 
item_id BIGINT AUTO_INCREMENT PRIMARY KEY, 
name VARCHAR(150) NOT NULL, 
description TEXT, 
price DECIMAL(10,2) NOT NULL, 
is_available BOOLEAN DEFAULT FALSE, 
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);

-- -----------------------------------------------------
-- Table restaurant_menu_items
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS restaurant_menu_items ( 
restaurant_item_id BIGINT PRIMARY KEY NOT NULL, 
restaurant_id BIGINT NOT NULL, 
vegetarian BOOLEAN DEFAULT FALSE, 
FOREIGN KEY (restaurant_item_id) REFERENCES menu_items(item_id) ON DELETE CASCADE, 
FOREIGN KEY (restaurant_id) REFERENCES restaurants(restaurant_id) ON DELETE CASCADE 
); 

-- -----------------------------------------------------
-- Table supermarket_items
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS supermarket_items ( 
supermarket_item_id BIGINT PRIMARY KEY NOT NULL,
supermarket_id BIGINT NOT NULL, 
stock_quantity INT DEFAULT 0, 
FOREIGN KEY (supermarket_item_id) REFERENCES menu_items(item_id) ON DELETE CASCADE, 
FOREIGN KEY (supermarket_id) REFERENCES supermarkets(supermarket_id) ON DELETE CASCADE 
);

-- -----------------------------------------------------
-- Table orders
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS orders (
order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
customer_id BIGINT NOT NULL,
courier_id BIGINT,
establishment_id BIGINT NOT NULL,
order_status ENUM('PROCESSING', 'CANCELLED', 'ACCEPTED', 'PENDING', 'REFUSED') NOT NULL,
delivery_status ENUM('ASSIGNED', 'ON_THE_WAY', 'DELIVERED') NOT NULL,
delivery_address VARCHAR(255) NOT NULL,
total_price DECIMAL(10,2) NOT NULL DEFAULT 0.00,
delivery_time TIMESTAMP,        
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
FOREIGN KEY (courier_id) REFERENCES couriers(courier_id) ON DELETE SET NULL,
FOREIGN KEY (establishment_id) REFERENCES food_establishments(establishment_id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table order_items
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS order_items (
order_item_id BIGINT AUTO_INCREMENT PRIMARY KEY,
order_id BIGINT NOT NULL,
item_id BIGINT NOT NULL,    
quantity INT NOT NULL DEFAULT 1,
price DECIMAL(10,2) NOT NULL,
    
FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
FOREIGN KEY (item_id) REFERENCES menu_items(item_id) ON DELETE CASCADE
);

-- -----------------------------------------------------
-- Table payments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS payments (
  payment_id BIGINT AUTO_INCREMENT PRIMARY KEY,
  order_id BIGINT NOT NULL,
  amount DECIMAL(10,2) NOT NULL,
  payment_method ENUM('CASH', 'CREDIT_CARD', 'DEBIT_CARD', 'DIGITAL_WALLET', 'PAYPAL') NOT NULL,
  payment_status ENUM('PENDING', 'COMPLETED', 'FAILED') NOT NULL DEFAULT 'PENDING',
  payment_provider VARCHAR(100),       -- only for digital
  cash_received BOOLEAN,               -- only for cash
  customer_id BIGINT,                  -- common reference
  courier_id BIGINT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE,
  FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE,
  FOREIGN KEY (courier_id) REFERENCES couriers(courier_id) ON DELETE SET NULL
);

-- -----------------------------------------------------
-- Data insertion for testing
-- -----------------------------------------------------
USE food_delivery_app_db;

-- -----------------------------------------------------
-- Users
-- -----------------------------------------------------
INSERT INTO users (username, user_password, email, role) VALUES
('Alice', 'pass123', 'alice@mail.com', 'CUSTOMER'),
('Bob', 'pass123', 'bob@mail.com', 'CUSTOMER'),
('Carol', 'pass123', 'carol@mail.com', 'CUSTOMER'),
('Dave', 'pass123', 'dave@mail.com', 'CUSTOMER'),
('Eve', 'pass123', 'eve@mail.com', 'CUSTOMER'),
('John', 'pass123', 'john.manager@mail.com', 'MANAGER'),
('Susan', 'pass123', 'susan.manager@mail.com', 'MANAGER'),
('Mike', 'pass123', 'mike.courier@mail.com', 'COURIER'),
('Lisa', 'pass123', 'lisa.courier@mail.com', 'COURIER'),
('Tom', 'pass123', 'tom.courier@mail.com', 'COURIER');

-- -----------------------------------------------------
-- Customers
-- -----------------------------------------------------
INSERT INTO customers (customer_id, address, phone_number) VALUES
(1, '123 Main St', '3001112233'),
(2, '456 Oak St', '3002223344'),
(3, '789 Pine St', '3003334455'),
(4, '321 Maple Ave', '3004445566'),
(5, '654 Birch Rd', '3005556677');

-- -----------------------------------------------------
-- Managers
-- -----------------------------------------------------
INSERT INTO managers (manager_id, is_verified) VALUES
(6, TRUE),
(7, FALSE);

-- -----------------------------------------------------
-- Couriers
-- -----------------------------------------------------
INSERT INTO couriers (courier_id, vehicle_type, license_plate, availability) VALUES
(8, 'BIKE', 'BIKE123', TRUE),
(9, 'CAR', 'CAR456', FALSE),
(10, 'SCOOTER', 'SCO789', TRUE);

-- -----------------------------------------------------
-- Food establishments
-- -----------------------------------------------------
INSERT INTO food_establishments (name, address, phone_number, open_hours, is_open, rating, rating_count) VALUES
('Pizza Palace', '12 Food St', '4001112233', '10:00-22:00', TRUE, 4.5, 200),
('Sushi World', '34 Ocean Ave', '4002223344', '11:00-23:00', TRUE, 4.8, 150),
('Burger Town', '56 Grill Rd', '4003334455', '09:00-21:00', TRUE, 4.2, 300),
('Green Supermarket', '78 Market Ln', '4004445566', '08:00-22:00', TRUE, 4.1, 500),
('FreshMart', '90 Healthy Rd', '4005556677', '07:00-23:00', TRUE, 4.3, 350),
('Pasta House', '22 Italy St', '4006667788', '10:00-23:00', FALSE, 4.6, 120),
('Taco Fiesta', '44 Mexico Rd', '4007778899', '11:00-23:00', TRUE, 4.7, 250),
('Urban Market', '55 City Ave', '4008889900', '08:00-20:00', TRUE, 4.0, 600),
('Steak Grill', '77 BBQ Blvd', '4009990011', '12:00-23:00', TRUE, 4.4, 180),
('Vegan Deli', '66 Plant Rd', '4001110099', '09:00-21:00', TRUE, 4.9, 210);

-- -----------------------------------------------------
-- Restaurants
-- -----------------------------------------------------
INSERT INTO restaurants (restaurant_id, cuisine_type) VALUES
(1, 'Italian'),
(2, 'Japanese'),
(3, 'American'),
(6, 'Italian'),
(7, 'Mexican'),
(9, 'Steakhouse'),
(10, 'Vegan');

-- -----------------------------------------------------
-- Supermarkets
-- -----------------------------------------------------
INSERT INTO supermarkets (supermarket_id, bulkDiscounts) VALUES
(4, TRUE),
(5, FALSE),
(8, TRUE);

-- -----------------------------------------------------
-- Manager Establishments
-- -----------------------------------------------------
INSERT INTO manager_establishments (manager_id, establishment_id) VALUES
(6, 1),
(6, 2),
(6, 4),
(7, 3),
(7, 5),
(7, 6),
(6, 7),
(7, 8),
(6, 9),
(7, 10);

-- -----------------------------------------------------
-- Menu Items
-- -----------------------------------------------------
INSERT INTO menu_items (name, description, price, is_available) VALUES
('Margherita Pizza', 'Classic tomato and mozzarella pizza', 9.99, TRUE),
('Pepperoni Pizza', 'Spicy pepperoni and cheese', 11.50, TRUE),
('California Roll', 'Crab and avocado sushi roll', 7.99, TRUE),
('Salmon Nigiri', 'Fresh salmon over rice', 5.99, TRUE),
('Cheeseburger', 'Grilled beef patty with cheese', 8.49, TRUE),
('Vegan Burger', 'Plant-based patty with veggies', 9.49, TRUE),
('Spaghetti Carbonara', 'Pasta with bacon and cream sauce', 12.99, TRUE),
('Tacos Al Pastor', 'Pork tacos with pineapple', 6.50, TRUE),
('Ribeye Steak', 'Grilled ribeye with seasoning', 19.99, TRUE),
('Vegan Salad Bowl', 'Mixed greens, quinoa, and tofu', 10.99, TRUE);

-- -----------------------------------------------------
-- Restaurant Menu Items
-- -----------------------------------------------------
INSERT INTO restaurant_menu_items (restaurant_item_id, restaurant_id, vegetarian) VALUES
(1, 1, TRUE),
(2, 1, FALSE),
(3, 2, TRUE),
(4, 2, FALSE),
(5, 3, FALSE),
(6, 10, TRUE),
(7, 6, FALSE),
(8, 7, FALSE),
(9, 9, FALSE),
(10, 10, TRUE);

-- -----------------------------------------------------
-- Supermarket Items
-- -----------------------------------------------------
INSERT INTO supermarket_items (supermarket_item_id, supermarket_id, stock_quantity) VALUES
(1, 4, 50),
(2, 4, 40),
(3, 5, 100),
(4, 5, 80),
(5, 8, 200),
(6, 8, 120),
(7, 4, 70),
(8, 5, 90),
(9, 8, 60),
(10, 5, 150);

-- -----------------------------------------------------
-- Orders
-- -----------------------------------------------------
INSERT INTO orders (customer_id, courier_id, establishment_id, order_status, delivery_status, delivery_address, total_price, delivery_time) VALUES
(1, 8, 1, 'PENDING', 'ASSIGNED', '123 Main St', 21.49, NOW()),
(2, 9, 2, 'ACCEPTED', 'ON_THE_WAY', '456 Oak St', 13.98, NOW()),
(3, 10, 3, 'PROCESSING', 'ASSIGNED', '789 Pine St', 8.49, NOW()),
(4, NULL, 4, 'PENDING', 'ASSIGNED', '321 Maple Ave', 50.00, NOW()),
(5, 8, 5, 'REFUSED', 'DELIVERED', '654 Birch Rd', 22.99, NOW()),
(1, 9, 6, 'CANCELLED', 'ASSIGNED', '123 Main St', 12.99, NOW()),
(2, 10, 7, 'PENDING', 'ON_THE_WAY', '456 Oak St', 6.50, NOW()),
(3, 8, 8, 'ACCEPTED', 'DELIVERED', '789 Pine St', 40.00, NOW()),
(4, 9, 9, 'PROCESSING', 'ON_THE_WAY', '321 Maple Ave', 19.99, NOW()),
(5, 10, 10, 'PENDING', 'ASSIGNED', '654 Birch Rd', 10.99, NOW());

-- -----------------------------------------------------
-- Order Items
-- -----------------------------------------------------
INSERT INTO order_items (order_id, item_id, quantity, price) VALUES
(1, 1, 1, 9.99),
(1, 2, 1, 11.50),
(2, 3, 1, 7.99),
(2, 4, 1, 5.99),
(3, 5, 1, 8.49),
(4, 1, 2, 19.98),
(5, 6, 1, 9.49),
(6, 7, 1, 12.99),
(7, 8, 1, 6.50),
(8, 9, 2, 39.98);

-- -----------------------------------------------------
-- Payments (merged: cash + digital)
-- -----------------------------------------------------
INSERT INTO payments (order_id, amount, payment_method, payment_status, cash_received, payment_provider, customer_id, courier_id) VALUES
(1, 21.49, 'CASH', 'COMPLETED', TRUE, NULL, 1, 8),
(2, 13.98, 'CREDIT_CARD', 'COMPLETED', NULL, 'Visa', 2, 9),
(3, 8.49, 'DIGITAL_WALLET', 'FAILED', NULL, 'Apple Pay', 3, 10),
(4, 50.00, 'CASH', 'PENDING', FALSE, NULL, 4, NULL),
(5, 22.99, 'PAYPAL', 'COMPLETED', NULL, 'PayPal', 5, 8),
(6, 12.99, 'DEBIT_CARD', 'FAILED', NULL, 'Stripe', 1, 8),
(7, 6.50, 'CASH', 'COMPLETED', TRUE, NULL, 2, 10),
(8, 40.00, 'DIGITAL_WALLET', 'COMPLETED', NULL, 'Google Pay', 3, 8),
(9, 19.99, 'CREDIT_CARD', 'PENDING', NULL, 'Mastercard', 4, 9),
(10, 10.99, 'CASH', 'COMPLETED', TRUE, NULL, 5, 9);
