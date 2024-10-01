CREATE DATABASE IF NOT EXISTS hotelDB;

USE hotelDB;

CREATE TABLE Room (
    room_number INT PRIMARY KEY AUTO_INCREMENT,
    room_type VARCHAR(20) NOT NULL,
    is_available BOOLEAN DEFAULT TRUE
);

CREATE TABLE Reservation (
    reservation_id INT PRIMARY KEY AUTO_INCREMENT,
    guest_name VARCHAR(100) NOT NULL,
    room_number INT,
    FOREIGN KEY (room_number) REFERENCES Room(room_number)
);

INSERT INTO Room (room_type, is_available) VALUES
('Single', TRUE),
('Double', TRUE),
('Suite', TRUE),
('Single', FALSE),
('Double', TRUE),
('Suite', FALSE);

INSERT INTO Reservation (guest_name, room_number) VALUES
('Alice', 4), 
('Bob', 6); 
