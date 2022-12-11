INSERT INTO users (id,email,password,role) VALUES (
    (1,'jpowell0@hplussport.com','$2a$10$h4hQfiPxKgfOKNsYxaYIFO.ztTcYed0y/rEFIx2IBBNNzHHecOMAm','ADMIN'),
    (2,'egarcia1@hplussport.com','$2a$10$K1OGUiEhp499D82doXRjK.t0SgoQpLgQ1.jdhig2MMqox.zk6uMZS','EMPLOYEE'),
    (3,'rdean2@hplussport.com','$2a$10$OLu/wN0I4diWy7jCLlAQPukCzJIq.Twsvi2/ksYBq6cpgrNPUtIuq','EMPLOYEE'),
    (4,'cshaw0@mlb.com','$2a$10$g0kSvWXRb7UNxP39N9DNae7rZ2lFSP3r8YFQXDlf22OX4Q.uj/DPG','CUSTOMER'),
    (5,'ecarr1@oracle.com','$2a$10$3v3MCMNcARtqAkA46vDkZOVAVXqZq9AZaFr7NqqfZFeiGTGgGT99.','CUSTOMER')
);

INSERT INTO employees (id,address,city,email,first_name,last_name,state,zip_code,user_id) VALUES (
    (1,'5 Jenifer Crossing','Lynchburg','jpowell0@hplussport.com','Jack','Powell','VA','24515',1),
    (2,'97 Vidon Alley','Manchester','egarcia1@hplussport.com','Emily','Garcia','NH','31050',2),
    (3,'2482 1st Road','Houston','rdean2@hplussport.com','Richard','Dean','TX','77228',3)
);

INSERT INTO customers (id,address,city,email,first_name,last_name,phone,state,zip_code,user_id) VALUES (
    (1,'8157 Longview Court','Seattle','cshaw0@mlb.com','Carol','Shaw','206-804-8771','WA','98121',4),
    (2,'3934 Petterle Trail','Austin','ecarr1@oracle.com','Elizabeth','Carr','512-187-2507','TX','78732',5)
);

INSERT INTO orders (id,creation_date,product,total,customer_id,employee_id,quantity) VALUES (
    (1,'2022-12-02 16:07:59','MWBLU32',12,1,1,3),
    (2,'2022-12-02 16:08:16','MWCRA32',16,2,2,4)
);
