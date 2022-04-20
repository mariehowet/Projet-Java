CREATE SCHEMA marvinairline;

USE marvinairline;

CREATE TABLE `airplane` (
                            `id` int NOT NULL,
                            `airplane_type` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat_type` (
                             `name` varchar(20) NOT NULL,
                             `additional_percent` decimal(3,2) NOT NULL,
                             PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat` (
                        `id` int NOT NULL,
                        `number` int NOT NULL,
                        `column_letter` char(1) NOT NULL,
                        `airplane` int NOT NULL,
                        `seat_type` varchar(20) NOT NULL,
                        PRIMARY KEY (`id`),
                        KEY `airplane_idx` (`airplane`),
                        KEY `seat_type_idx` (`seat_type`),
                        CONSTRAINT `airplane_seat` FOREIGN KEY (`airplane`) REFERENCES `airplane` (`id`),
                        CONSTRAINT `seat_type` FOREIGN KEY (`seat_type`) REFERENCES `seat_type` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


CREATE TABLE `locality` (
                            `city` varchar(45) NOT NULL,
                            `post_code` varchar(5) NOT NULL,
                            `country` varchar(45) NOT NULL,
                            PRIMARY KEY (`city`,`post_code`,`country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `airport` (
                           `code` int NOT NULL,
                           `name` varchar(45) NOT NULL,
                           `city` varchar(45) NOT NULL,
                           `post_code` varchar(5) NOT NULL,
                           `country` varchar(45) NOT NULL,
                           PRIMARY KEY (`code`),
                           KEY `city_idx` (`city`,`post_code`,`country`),
                           CONSTRAINT `locality_airport` FOREIGN KEY (`city`, `post_code`, `country`) REFERENCES `locality` (`city`, `post_code`, `country`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `flight` (
                          `id` int NOT NULL,
                          `departure_date` date NOT NULL,
                          `departure_hour` time NOT NULL,
                          `expected_arrival_date` date NOT NULL,
                          `expected_arrival_hour` time NOT NULL,
                          `price` decimal(7,2) NOT NULL,
                          `departure_airport` int NOT NULL,
                          `arrival_airport` int NOT NULL,
                          `airplane` int NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `departure_airport_idx` (`departure_airport`),
                          KEY `arrival_airport_idx` (`arrival_airport`),
                          KEY `airplane_idx` (`airplane`),
                          CONSTRAINT `airplane` FOREIGN KEY (`airplane`) REFERENCES `airplane` (`id`),
                          CONSTRAINT `arrival_airport` FOREIGN KEY (`arrival_airport`) REFERENCES `airport` (`code`),
                          CONSTRAINT `departure_airport` FOREIGN KEY (`departure_airport`) REFERENCES `airport` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `stopover` (
                            `flight` int NOT NULL,
                            `airport` int NOT NULL,
                            `duration` int NOT NULL,
                            PRIMARY KEY (`flight`,`airport`),
                            KEY `airport_stopover_idx` (`airport`),
                            CONSTRAINT `airport_stopover` FOREIGN KEY (`airport`) REFERENCES `airport` (`code`),
                            CONSTRAINT `flight_stopover` FOREIGN KEY (`flight`) REFERENCES `flight` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `passenger` (
                             `id` int NOT NULL,
                             `last_name` varchar(25) NOT NULL,
                             `first_name` varchar(25) NOT NULL,
                             `initial_middle_name` varchar(45) DEFAULT NULL,
                             `birth_date` date NOT NULL,
                             `gender` varchar(10) NOT NULL,
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

CREATE TABLE `seat_booked` (
                               `flight` int NOT NULL,
                               `seat` int NOT NULL,
                               PRIMARY KEY (`flight`,`seat`),
                               KEY `seat_seat_booked_idx` (`seat`),
                               CONSTRAINT `flight_seat_booked` FOREIGN KEY (`flight`) REFERENCES `flight` (`id`),
                               CONSTRAINT `seat_seat_booked` FOREIGN KEY (`seat`) REFERENCES `seat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `booking` (
                           `id` int NOT NULL,
                           `date` date NOT NULL,
                           `has_paid` tinyint(1) NOT NULL,
                           `luggage_weight` int DEFAULT NULL,
                           `company_name` varchar(45) DEFAULT NULL,
                           `meal_type` varchar(15) NOT NULL,
                           `real_price` decimal(7,2) NOT NULL,
                           `passenger` int NOT NULL,
                           `seat` int NOT NULL,
                           `flight` int NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `seat_booking_idx` (`seat`),
                           KEY `flight_booking_idx` (`flight`),
                           KEY `passenger_booking_idx` (`passenger`),
                           CONSTRAINT `flight_booking` FOREIGN KEY (`flight`) REFERENCES `seat_booked` (`flight`),
                           CONSTRAINT `passenger_booking` FOREIGN KEY (`passenger`) REFERENCES `passenger` (`id`),
                           CONSTRAINT `seat_booking` FOREIGN KEY (`seat`) REFERENCES `seat_booked` (`seat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;




