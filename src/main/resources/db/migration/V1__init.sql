
/*
CREATE TABLE IF NOT EXISTS product (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL,
  price DECIMAL NOT NULL,
  weight DOUBLE NOT NULL,
  category INTEGER NOT NULL,
  supplier INTEGER NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS product_category (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS supplier (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS customer (
  id INT NOT NULL AUTO_INCREMENT,
  firstName VARCHAR(100) NOT NULL,
  lastName VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS stock (
  id INT NOT NULL AUTO_INCREMENT,
  product INT NOT NULL,
  location INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS order_detail (
  id INT NOT NULL AUTO_INCREMENT,
  order INT NOT NULL,
  product INT NOT NULL,
  quantity INT NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS location (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  address_country VARCHAR(100) NOT NULL,
  address_city VARCHAR(100) NOT NULL,
  address_county VARCHAR(100) NOT NULL,
  address_street VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS order (
  id INT NOT NULL AUTO_INCREMENT,
  shipped_from INT NOT NULL,
  customer INT NOT NULL,
  address_country VARCHAR(100) NOT NULL,
  address_city VARCHAR(100) NOT NULL,
  address_county VARCHAR(100) NOT NULL,
  address_street VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

insert into supplier (name) values ('supplier1');
insert into supplier (name) values ('supplier2');
insert into supplier (name) values ('supplier3');

insert into product_category (name, description) values ('category1','productcategoryDecription1');
insert into product_category (name, description) values ('category2','productcategoryDecription2');
insert into product_category (name, description) values ('category3','productcategoryDecription3');
*/