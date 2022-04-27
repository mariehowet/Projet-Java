-- ----------------- airplane---------------
INSERT INTO airplane
VALUES (1, 'AIRBUS A380');

INSERT INTO airplane
VALUES (2, 'BOEING 707');

-- ----------------- seattype---------------

INSERT INTO seat_type
VALUES ('Classe économique', 0.1);

INSERT INTO seat_type
VALUES ('Classe business', 0.3);

INSERT INTO seat_type
VALUES ('Première classe', 0.5);
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
INSERT INTO airport
VALUES(1,'Bruxelles Airport', 'Bruxelles', '1000', 'Belgique');

INSERT INTO airport
VALUES(2,'Charleroi Airport', 'Charleroi', '6000', 'Belgique');

INSERT INTO airport
VALUES(3,'Paris Charles de Gaulle Airport','Paris', '75000', 'France');

INSERT INTO airport
VALUES(4,'Paris Orly Airport', 'Paris', '75000', 'France');

INSERT INTO airport
VALUES(5,'Marseille Airport', 'Marseille', '13000', 'France');

INSERT INTO airport
VALUES(6,'Londres Heatrow Airport', 'Londres', '25029', 'Royaume-Uni');

INSERT INTO airport
VALUES(7,'Londres Gatwick Airport', 'Londres', '25029', 'Royaume-Uni');

INSERT INTO airport
VALUES(8,'Amsterdam Airport Schiphol', 'Amsterdam', '1109', 'Pays-Bas');

INSERT INTO airport
VALUES(9,'Madrid Barajas Airport', 'Madrid', '28042', 'Espagne');

INSERT INTO airport
VALUES(10,'JFK International Airport', 'New York', '10001', 'Etats-Unis');

INSERT INTO airport
VALUES(11,' Newark International Airport', 'New York', '10001', 'Etats-Unis');

INSERT INTO airport
VALUES(12,'Los Angeles International Airport', 'Los Angeles', '90210', 'Etats-Unis');

-- ------------------------Passenger--------------------------------
INSERT INTO passenger
VALUES(1, 'Parache', 'Melvin',null, '2000-01-01', 'm', 'mp@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

INSERT INTO passenger
VALUES(2, 'Howet', 'Marie',null, '2000-01-01', 'f', 'mh@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES(3, 'Libert', 'Charlotte',null, '2000-01-01', 'f', 'cl@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES(4, 'Louis', 'Sarah',null, '2000-01-01', 'f', 'sl@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES(5, 'Sermeus', 'Steven',null, '2000-01-01', 'm', 'ss@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

INSERT INTO passenger
VALUES(6, 'Nyssens', 'Elisabeth',null, '2000-01-01', 'f', 'en@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES(7, 'Turpin', 'Guillaume',null, '2000-01-01', 'm', 'gt@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');


INSERT INTO passenger
VALUES(8, 'Hanquet', 'Julien',null, '2000-01-01', 'm', 'jh@gmail.com','0478955465', 'Rue du truc, 10', 'Namur', '5000', 'Belgique');

--  --------------------flight --------------------------------
INSERT INTO flight
VALUES(123, '2000-01-01', '2000-01-01', '2000-01-01', '2000-01-01',750.0,'PRS_CG','NY-JFK',1);


DELETE FROM `seat_type`;
