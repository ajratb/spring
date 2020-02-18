DROP TABLE quote IF EXISTS;
CREATE TABLE quote(
    id SERIAL, 
    isin CHAR(12), 
    bid DECIMAL(20,4) , 
    ask DECIMAL(20,4),
--  CHECK (ask >= bid),
    elvl DECIMAL(20,4)
);

ALTER TABLE
quote
ADD CONSTRAINT TEST_CHECK
CHECK ( bid > ask );

-- insert into quote(isin, bid, ask) values('cronus', 45, 9.21948);