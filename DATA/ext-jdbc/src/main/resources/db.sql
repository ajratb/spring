DROP TABLE people IF EXISTS;
CREATE TABLE people(
    id SERIAL, 
    first_name VARCHAR(255), 
    last_name VARCHAR(255),
    age INT,
    range DECIMAL(20,4)
);

insert into people(first_name, last_name, age, range) values('Mahmud','Ali', 45, 9.21948);