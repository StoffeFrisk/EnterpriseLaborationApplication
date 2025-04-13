INSERT INTO address (address_id, street, postal_code, city) VALUES (1, 'Storgatan 1', 11122, 'Stockholm');
INSERT INTO address (address_id, street, postal_code, city) VALUES (2, 'Glimmervägen 7a', 22233, 'Uppsala');
INSERT INTO address (address_id, street, postal_code, city) VALUES (3, 'Östra gatan 3', 33344, 'Malmö');
INSERT INTO address (address_id, street, postal_code, city) VALUES (4, 'Ullsaxvägen 11', 76548, 'Uppsala');

INSERT INTO member (id, first_name, last_name, email, phone, date_of_birth, address_id) VALUES (1, 'Anna', 'Andersson', 'anna@email.com', '0701234567', 19900101, 1);
INSERT INTO member (id, first_name, last_name, email, phone, date_of_birth, address_id) VALUES (2, 'Bertil', 'Berg', 'bertil@email.com', '0702345678', 19920202, 2);
INSERT INTO member (id, first_name, last_name, email, phone, date_of_birth, address_id) VALUES (3, 'Hilda', 'Carlsson', 'cecilia@email.com', NULL, 19930303, 3);
INSERT INTO member (id, first_name, last_name, email, phone, date_of_birth, address_id) VALUES (4, 'David', 'Dahl', 'david@email.com', '0703456789', 19940404, 4);
INSERT INTO member (id, first_name, last_name, email, phone, date_of_birth, address_id) VALUES (5, 'Eva', 'Ek', 'eva@email.com', '0704567890', 19950505, 2);