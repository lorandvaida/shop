DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS location;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS order_detail;
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS stock;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_privilege;
DROP TABLE IF EXISTS role_privileges;
DROP TABLE IF EXISTS user_roles;

CREATE TABLE IF NOT EXISTS products (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(100),
  price DECIMAL,
  weight DOUBLE,
  product_category INTEGER,
  supplier INTEGER,
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
  first_name VARCHAR(100) NOT NULL,
  last_name VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS stock (
  id INT NOT NULL AUTO_INCREMENT,
  product_id INT,
  location_id INT,
  quantity INT,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS order_detail (
  id INT NOT NULL AUTO_INCREMENT,
  order_id INT,
  product_id INT,
  quantity INT,
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS location (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  address_country VARCHAR(100),
  address_city VARCHAR(100),
  address_county VARCHAR(100),
  address_street VARCHAR(100),
  PRIMARY KEY (id));

CREATE TABLE IF NOT EXISTS orders (
  id INT NOT NULL AUTO_INCREMENT,
  shipped_from INT,
  customer_id INT,
  address_country VARCHAR(100),
  address_city VARCHAR(100),
  address_county VARCHAR(100),
  address_street VARCHAR(100),
  PRIMARY KEY (id));
