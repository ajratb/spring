--<ScriptOptions statementTerminator=";"/>

--ALTER TABLE courses DROP CONSTRAINT courses_pkey IF EXISTS;

DROP TABLE people IF EXISTS;

CREATE TABLE people (
		id IDENTITY NOT NULL PRIMARY KEY,-- BIGINT auto_increment,
		age INT,
		first_name TEXT(2147483647),
		last_name TEXT(2147483647)
	);

--ALTER TABLE courses ADD CONSTRAINT courses_pkey PRIMARY KEY (c_no);
--insert into courses(c_no, title, hours) values('005','Math', 245);
--insert into courses(c_no, title, hours) values('006','Algebra', 145);
--insert into courses(c_no, title, hours) values('007','Chemistry', 45);
--insert into courses(c_no, title, hours) values('010','IT', 154);


