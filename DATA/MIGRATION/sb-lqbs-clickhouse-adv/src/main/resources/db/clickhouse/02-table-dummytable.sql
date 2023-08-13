--liquibase formatted sql
--changeset johndoe:create-dummy-table

CREATE TABLE tutorial.dummytable
(

    `WatchID` UInt64,

    `JavaEnable` UInt8,

    `Title` String
)
ENGINE = MergeTree
ORDER BY WatchID
SETTINGS index_granularity = 8192;