USE marvinairline;

-- ----------------- airplane---------------
INSERT INTO airplane (airplane_type) VALUES
('AIRBUS A380'),
( 'BOEING 707');

-- ----------------- seattype---------------
INSERT INTO seat_type VALUES
('Economique', 30),
('Affaire', 100),
('Premiere', 200);

-- ----------------- seat---------------
INSERT INTO seat (number, column_letter, seat_type, airplane_id) VALUES
 ( 1, 'A', 'Premiere', 1),
 ( 1, 'B', 'Premiere', 1),
 ( 1, 'C','Premiere', 1),
 ( 1, 'D','Premiere', 1),
 ( 1, 'E','Premiere', 1),
 ( 1, 'F','Premiere', 1),
 ( 2, 'A', 'Affaire', 1),
 ( 2, 'B', 'Affaire', 1),
 ( 2, 'C', 'Affaire', 1),
 ( 2, 'D', 'Affaire', 1),
 ( 2, 'E', 'Affaire', 1),
 ( 2, 'F', 'Affaire', 1),
 ( 3, 'A', 'Economique', 1),
 ( 3, 'B', 'Economique', 1),
 ( 3, 'C', 'Economique', 1),
 ( 3, 'D', 'Economique', 1),
 ( 3, 'E', 'Economique', 1),
 ( 3, 'F', 'Economique', 1),
 ( 1, 'A', 'Premiere', 2),
 ( 1, 'B', 'Premiere', 2),
 ( 1, 'C','Premiere', 2),
 ( 1, 'D','Premiere', 2),
 ( 1, 'E','Premiere', 2),
 ( 1, 'F','Premiere', 2),
 ( 2, 'A', 'Affaire', 2),
 ( 2, 'B', 'Affaire', 2),
 ( 2, 'C', 'Affaire', 2),
 ( 2, 'D', 'Affaire', 2),
 ( 2, 'E', 'Affaire', 2),
 ( 2, 'F', 'Affaire', 2),
 ( 3, 'A', 'Economique', 2),
 ( 3, 'B', 'Economique', 2),
 ( 3, 'C', 'Economique', 2),
 ( 3, 'D', 'Economique', 2),
 ( 3, 'E','Economique', 2),
 ( 3, 'F','Economique', 2);

-- ----------------- locality ---------------
INSERT INTO locality VALUES
 ('Bruxelles', '1000', 'Belgique'),
 ('Charleroi', '6000', 'Belgique'),
 ('Paris', '75000', 'France'),
 ('Marseille', '13000', 'France'),
 ('Londres', '25029', 'Royaume-Uni'),
 ('Amsterdam', '1109', 'Pays-Bas'),
 ('Barcelone', '8820', 'Espagne'),
 ('Valence', '26000', 'Espagne'),
 ('Madrid', '28042', 'Espagne'),
 ('New York', '10001', 'Etats-Unis'),
 ('Los Angeles', '90210', 'Etats-Unis'),
 ('Namur', '5000', 'Belgique');

-- ----------------- airport ---------------
INSERT INTO airport (name, city, post_code, country) VALUES
('Bruxelles Airport', 'Bruxelles', '1000', 'Belgique'),
('Charleroi Airport', 'Charleroi', '6000', 'Belgique'),
('Paris Charles de Gaulle Airport','Paris', '75000', 'France'),
('Paris Orly Airport', 'Paris', '75000', 'France'),
('Marseille Airport', 'Marseille', '13000', 'France'),
('Londres Heatrow Airport', 'Londres', '25029', 'Royaume-Uni'),
('Londres Gatwick Airport', 'Londres', '25029', 'Royaume-Uni'),
('Amsterdam Airport Schiphol', 'Amsterdam', '1109', 'Pays-Bas'),
('Barcelona Tarradellas Airport', 'Barcelone', '8820', 'Espagne'),
('Madrid Barajas Airport', 'Madrid', '28042', 'Espagne'),
('Valencia Airport', 'Valence', '26000', 'Espagne'),
('NY JFK International Airport', 'New York', '10001', 'Etats-Unis'),
('Newark International Airport', 'New York', '10001', 'Etats-Unis'),
('Los Angeles International Airport', 'Los Angeles', '90210', 'Etats-Unis');


--  --------------------flight --------------------------------
INSERT INTO flight (departure_date, departure_hour, expected_arrival_date, expected_arrival_hour, price, airplane_id, departure_airport_id, arrival_airport_id)
VALUES
('2022-06-20', '16:00:00','2022-06-20','18:10:00', 100, 1, 1, 5),
('2022-06-25', '16:00:00','2022-06-25','18:10:00', 100, 1, 5, 1),
('2022-06-20', '10:00:00','2022-06-20','11:25:00', 79.99, 2, 2, 10),
('2022-06-25', '10:00:00','2022-06-25','11:25:00', 79.99, 2, 10, 2),
('2022-06-28', '14:05:00','2022-06-28','16:20:00', 50.50, 1, 1, 11),
('2022-07-05', '11:25:00','2022-07-05','13:40:00', 50.50, 1, 11, 1),
('2022-06-21', '23:50:00','2022-06-22','04:50:00', 749.50, 2, 3, 12),
('2022-06-26', '23:50:00','2022-06-27','16:50:00', 749.50, 2, 12, 3),
('2022-06-22', '03:00:00','2022-06-22','13:50:00', 699, 1, 4, 13),
('2022-06-27', '03:00:00','2022-06-27','19:50:00', 699, 1, 13, 4),
('2022-06-22', '11:00:00','2022-06-22','12:40:00', 100, 2, 5, 8),
('2022-06-27', '11:00:00','2022-06-27','12:40:00', 100, 2, 8, 5),
('2022-06-23', '7:00:00','2022-06-23','18:40:00', 850.0, 1, 6, 14),
('2022-06-28', '7:00:00','2022-06-29','00:40:00', 850.0, 1, 14, 6),
('2022-06-23', '7:00:00','2022-06-23','18:40:00', 810.86, 1, 7, 14),
('2022-06-28', '7:00:00','2022-06-29','00:40:00', 805.86, 1, 14, 7),
('2022-06-25', '10:00:00','2022-06-25','16:00:00', 720, 1, 4, 13),
('2022-06-23', '22:00:00','2022-06-24','04:40:00', 749.50, 2, 3, 12),
('2022-06-25', '21:50:00','2022-06-26','02:50:00', 699.99, 2, 3, 12),
('2022-06-28', '14:20:00','2022-06-28','22:00:00', 760, 2, 3, 12),
('2022-06-26', '06:00:00','2022-06-26','13:40:00', 670, 1, 4, 13),
('2022-06-27', '01:00:00','2022-06-27','08:40:00', 675, 1, 4, 13),
('2022-06-28', '10:00:00','2022-06-28','20:00:00', 699, 1, 4, 13),
('2022-06-29', '05:00:00','2022-06-29','15:50:00', 715, 1, 4, 13);

-- -------------------- stopover ------------
INSERT INTO stopover (flight_id, airport_id, duration)
VALUES
 (9,8,75),
 (9,7,120),
 (10,7,120),
 (10,8,75),
 (15, 12, 65),
 (16, 13, 110),
 (17,8,60),
 (18,7,100),
 (20,8,60),
 (20,7,100),
 (23,7,150),
 (24,8,75),
 (24,7,120);


-- ------------------------Passenger--------------------------------
INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES
('Parache', 'Melvin',null, '2000-01-01', 'mp@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique'),
('Howet', 'Marie','D.', '2000-01-01', 'mh@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique'),
('Libert', 'Charlotte','H.', '2000-01-01', 'cl@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique'),
('Louis', 'Sarah','G.', '2000-01-01','sl@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique'),
('Sermeus', 'Steven',null, '2000-01-01', 'ss@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique'),
('Nyssens', 'Elisabeth','M.', '2000-01-01', 'en@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique'),
('Turpin', 'Guillaume',null, '2000-01-01', 'gt@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique'),
('Hanquet', 'Julien','M.', '2000-01-01', 'jh@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

