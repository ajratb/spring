DROP TABLE quote IF EXISTS;
CREATE TABLE quote(
    id SERIAL, 
    isin CHAR(12), 
    bid DECIMAL(20,4) , 
    ask DECIMAL(20,4),
    elvl DECIMAL(20,4)
);

ALTER TABLE
quote
ADD CONSTRAINT BID_CHECK
CHECK ( bid < ask );

ALTER TABLE
quote
ADD CONSTRAINT ISIN_CHECK
CHECK ( LENGTH(isin) = 12 );

-- insert into quote(isin, bid, ask, elvl) values('test12312355', 45, 45.333, 2442347.45645);
-- 
-- insert into quote(isin, bid, ask, elvl) values('111111111111', 120, 120.6, 120);
-- insert into quote(isin, bid, ask, elvl) values('111111111111', 99, 99.01, 99);
-- insert into quote(isin, bid, ask, elvl) values('222222222222', 78.9, 81, 78.9);
-- insert into quote(isin, bid, ask) values('cronus', 45, 9.21948);