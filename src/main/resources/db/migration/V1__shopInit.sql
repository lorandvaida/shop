CREATE TABLE product (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL,
  price DECIMAL NOT NULL,
  weight DOUBLE NOT NULL,
  category INTEGER NOT NULL,
  supplier INTEGER NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE product_category (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE supplier (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE customer (
  id INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(100) NOT NULL,
  lastName VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE stock (
  id INT NOT NULL AUTO_INCREMENT,
  product INT NOT NULL,
  location INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE order_detail (
  id INT NOT NULL AUTO_INCREMENT,
  order INT NOT NULL,
  product INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE location (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  address_country VARCHAR(100) NOT NULL,
  address_city VARCHAR(100) NOT NULL,
  address_county VARCHAR(100) NOT NULL,
  address_street VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE order (
  id INT NOT NULL AUTO_INCREMENT,
  shipped_from INT NOT NULL,
  customer INT NOT NULL,
  address_country VARCHAR(100) NOT NULL,
  address_city VARCHAR(100) NOT NULL,
  address_county VARCHAR(100) NOT NULL,
  address_street VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));


insert into product (name, description, price, weight, category, supplier) values ('Sapun', 'Duru Duru', 1.99, 0.100, 1, 1);
insert into product (name, description, price, weight, category, supplier) values ('Baterie', 'Duracell', 5.5, 0.050, 1, 1);
insert into product (name, description, price, weight, category, supplier) values ('Frigider', 'Normandia', 1599, 35, 1, 1);