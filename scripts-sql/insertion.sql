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

--  --------------------flight --------------------------------
INSERT INTO flight (departure_date, departure_hour, expected_arrival_date, expected_arrival_hour, price, airplane_id, departure_airport_id, arrival_airport_id)
VALUES('2022-06-20', '16:00:00','2022-06-20','18:00:00', 750.0, 1, 3, 10);


