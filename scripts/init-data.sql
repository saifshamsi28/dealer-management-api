-- Insert sample users for authentication
INSERT INTO users (username, password, enabled) VALUES 
('admin', '$2a$10$DowJonesIndex123456789012345678901234567890123456789012345678', true),
('dealer1', '$2a$10$DowJonesIndex123456789012345678901234567890123456789012345678', true);

-- Insert sample dealers
INSERT INTO dealers (name, email, subscription_type) VALUES 
('Premium Motors', 'premium@motors.com', 'PREMIUM'),
('Basic Cars', 'basic@cars.com', 'BASIC'),
('Elite Vehicles', 'elite@vehicles.com', 'PREMIUM'),
('Standard Auto', 'standard@auto.com', 'BASIC');

-- Insert sample vehicles
INSERT INTO vehicles (dealer_id, model, price, status) VALUES 
(1, 'BMW X5', 75000.00, 'AVAILABLE'),
(1, 'Mercedes C-Class', 65000.00, 'SOLD'),
(2, 'Toyota Camry', 35000.00, 'AVAILABLE'),
(2, 'Honda Civic', 28000.00, 'AVAILABLE'),
(3, 'Audi A4', 55000.00, 'AVAILABLE'),
(3, 'Porsche 911', 120000.00, 'SOLD'),
(4, 'Ford Focus', 25000.00, 'AVAILABLE'),
(4, 'Chevrolet Malibu', 32000.00, 'AVAILABLE');

-- Insert sample payments
INSERT INTO payments (dealer_id, amount, payment_method, status, created_at) VALUES 
(1, 999.99, 'CARD', 'SUCCESS', NOW()),
(2, 499.99, 'UPI', 'SUCCESS', NOW()),
(3, 999.99, 'NETBANKING', 'PENDING', NOW()),
(4, 499.99, 'CARD', 'PENDING', NOW());
