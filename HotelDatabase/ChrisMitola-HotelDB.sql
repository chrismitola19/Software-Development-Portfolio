DROP DATABASE IF EXISTS Hotel;

CREATE DATABASE Hotel;

USE Hotel;

CREATE TABLE RoomType (
RoomTypeId INT PRIMARY KEY NOT NULL,
RoomTypeName VARCHAR(30) NOT NULL
);

CREATE TABLE Room (
RoomId INT PRIMARY KEY NOT NULL,
RoomTypeId INT NOT NULL,
ADAAccessible VARCHAR(5) NOT NULL,
StanOcc INT NOT NULL,
MaxOcc INT NOT NULL,
BasePrice DECIMAL(6,2) NOT NULL,
ExtraPerson DECIMAL(6,2) NULL,
FOREIGN KEY fk_Room_RoomType(RoomTypeId)
REFERENCES RoomType(RoomTypeId)
);

CREATE TABLE Amenity (
AmenityId INT PRIMARY KEY NOT NULL,
AmenityName VARCHAR(50) NOT NULL
);

CREATE TABLE RoomAmenity (
RoomId INT NOT NUll,
AmenityId INT NOT NULL,
PRIMARY KEY (RoomId, AmenityId),
FOREIGN KEY fk_RoomAmenity_Room(RoomId)
REFERENCES Room(RoomId),
FOREIGN KEY fk_RoomAmenity_Amenity(AmenityId)
REFERENCES Amenity(AmenityId)
);

CREATE TABLE Guest (
GuestId INT PRIMARY KEY NOT NULL,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50) NOT NULL,
Address VARCHAR(120) NOT NULL,
City VARCHAR(30) NOT NULL,
State CHAR(2) NOT NULL,
Zip VARCHAR(12) NOT NULL,
Phone VARCHAR(36) NOT NULL
);

CREATE TABLE Reservation (
ReservationId INT PRIMARY KEY NOT NULL,
GuestId INT NOT NULL,
FirstName VARCHAR(50) NOT NULL,
LastName VARCHAR(50) NOT NULL,
Adults INT NOT NULL,
Children INT NULL,
TotalGuests INT Not Null,
StartDate DATE NOT NULL,
EndDate DATE NOT NULL,
TotalCost Decimal(6,2) NOT NULL,
FOREIGN KEY fk_Reservation_Guest(GuestId) 
REFERENCES Guest(GuestId)
);

CREATE TABLE RoomReservation (
RoomId INT NOT NULL,
ReservationId INT NOT NULL,
PRIMARY KEY(RoomId, ReservationId),
FOREIGN KEY fk_RoomReservation_Room(RoomId) 
REFERENCES Room(RoomId),
FOREIGN KEY fk_RoomReservation_Reservation(ReservationId)
REFERENCES Reservation(ReservationId)
);

-- ADD DATA -- 

INSERT INTO RoomType(RoomTypeId, RoomTypeName) VALUES
(1, 'Single'),
(2, 'Double'),
(3, 'Suite');

 INSERT INTO Room (RoomId, RoomTypeId, ADAAccessible, StanOcc, MaxOcc, BasePrice, ExtraPerson) VALUES
 (201, 2, 'No', 2, 4, 199.99, 10.00),
 (202, 2, 'Yes', 2,	4, 174.99, 10.00),
 (203, 2, 'No', 2, 4,	199.99,	10.00),
 (204, 2, 'Yes', 2, 4, 174.99, 10.00),
 (205, 1, 'No',	2, 2, 174.99, NULL),
 (206, 1, 'Yes', 2, 2, '149.99', NULL),
 (207, 1, 'No',	2, 2, 174.99, NULL),
 (208, 1, 'Yes', 2, 2, 149.99, NULL),
 (301, 2, 'No', 2, 4, 199.99, 10.00),
 (302, 2, 'Yes', 2, 4, 174.99, 10.00),
 (303, 2, 'No', 2,	4, 199.99, 10.00),
 (304, 2, 'Yes', 2, 4, 174.99, 10.00),
 (305, 1, 'No', 2, 2, 174.99, NULL),
 (306, 1, 'Yes', 2, 2, 149.99, NULL),
 (307, 1, 'No',	2, 2, 174.99, NULL),
 (308, 1, 'Yes', 2, 2, 149.99, NULL),
 (401, 3, 'Yes', 3, 8, 399.99, 20.00),
 (402, 3, 'Yes', 3, 8, 399.99, 20.00),
 (555, 2, 'Yes', 3, 6, 299.00, 10);
 
 INSERT INTO Guest(GuestId, FirstName, LastName, Address, City, State, Zip, Phone) VALUES
(1, 'Chris', 'Mitola', 'Nunya',	'Bizness', '??', '00000', '111-111-1111'),
(2, 'Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs',	'IA', '51501', '(291) 553-0508'),
(3, 'Bettyann', 'Seery', '750 Wintergreen Dr.',	'Wasilla', 'AK', '99654', '(478) 277-9632'),
(4, 'Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX',	'78552', '(308) 494-0198'),
(5, 'Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', '08096', '(214) 730-0298'),
(6, 'Aurore', 'Lipton',	'762 Wild Rose Street',	'Saginaw', 'MI', '48601', '(377) 507-0974'),
(7, 'Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', '80003', '(814) 485-2615'),
(8, 'Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', '60099', '(279) 491-0960'),
(9, 'Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', '02864', '(446) 396-6785'),
(10, 'Wilfred', 'Vise',	'77 West Surrey Street', 'Oswego', 'NY', '13126', '(834) 727-1001'),
(11, 'Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', '22015', '(446) 351-6860'),
(12, 'Joleen', 'Tison',	'87 Queen St.',	'Drexel Hill', 'PA', '19026', '(231) 893-2755');

INSERT INTO Reservation(ReservationId, GuestId, FirstName, LastName, Adults, Children, TotalGuests, StartDate, EndDate, TotalCost) VALUES
(1,  2, 'Mack', 'Simmer', 1, 0, 1, '2023/2/2','2023/2/4', 299.98),
(2,  3, 'Bettyann', 'Seery', 2, 1, 3,'2023/2/5', '2023/2/10', 999.95),
(3,  4, 'Duane', 'Cullison', 2, 0, 2, '2023/2/22', '2023/2/24', 349.98),
(4,  5, 'Karie', 'Yang', 2, 2, 4, '2023/3/6', '2023/3/7', 199.99),
(5,  1, 'Chris', 'Mitola', 1, 1, 2, '2023/3/17', '2023/3/20', 524.97),
(6,  6, 'Aurore', 'Lipton', 3,	0, 3, '2023/3/18',	'2023/3/23', 924.95),
(7,  7, 'Zachery', 'Luechtefeld', 2, 2, 4, '2023/3/29', '2023/3/31', 349.98),
(8,  8, 'Jeremiah', 'Pendergrass',	2, 0, 2, '2023/3/31', '2023/4/5',	874.95),
(9,  9, 'Walter', 'Holaway', 1, 0, 1, '2023/4/9', '2023/4/13', 799.96),
(10, 10, 'Wilfred', 'Vise', 1,	1, 2, '2023/4/23',	'2023/4/24', 174.99),
(11, 11, 'Maritza', 'Tilton', 2, 4, 6, '2023/5/30', '2023/6/2', 1199.97),
(12, 12, 'Joleen', 'Tison', 2,	0, 2, '2023/6/10',	'2023/6/14', 599.96),
(13, 12, 'Joleen', 'Tison', 1,	0, 1, '2023/6/10',	'2023/6/14', 599.96),
(14, 6, 'Aurore', 'Lipton', 3,	0, 3, '2023/6/17', '2023/6/18', 184.99),
(15, 1, 'Chris', 'Mitola',	2, 0, 2, '2023/6/28', '2023/7/2', 699.96),
(16, 9, 'Walter', 'Holaway', 3, 1, 4, '2023/7/13', '2023/7/14', 184.99),
(17, 10, 'Wilfred', 'Vise', 4,	2, 6, '2023/7/18',	'2023/7/21', 1259.97),
(18, 3, 'Bettyann', 'Seery', 2, 1, 3, '2023/7/28', '2023/7/29', 199.99),
(19, 3, 'Bettyann', 'Seery', 1, 0, 1, '2023/8/30', '2023/9/1', 349.98),
(20, 2, 'Mack', 'Simmer', 2, 0, 2, '2023/9/16', '2023/9/17', 149.99),
(21, 5, 'Karie', 'Yang', 2, 2, 4,'2023/9/13', '2023/9/15', 399.98),
(22, 4, 'Duane', 'Cullison', 2, 2, 4, '2023/11/22', '2023/11/25', 1199.97),
(23, 2, 'Mack', 'Simmer', 2, 0, 2, '2023/11/22', '2023/11/25', 449.97),
(24, 2, 'Mack', 'Simmer', 2, 2, 4, '2023/11/22', '2023/11/25', 599.97),
(25, 11, 'Maritza', 'Tilton', 2, 0, 2, '2023/12/24', '2023/12/28', 699.96);

INSERT INTO RoomReservation(RoomId, ReservationId) VALUES
(308, 1),
(203, 2),
(305, 3),
(201, 4),
(307, 5),
(302, 6),
(202, 7),
(304, 8),
(301, 9),
(207, 10),
(401, 11),
(206, 12),
(208, 13),
(304, 14),
(205, 15),
(204, 16),
(401, 17),
(303, 18),
(305, 19),
(208, 20),
(203, 21),
(401, 22),
(206, 23),
(301, 24),
(302, 25);

INSERT INTO Amenity(AmenityId, AmenityName) VALUES 
(1, 'Microwave, Jacuzzi'),
(2, 'Refrigerator'),
(3, 'Microwave, Refrigerator, Jacuzzi'),
(4, 'Microwave, Refrigerator'),
(5, 'Microvave, Refrigerator, Oven'); 

INSERT INTO RoomAmenity(AmenityId, RoomId) VALUES
(1, 201),
(2, 202),
(1, 203),
(2, 204),
(3, 205),
(4, 206),
(3, 207),
(4, 208),
(1, 301),
(2, 302),
(1, 303),
(2, 304),
(3, 305),
(4, 306),
(3, 307),
(4, 308),
(5, 401),
(5, 402);

-- Delete Jeremy Pendergrass from records --

DELETE FROM RoomReservation
WHERE ReservationId = 8;

DELETE FROM Reservation 
WHERE GuestId = 8;

DELETE FROM Guest
WHERE GuestId = 8;
