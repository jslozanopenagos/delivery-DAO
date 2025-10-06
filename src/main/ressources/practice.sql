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
INSERT INTO supermarkets (supermarket_id) VALUES
(4),
(5),
(8);

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
-- Payments
-- -----------------------------------------------------
INSERT INTO payments (order_id, amount, payment_method, payment_status) VALUES
(1, 21.49, 'CASH', 'COMPLETED'),
(2, 13.98, 'CREDIT_CARD', 'COMPLETED'),
(3, 8.49, 'DIGITAL_WALLET', 'FAILED'),
(4, 50.00, 'CASH', 'PENDING'),
(5, 22.99, 'PAYPAL', 'COMPLETED'),
(6, 12.99, 'DEBIT_CARD', 'FAILED'),
(7, 6.50, 'CASH', 'COMPLETED'),
(8, 40.00, 'DIGITAL_WALLET', 'COMPLETED'),
(9, 19.99, 'CREDIT_CARD', 'PENDING'),
(10, 10.99, 'CASH', 'COMPLETED');

-- -----------------------------------------------------
-- Payments Digital
-- -----------------------------------------------------
INSERT INTO payments_digital (payment_id, customer_id, courier_id, payment_provider) VALUES
(3, 3, 10, 'Apple Pay'),
(8, 3, 8, 'Google Pay'),
(2, 2, 9, 'Visa'),
(5, 5, 8, 'PayPal'),
(9, 4, 9, 'Mastercard'),
(6, 1, 8, 'Stripe'),
(7, 2, 10, 'Square'),
(1, 1, 8, 'CashApp'),
(4, 5, 10, 'Payoneer'),
(10, 3, 9, 'Amazon Pay');

-- -----------------------------------------------------
-- Payments Cash
-- -----------------------------------------------------
INSERT INTO payments_cash (payment_id, customer_id, courier_id, cash_received) VALUES
(1, 1, 8, TRUE),
(4, 4, NULL, FALSE),
(7, 2, 10, TRUE),
(10, 5, 9, TRUE),
(3, 3, 10, FALSE),
(2, 2, 9, TRUE),
(5, 5, 8, TRUE),
(6, 1, 8, FALSE),
(8, 3, 8, TRUE),
(9, 4, 9, FALSE);
