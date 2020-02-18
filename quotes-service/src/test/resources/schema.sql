DROP TABLE quote IF EXISTS;
CREATE TABLE quote(
    id SERIAL, 
    isin CHAR(12), 
    bid DECIMAL(20,4),
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

insert into quote(isin, bid, ask, elvl) values('test12312355', 45, 45.333,2442347.45645);