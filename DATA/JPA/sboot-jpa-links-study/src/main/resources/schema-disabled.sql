create table EMPLOYEE
(
    ID  bigint  not null AUTO_INCREMENT,
    NAME varchar(100) not null,
    PRIMARY KEY (ID)
);

create table DEPARTMENT
(
    ID  bigint  not null AUTO_INCREMENT,
    NAME varchar(100) not null,
    BOSS_ID bigint,
    PARENT_DEP bigint,
    PRIMARY KEY (ID)
);