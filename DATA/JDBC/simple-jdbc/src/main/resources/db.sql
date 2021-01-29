--<ScriptOptions statementTerminator=";"/>

--ALTER TABLE courses DROP CONSTRAINT courses_pkey IF EXISTS;

DROP TABLE courses IF EXISTS;

CREATE TABLE courses (
		c_no TEXT(2147483647) NOT NULL,
		title TEXT(2147483647),
		hours INT4
	);

--ALTER TABLE courses ADD CONSTRAINT courses_pkey PRIMARY KEY (c_no);
insert into courses(c_no, title, hours) values('Mahmud','Ali', 45);
insert into courses(c_no, title, hours) values('Mahmud','Ali', 45);
insert into courses(c_no, title, hours) values('Mahmud','Ali', 45);
insert into courses(c_no, title, hours) values('Mahmud','Ali', 45);


