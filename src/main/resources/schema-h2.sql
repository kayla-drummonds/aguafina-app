CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  role varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE customers (
  id int NOT NULL AUTO_INCREMENT,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  phone varchar(255) DEFAULT NULL,
  address varchar(255) DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  zip_code varchar(255) DEFAULT NULL,
  user_id int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKrh1g1a20omjmn6kurd35o3eit (user_id),
  CONSTRAINT FKrh1g1a20omjmn6kurd35o3eit FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE employees (
  id int NOT NULL AUTO_INCREMENT,
  address varchar(255) DEFAULT NULL,
  city varchar(255) DEFAULT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  state varchar(255) DEFAULT NULL,
  zip_code varchar(255) DEFAULT NULL,
  user_id int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FK69x3vjuy1t5p18a5llb8h2fjx (user_id),
  CONSTRAINT FK69x3vjuy1t5p18a5llb8h2fjx FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE orders (
  id int NOT NULL AUTO_INCREMENT,
  creation_date datetime DEFAULT NULL,
  product varchar(255) DEFAULT NULL,
  total double DEFAULT NULL,
  customer_id int DEFAULT NULL,
  employee_id int DEFAULT NULL,
  quantity int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKpxtb8awmi0dk6smoh2vp1litg (customer_id),
  KEY FKfhl8bv0xn3sj33q2f3scf1bq6 (employee_id),
  CONSTRAINT FKfhl8bv0xn3sj33q2f3scf1bq6 FOREIGN KEY (employee_id) REFERENCES employees (id),
  CONSTRAINT FKpxtb8awmi0dk6smoh2vp1litg FOREIGN KEY (customer_id) REFERENCES customers (id)
);