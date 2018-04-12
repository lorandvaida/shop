
insert into supplier(name) values
('Altex'),
('Emag'),
('Domo');

insert into customer(first_name,last_name,username) values
('Lorand','Vaida','LorandVaida'),
('Gheroghe','Reman','GheorgheReman'),
('Alex','Pop','AlexPop');

insert into location(name,address_country,address_city,address_county,address_street) VALUES
('Cluj','Romania','Cluj-Napoca','Cluj','Aleea Gârbău'),
('Bistrita','Romania','Bistrita','Bistrita-Nasaud','Strada Rodnei'),
('Oradea','Romania','Oradea','Bihor','Strada Republicii'),
('Bucuretsi','Romania','Bucuretsi','Bucuretsi','Strada Doamnei');

insert into product_category(name,description) VALUES
('Telefoane','telefoane'),
('Electrocasnice','electrocasnice'),
('Fashion','fashion'),
('AutoMoto','auto moto'),
('PC','calculatoare');

insert into products(name,description,price,weight,product_category,supplier) VALUES
('Samsung','S8',1999,0.200,1,1),
('Iphone','6s',3599,0.200,1,2),
('Radio-Cd','Mp3 pt masina',250,1,4,3),
('Beko','Frigider',1999,40,2,1),
('Ram','DDR4',1599,0.200,5,1),
('Tricou','Marimea L',99,0.200,3,3);


insert into stock(product_id,location_id,quantity) VALUES
(1,1,20),
(2,1,30),
(3,2,9),
(4,3,10),
(5,1,15),
(6,4,8);
