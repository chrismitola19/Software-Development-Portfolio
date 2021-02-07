USE Hotel;

SELECT *
FROM Room;

SELECT *
FROM Guest;

SELECT *
FROM Reservation;

SELECT FirstName
FROM Guest;

-- Write a query that returns a list of reservations that end in July 2023, including the name of the guest, the room number(s), 
-- and the reservation dates.
SELECT 
g.FirstName,
g.LastName,
r.RoomId,
re.StartDate,
re.EndDate
FROM Guest g
INNER JOIN Reservation re ON g.GuestId = re.GuestId
INNER JOIN RoomReservation rr ON re.ReservationId = rr.ReservationId
INNER JOIN Room r ON rr.RoomId = r.RoomID
WHERE re.EndDate BETWEEN '2023/7/1' AND '2023/7/31';
-- returns 4 rows 

-- Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name, 
-- the room number, and the dates of the reservation.
SELECT DISTINCT
g.FirstName,
g.Lastname,
r.RoomId,
re.StartDate,
re.EndDate,
a.AmenityName
FROM  Room r
INNER JOIN RoomAmenity ra ON r.RoomId = ra.RoomId
INNER JOIN Amenity a  ON ra.AmenityId = a.AmenityID
INNER JOIN RoomReservation rr ON r.RoomId = rr.RoomId
INNER JOIN Reservation re ON rr.ReservationId = re.ReservationId
INNER JOIN Guest g ON re.GuestId = g.GuestId
WHERE a.AmenityName LIKE '%Jacuzzi%';
-- returns 11 rows


-- Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) reserved, the starting 
-- date of the reservation, and how many people were included in the reservation. (Choose a guest's name from the existing data.)
SELECT
g.FirstName,
g.LastName,
r.RoomID,
re.StartDate,
re.TotalGuests
FROM Guest g
INNER JOIN Reservation re ON g.GuestId = re.GuestId
INNER JOIN RoomReservation rr ON re.ReservationId = rr.ReservationId
INNER JOIN Room r ON rr.RoomId = r.RoomID
WHERE g.GuestId = 2;
-- returns 4 rows for Mack Simmer 


-- Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
-- The results should include all rooms, whether or not there is a reservation associated with the room.
SELECT
r.RoomId,
re.ReservationId,
re.TotalCost
FROM Room r
LEFT OUTER JOIN RoomReservation rr ON r.RoomId = rr.RoomId
LEFT OUTER JOIN Reservation re ON rr.ReservationId = re.ReservationId;
-- 27 rows returned, 3 have NULL values, LEFT OUTER gets the NULL values


-- Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.
SELECT 
g.FirstName,
g.LastName,
r.RoomId,
re.StartDate,
re.EndDate,
re.TotalGuests
FROM Guest g
INNER JOIN Reservation re ON g.GuestId = re.GuestId
INNER JOIN RoomReservation rr ON re.ReservationId = rr.ReservationId
INNER JOIN Room r ON rr.RoomId = r.RoomID
WHERE re.StartDate BETWEEN '2023/4/1' AND '2031/4/30' AND re.EndDate BETWEEN '2023/4/1' AND '2023/4/30'
AND re.TotalGuests >= 3;
-- returns no rows beacuse there are no reservations for rooms with 3 or more guests in April.


-- Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting with 
-- the guest with the most reservations and then by the guest's last name.
SELECT 
g.FirstName,
g.LastName,
COUNT(re.ReservationId) 
FROM Guest g
INNER JOIN Reservation re ON g.GuestId = re.GuestId
GROUP BY g.GuestId, g.LastName
ORDER BY COUNT(re.ReservationId) DESC, g.Lastname ASC;
-- returns 11 rows showing the most reservations first, if a guest has the same amount of reservations as another it orders by last name Desc.


-- Write a query that displays the name, address, and phone number of a guest based on their phone number. 
-- (Choose a phone number from the existing data.)
SELECT 
g.FirstName,
g.LastName,
g.Address,
g.Phone
FROM Guest g
WHERE g.Phone = '(291) 553-0508';
-- returns Mack Simmer

SELECT 
RoomId,
BasePrice,
ExtraPerson,
SUM(BasePrice + COALESCE(ExtraPerson, 0))Total    -- coalesce returns 0 if null 
FROM Room
GROUP BY RoomId;


