-- CREATE SCHEMA marvinairline;

USE marvinairline;

CREATE TABLE `airplane` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `airplane_type` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat_type` (
                             `name` varchar(20) NOT NULL,
                             `additional_price` int NOT NULL,
                             CHECK ( additional_price >= 0 ),
                             PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `number` int NOT NULL,
                        `column_letter` char(1) NOT NULL,
                        `seat_type` varchar(20) NOT NULL,
                        `airplane_id` int NOT NULL,
                        CHECK ( number >= 0 ),
                        PRIMARY KEY (`id`),
                        KEY `seat_type_idx` (`seat_type`),
                        KEY `airplane_seat_fk_idx` (`airplane_id`),
                        CONSTRAINT `airplane_seat_fk` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`id`),
                        CONSTRAINT `seat_type` FOREIGN KEY (`seat_type`) REFERENCES `seat_type` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `locality` (
                            `city` varchar(45) NOT NULL,
                            `post_code` varchar(5) NOT NULL,
                            `country` varchar(45) NOT NULL,
                            PRIMARY KEY (`city`,`post_code`,`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `airport` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) NOT NULL,
                           `city` varchar(45) NOT NULL,
                           `post_code` varchar(5) NOT NULL,
                           `country` varchar(45) NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `city_idx` (`city`,`post_code`,`country`),
                           CONSTRAINT `locality_airport` FOREIGN KEY (`city`, `post_code`, `country`) REFERENCES `locality` (`city`, `post_code`, `country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `flight` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `departure_date` date NOT NULL,
                          `departure_hour` varchar(15) NOT NULL,
                          `expected_arrival_date` date NOT NULL,
                          `expected_arrival_hour` varchar(15) NOT NULL,
                          `price` decimal(7,2) NOT NULL,
                          `airplane_id` int NOT NULL,
                          `departure_airport_id` int NOT NULL,
                          `arrival_airport_id` int NOT NULL,
                          CHECK ( price >= 0 ),
                          PRIMARY KEY (`id`),
                          KEY `airplane_fk_idx` (`airplane_id`),
                          KEY `departure_airport_fk_idx` (`departure_airport_id`),
                          KEY `arrival_airport_fk_idx` (`arrival_airport_id`),
                          CONSTRAINT `airplane_fk` FOREIGN KEY (`airplane_id`) REFERENCES `airplane` (`id`),
                          CONSTRAINT `arrival_airport_fk` FOREIGN KEY (`arrival_airport_id`) REFERENCES `airport` (`id`),
                          CONSTRAINT `departure_airport_fk` FOREIGN KEY (`departure_airport_id`) REFERENCES `airport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `stopover` (
                            `flight_id` int NOT NULL,
                            `airport_id` int NOT NULL,
                            `duration` int NOT NULL,
                            CHECK ( duration >= 0 ),
                            PRIMARY KEY (`flight_id`,`airport_id`),
                            KEY `airport_stopover_fk_idx` (`airport_id`),
                            CONSTRAINT `airport_stopover_fk` FOREIGN KEY (`airport_id`) REFERENCES `airport` (`id`),
                            CONSTRAINT `flight_stopover_fk` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `passenger` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `last_name` varchar(25) NOT NULL,
                             `first_name` varchar(25) NOT NULL,
                             `initial_middle_name` varchar(10) DEFAULT NULL,
                             `birth_date` date NOT NULL,
                             `email` varchar(60) NOT NULL,
                             `phone_number` varchar(20) NOT NULL,
                             `street_and_number` varchar(60) NOT NULL,
                             `city` varchar(45) NOT NULL,
                             `post_code` varchar(5) NOT NULL,
                             `country` varchar(45) NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `city_passenger_idx` (`city`,`post_code`,`country`),
                             CONSTRAINT `locality_passenger` FOREIGN KEY (`city`, `post_code`, `country`) REFERENCES `locality` (`city`, `post_code`, `country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `booking` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `date_booking` date NOT NULL,
                           `has_paid` bit(1) NOT NULL,
                           `luggage_weight` varchar(45) DEFAULT NULL,
                           `company_name` varchar(45) DEFAULT NULL,
                           `meal_type` varchar(15) NOT NULL,
                           `real_price` decimal(7,2) NOT NULL,
                           `flight_id` int NOT NULL,
                           `seat_id` int NOT NULL,
                           `passenger_id` int NOT NULL,
                           CHECK ( real_price >= 0 ),
                           PRIMARY KEY (`id`),
                           KEY `flight_fk_idx` (`flight_id`),
                           KEY `seat_booking_fk_idx` (`seat_id`),
                           KEY `passenger_booking_fk_idx` (`passenger_id`),
                           CONSTRAINT `flight_fk` FOREIGN KEY (`flight_id`) REFERENCES `flight` (`id`),
                           CONSTRAINT `passenger_booking_fk` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`id`),
                           CONSTRAINT `seat_booking_fk` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*
DROP TABLE booking;
DROP TABLE passenger;
DROP TABLE stopover;
DROP TABLE flight;
DROP TABLE seat;
DROP TABLE seat_type;
DROP TABLE airplane;
DROP TABLE airport;
DROP TABLE locality;
*/



