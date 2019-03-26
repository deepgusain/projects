--liquibase formatted sql

--changeset deepak:11
CREATE TABLE catable (ID VARCHAR(19) NOT NULL, NAME VARCHAR(20) NULL)

--changeset deepak:21
alter table catable add column newfield3 varchar(20);
--rollback drop table catable;