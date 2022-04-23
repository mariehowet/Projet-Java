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
                           `code` INT NOT NULL,
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
                          `departure_airport_code` INT NOT NULL,
                          `arrival_airport_code` INT NOT NULL,
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
                            `airport_code` INT NOT NULL,
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

-- mettre insert




