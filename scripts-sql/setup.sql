CREATE SCHEMA marvinairline;

USE marvinairline;

CREATE TABLE `airplane` (
                            `id` INT NOT NULL,
                            `airplane_type` VARCHAR(45) NOT NULL,-- 45 ?
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat_type` (
                             `name` VARCHAR(20) NOT NULL,-- 20 ?
                             `additional_percent` DECIMAL(3,1) NOT NULL,
                             PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat` (
                        `id` INT NOT NULL,
                        `number` INT NOT NULL,
                        `column_letter` CHAR NOT NULL,-- default size = 1
                        `airplane_id` INT NOT NULL,
                        `seat_type_name` VARCHAR(20) NOT NULL,-- 20 ?
                        PRIMARY KEY (`id`),
                        -- KEY `airplane_idx` (`airplane_id`),-- ?
                        -- KEY `seat_type_idx` (`seat_type_name`),-- ?
                        CONSTRAINT `airplane_seat_fk` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`id`),
                        CONSTRAINT `seat_type_fk` FOREIGN KEY (`seat_type_name`) REFERENCES `seat_type` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `locality` (
                            `city` VARCHAR(45) NOT NULL,-- 45 ?
                            `post_code` VARCHAR(5) NOT NULL,
                            `country` VARCHAR(45) NOT NULL,
                            PRIMARY KEY (`city`,`post_code`,`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `airport` (
                           `code` VARCHAR(6) NOT NULL,
                           `name` VARCHAR(45) NOT NULL,
                           `city` VARCHAR(45) NOT NULL,
                           `post_code` VARCHAR(5) NOT NULL,
                           `country` VARCHAR(45) NOT NULL,
                           PRIMARY KEY (`code`),
                           -- KEY `city_idx` (`city`,`post_code`,`country`),
                           CONSTRAINT `locality_airport_fk` FOREIGN KEY (`city`, `post_code`, `country`) REFERENCES `locality` (`city`, `post_code`, `country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `flight` (
                          `id` INT NOT NULL,
                          `departure_date` DATE NOT NULL,
                          `departure_hour` TIME NOT NULL,
                          `expected_arrival_date` DATE NOT NULL,
                          `expected_arrival_hour` TIME NOT NULL,
                          `price` DECIMAL(7,2) NOT NULL,
                          `departure_airport_code` VARCHAR(6) NOT NULL,
                          `arrival_airport_code` VARCHAR(6) NOT NULL,
                          `airplane_id` INT NOT NULL,
                          PRIMARY KEY (`id`),
                          -- KEY `departure_airport_idx` (`departure_airport_code`),
                          -- KEY `arrival_airport_idx` (`arrival_airport_code`),
                          -- KEY `airplane_idx` (`airplane_id`),
                          CONSTRAINT `airplane_flight_fk` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`id`),
                          CONSTRAINT `arrival_airport_fk` FOREIGN KEY (`arrival_airport_code`) REFERENCES `airport` (`code`),
                          CONSTRAINT `departure_airport_fk` FOREIGN KEY (`departure_airport_code`) REFERENCES `airport` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `stopover` (
                            `flight_id` INT NOT NULL,
                            `airport_code` VARCHAR(6) NOT NULL,
                            `duration` INT NOT NULL,
                            PRIMARY KEY (`flight_id`,`airport_code`),
                           -- KEY `airport_stopover_idx` (`airport_code`),
                            CONSTRAINT `airport_stopover_fk` FOREIGN KEY (`airport_code`) REFERENCES `airport` (`code`),
                            CONSTRAINT `flight_stopover_fk` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `passenger` (
                             `id` INT NOT NULL,
                             `last_name` VARCHAR(25) NOT NULL,
                             `first_name` VARCHAR(25) NOT NULL,
                             `initial_middle_name` VARCHAR(45) DEFAULT NULL,
                             `birth_date` DATE NOT NULL,
                             `gender` VARCHAR(10) NOT NULL,
                             `email` VARCHAR(60) NOT NULL,
                             `phone_number` VARCHAR(20) NOT NULL,
                             `street_and_number` VARCHAR(60) NOT NULL,
                             `city` VARCHAR(45) NOT NULL,
                             `post_code` VARCHAR(5) NOT NULL,
                             `country` VARCHAR(45) NOT NULL,
                             PRIMARY KEY (`id`),
                             -- KEY `city_passenger_idx` (`city`,`post_code`,`country`),
                             CONSTRAINT `locality_passenger_fk` FOREIGN KEY (`city`, `post_code`, `country`) REFERENCES `locality` (`city`, `post_code`, `country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat_booked` (
                               `flight_id` INT NOT NULL,
                               `seat_id` INT NOT NULL,
                               `booking_id` INT DEFAULT NULL,
                               PRIMARY KEY (`flight_id`,`seat_id`),
                               -- KEY `seat_seat_booked_idx` (`seat_id`),
                               CONSTRAINT `flight_seat_booked-fk` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
                               CONSTRAINT `seat_seat_booked_fk` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`),
                               CONSTRAINT `booking_fk` FOREIGN KEY (`booking_id`) REFERENCES `booking`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `booking` (
                           `id` int NOT NULL,
                           `date` date NOT NULL,
                           `has_paid` tinyint(1) NOT NULL,
                           `luggage_weight` int DEFAULT NULL,
                           `company_name` varchar(45) DEFAULT NULL,
                           `meal_type` varchar(15) NOT NULL,
                           `real_price` decimal(7,2) NOT NULL,
                           `passenger_id` int NOT NULL,
                           `seat_id` int NOT NULL,
                           `flight_id` int NOT NULL,
                           PRIMARY KEY (`id`),
                           -- KEY `seat_booking_idx` (`seat_id`),
                           -- KEY `flight_booking_idx` (`flight_id`),
                           -- KEY `passenger_booking_idx` (`passenger_id`),
                           CONSTRAINT `flight_booking_fk` FOREIGN KEY (`flight_id`) REFERENCES `seat_booked` (`flight`),
                           CONSTRAINT `passenger_booking_fk` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`),
                           CONSTRAINT `seat_booking_fk` FOREIGN KEY (`seat_id`) REFERENCES `seat_booked` (`seat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------- airplane---------------
INSERT INTO airplane
VALUES (1, 'AIRBUS A380');

INSERT INTO airplane
VALUES (2, 'BOEING 707');

-- ----------------- seattype---------------

INSERT INTO seat_type
VALUES ('Classe économique', 30);

INSERT INTO seat_type
VALUES ('Classe business', 100);

INSERT INTO seat_type
VALUES ('Première classe', 200);
-- ----------------- seat---------------

INSERT INTO seat
VALUES (111, 1, 'A', 1,'Première classe');

INSERT INTO seat
VALUES (112, 1, 'B', 1,'Première classe');

INSERT INTO seat
VALUES (113, 1, 'C',1,'Première classe');

INSERT INTO seat
VALUES (114, 1, 'D',1,'Première classe');

INSERT INTO seat
VALUES (115, 1, 'E',1,'Première classe');

INSERT INTO seat
VALUES (116, 1, 'F',1,'Première classe');

INSERT INTO seat
VALUES (121, 2, 'A', 1,'Classe business');

INSERT INTO seat
VALUES (122, 2, 'B', 1,'Classe business');

INSERT INTO seat
VALUES (123, 2, 'C', 1,'Classe business');

INSERT INTO seat
VALUES (124, 2, 'D', 1,'Classe business');

INSERT INTO seat
VALUES (125, 2, 'E', 1,'Classe business');

INSERT INTO seat
VALUES (126, 2, 'F', 1,'Classe business');

INSERT INTO seat
VALUES (131, 3, 'A', 1,'Classe économique');

INSERT INTO seat
VALUES (132, 3, 'B', 1,'Classe économique');

INSERT INTO seat
VALUES (133, 3, 'C', 1,'Classe économique');

INSERT INTO seat
VALUES (134, 3, 'D', 1,'Classe économique');

INSERT INTO seat
VALUES (135, 3, 'E', 1,'Classe économique');

INSERT INTO seat
VALUES (136, 3, 'F', 1,'Classe économique');

INSERT INTO seat
VALUES (211, 1, 'A', 2,'Première classe');

INSERT INTO seat
VALUES (212, 1, 'B', 2,'Première classe');

INSERT INTO seat
VALUES (213, 1, 'C',2,'Première classe');

INSERT INTO seat
VALUES (214, 1, 'D',2,'Première classe');

INSERT INTO seat
VALUES (215, 1, 'E',2,'Première classe');

INSERT INTO seat
VALUES (216, 1, 'F',2,'Première classe');

INSERT INTO seat
VALUES (221, 2, 'A', 2,'Classe business');

INSERT INTO seat
VALUES (222, 2, 'B', 2,'Classe business');

INSERT INTO seat
VALUES (223, 2, 'C', 2,'Classe business');

INSERT INTO seat
VALUES (224, 2, 'D', 2,'Classe business');

INSERT INTO seat
VALUES (225, 2, 'E', 2,'Classe business');

INSERT INTO seat
VALUES (226, 2, 'F', 2,'Classe business');

INSERT INTO seat
VALUES (231, 3, 'A', 2,'Classe économique');

INSERT INTO seat
VALUES (232, 3, 'B', 2,'Classe économique');

INSERT INTO seat
VALUES (233, 3, 'C', 2,'Classe économique');

INSERT INTO seat
VALUES (234, 3, 'D', 2,'Classe économique');

INSERT INTO seat
VALUES (235, 3, 'E', 2,'Classe économique');

INSERT INTO seat
VALUES (236, 3, 'F', 2,'Classe économique');

-- ----------------- locality ---------------
INSERT INTO locality
VALUES ('Bruxelles', '1000', 'Belgique');

INSERT INTO locality
VALUES ('Charleroi', '6000', 'Belgique');

INSERT INTO locality
VALUES ('Paris', '75000', 'France');

INSERT INTO locality
VALUES ('Marseille', '13000', 'France');

INSERT INTO locality
VALUES ('Paris', '75000', 'France');

INSERT INTO locality
VALUES ('Londres', '250 329', 'Royaume-Uni');

INSERT INTO locality
VALUES ('Amsterdam', '1109', 'Pays-Bas');

INSERT INTO locality
VALUES ('Madrid', '28042', 'Espagne');

INSERT INTO locality
VALUES ('New York', '10001', 'Etats-Unis');

INSERT INTO locality
VALUES ('Los Angeles', '90210', 'Etats-Unis');

INSERT INTO locality
VALUES ('Namur', '5000', 'Belgique');

-- ----------------- airport ---------------
INSERT INTO airport
VALUES('BXL','Bruxelles Airport', 'Bruxelles', '1000', 'Belgique');

INSERT INTO airport
VALUES('CRL','Charleroi Airport', 'Charleroi', '6000', 'Belgique');

INSERT INTO airport
VALUES('PRS_CG','Paris Charles de Gaulle Airport','Paris', '75000', 'France');

INSERT INTO airport
VALUES('PRS_OR','Paris Orly Airport', 'Paris', '75000', 'France');

INSERT INTO airport
VALUES('MRS','Marseille Airport', 'Marseille', '13000', 'France');

INSERT INTO airport
VALUES('LDN-HT','Londres Heatrow Airport', 'Londres', '250 329', 'Royaume-Uni');

INSERT INTO airport
VALUES('LDN-GW','Londres Gatwick Airport', 'Londres', '250 329', 'Royaume-Uni');

INSERT INTO airport
VALUES('ADM','Amsterdam Airport Schiphol', 'Amsterdam', '1109', 'Pays-Bas');

INSERT INTO airport
VALUES('MDD','Madrid Barajas Airport', 'Madrid', '28042', 'Espagne');

INSERT INTO airport
VALUES('NY-JFK','JFK International Airport', 'New York', '10001', 'Etats-Unis');

INSERT INTO airport
VALUES('NY-NWK',' Newark International Airport', 'New York', '10001', 'Etats-Unis');

INSERT INTO airport
VALUES('LAX','Los Angeles International Airport', 'Los Angeles', '90210', 'Etats-Unis');

-- ------------------------Passenger--------------------------------
INSERT INTO passenger
VALUES('MELPAR', 'Parache', 'Melvin',null, CONVERT(birth_date, '01-01-2000', 103), 'm', 'mp@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

INSERT INTO passenger
VALUES('MARHOW ', 'Howet', 'Marie',null, CONVERT(birth_date, '01-01-2000', 103), 'f', 'mh@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES('CHALIB', 'Libert', 'Charlotte',null, CONVERT(birth_date, '01-01-2000', 103), 'f', 'cl@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES('SARLOU', 'Louis', 'Sarah',null, CONVERT(birth_date, '01-01-2000', 103), 'f', 'sl@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES('STESER', 'Sermeus', 'Steven',null, CONVERT(birth_date, '01-01-2000', 103), 'm', 'ss@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

INSERT INTO passenger
VALUES('ELINYS', 'Nyssens', 'Elisabeth',null, CONVERT(birth_date, '01-01-2000', 103), 'f', 'en@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES('GUITUR', 'Turpin', 'Guillaume',null, CONVERT(birth_date, '01-01-2000', 103), 'm', 'gt@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES('JULHAN', 'Hanquet', 'Julien',null, CONVERT(birth_date, '01-01-2000', 103), 'm', 'jh@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

--  --------------------flight --------------------------------
INSERT INTO flight
VALUES(123, CONVERT(departure_date, '01-01-2000', 103), CONVERT(departure_hour,'19:30:00',102), CONVERT(expected_arrival_date, '01-01-2000', 103), CONVERT(expected_arrival_hour,'19:30:00',102),750.0,'PRS_CG','NY-JFK',1);

