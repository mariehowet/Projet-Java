CREATE SCHEMA marvinairline;

USE marvinairline;


select id, name, departure_date, expected_arrival_date
       from (select f.id, da.name, f.departure_date, f.expected_arrival_date
            from flight f
            inner join airport da on (f.departure_airport_id = da.id)
            inner join airplane a on (f.airplane_id = a.id)

            where f.arrival_airport_id = 10
            and f.departure_date between '2022-04-10' and '2022-06-21') flights
        inner join
;



select f.id, da.name, f.departure_date, f.expected_arrival_date
from flight f
         inner join airport da on (f.departure_airport_id = da.id)
         inner join airplane a on (f.airplane_id = a.id)

where f.arrival_airport_id = 10
  and f.departure_date between '2022-04-10' and '2022-06-21';



select count(*)
from seat s
where s.airplane_id = 1;

select count(*)
from seat s
inner join booking b on s.id = b.seat_id
where b.flight_id = 1;


select count(*)
from seat s
where s.airplane_id = 1
in (select *
        from seat s
        inner join booking b on s.id = b.seat_id
        where b.flight_id = 1);


select f.id, da.name, f.departure_date, f.expected_arrival_date
from booking b
        inner join flight f on b.flight_id = f.id
         inner join airport da on (f.departure_airport_id = da.id)
         inner join airplane a on (f.airplane_id = a.id)
    inner join seat s on b.seat_id = s.id
where count() in (select * from seat where airplane_id = a.id);






select s.id, s.number, s.column_letter from seat s inner join airplane a on(s.airplane_id = a.id)
                inner join seat_type st on(st.name = s.seat_type)
                inner join flight f on (f.airplane_id = s.airplane_id)
                where st.name = ? and f.id = ? and s.id not in (select seat_id from booking) ;







CREATE TABLE `airplane` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `airplane_type` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat_type` (
                             `name` varchar(20) NOT NULL,
                             `additional_price` int NOT NULL,
                             PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `seat` (
                        `id` int NOT NULL AUTO_INCREMENT,
                        `number` int NOT NULL,
                        `column_letter` char(1) NOT NULL,
                        `seat_type` varchar(20) NOT NULL,
                        `airplane_id` int NOT NULL,
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
USE marvinairline;


-- ----------------- airplane---------------
INSERT INTO airplane (airplane_type)
VALUES ('AIRBUS A380');

INSERT INTO airplane (airplane_type)
VALUES ( 'BOEING 707');

-- ----------------- seattype---------------

INSERT INTO seat_type
VALUES ('Economic', 30);

INSERT INTO seat_type
VALUES ('Business', 100);

INSERT INTO seat_type
VALUES ('First', 200);
-- ----------------- seat---------------

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES  (1, 'A', 'First', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 1, 'B', 'First', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 1, 'C','First', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 1, 'D','First', 1);
INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 1, 'E','First', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES (1, 'F','First', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'A', 'Business', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'B', 'Business', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'C', 'Business', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'D', 'Business', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'E', 'Business', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'F', 'Business', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'A', 'Business', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'B', 'Economic', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'C', 'Economic', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'D', 'Economic', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'E', 'Economic', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'F', 'Economic', 1);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES  (1, 'A', 'First', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 1, 'B', 'First', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 1, 'C','First', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 1, 'D','First', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 1, 'E','First', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES (1, 'F','First', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'A', 'Business', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'B', 'Business', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'C', 'Business', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'D', 'Business', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'E', 'Business', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 2, 'F', 'Business', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'A', 'Economic', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'B', 'Economic', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'C', 'Economic', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'D', 'Economic', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'E','Economic', 2);

INSERT INTO seat (number, column_letter, seat_type, airplane_id)
VALUES ( 3, 'F','Economic', 2);

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
VALUES ('Londres', '25029', 'Royaume-Uni');

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
INSERT INTO airport (name, city, post_code, country)
VALUES('Bruxelles Airport', 'Bruxelles', '1000', 'Belgique');

INSERT INTO airport (name, city, post_code, country)
VALUES('Charleroi Airport', 'Charleroi', '6000', 'Belgique');

INSERT INTO airport (name, city, post_code, country)
VALUES('Paris Charles de Gaulle Airport','Paris', '75000', 'France');

INSERT INTO airport (name, city, post_code, country)
VALUES('Paris Orly Airport', 'Paris', '75000', 'France');

INSERT INTO airport (name, city, post_code, country)
VALUES('Marseille Airport', 'Marseille', '13000', 'France');

INSERT INTO airport (name, city, post_code, country)
VALUES('Londres Heatrow Airport', 'Londres', '25029', 'Royaume-Uni');

INSERT INTO airport (name, city, post_code, country)
VALUES('Londres Gatwick Airport', 'Londres', '25029', 'Royaume-Uni');

INSERT INTO airport (name, city, post_code, country)
VALUES('Amsterdam Airport Schiphol', 'Amsterdam', '1109', 'Pays-Bas');

INSERT INTO airport (name, city, post_code, country)
VALUES('Madrid Barajas Airport', 'Madrid', '28042', 'Espagne');

INSERT INTO airport (name, city, post_code, country)
VALUES('JFK International Airport', 'New York', '10001', 'Etats-Unis');

INSERT INTO airport (name, city, post_code, country)
VALUES('Newark International Airport', 'New York', '10001', 'Etats-Unis');

INSERT INTO airport (name, city, post_code, country)
VALUES('Los Angeles International Airport', 'Los Angeles', '90210', 'Etats-Unis');


--  --------------------flight --------------------------------
INSERT INTO flight (departure_date, departure_hour, expected_arrival_date, expected_arrival_hour, price, airplane_id, departure_airport_id, arrival_airport_id)
VALUES('2022-06-20', '16:00:00','2022-06-20','18:00:00', 750.0, 1, 3, 10);

INSERT INTO flight (departure_date, departure_hour, expected_arrival_date, expected_arrival_hour, price, airplane_id, departure_airport_id, arrival_airport_id)
VALUES('2022-05-10', '14:00:00','2022-06-20','18:00:00', 600, 2, 1, 2);

-- -------------------- stopover ------------
INSERT INTO stopover (flight_id, airport_id, duration)
VALUES (1,7,200);

-- ------------------------Passenger--------------------------------
INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES('Parache', 'Melvin',null, '2000-01-01', 'mp@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES('Howet', 'Marie',null, '2000-01-01', 'mh@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES('Libert', 'Charlotte',null, '2000-01-01', 'cl@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES('Louis', 'Sarah',null, '2000-01-01','sl@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES('Sermeus', 'Steven',null, '2000-01-01', 'ss@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES('Nyssens', 'Elisabeth',null, '2000-01-01', 'en@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES('Turpin', 'Guillaume',null, '2000-01-01', 'gt@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger (last_name, first_name, initial_middle_name, birth_date, email, phone_number, street_and_number, city, post_code, country)
VALUES('Hanquet', 'Julien',null, '2000-01-01', 'jh@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

-- -------------------- booking ------------
INSERT INTO booking (date_booking, has_paid, luggage_weight, company_name, meal_type, real_price, flight_id, seat_id, passenger_id)
VALUES ('2022-05-09',true,'0 < 10 kg','Protellux','Poulet',1000.0,1,5,1);

INSERT INTO booking (date_booking, has_paid, luggage_weight, company_name, meal_type, real_price, flight_id, seat_id, passenger_id)
VALUES ('2022-05-10',false,'10 < 20 kg',null,'Porc',2000.0,2,13,1);
