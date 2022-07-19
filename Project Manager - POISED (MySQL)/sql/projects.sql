create database if not exists poisePMS;
use poisePMS;
create table project (PROJ_NUM varchar (5),PROJ_NAME varchar(55),BUILD_TYPE char(55),PROJ_ADDRESS varchar(120),ERF_NUM varchar(10),TOTAL_FEE float,TOTAL_PAID float,TOTAL_DUE float,DEADLINE_YEAR int(4),DEADLINE_MONTH int(2),DEADLINE_DAY int(2),FINALISED char(5),CUST_ID varchar(5),ARCH_ID varchar(5),PROJ_MAN_ID varchar(5),STRUC_ENG_ID varchar(5));
insert into project values("1","Jaden","House","10 Bell road","12345",6500000,500000,TOTAL_FEE - TOTAL_PAID,2024,11,2,"False","1","1","1","1");
insert into project values("2","Mavis","Appartment","12 Bell road","98766",8500000,678906,TOTAL_FEE - TOTAL_PAID,2023,2,4,"False",PROJ_NUM,PROJ_NUM,PROJ_NUM,PROJ_NUM);

create table customer (CUST_ID varchar(5),CUST_FNAME varchar(55),CUST_LNAME varchar(55),CUST_CELLNUM varchar(10),CUST_EMAIL varchar(120),CUST_ADDRESS varchar(120));
insert into customer values("1","Carl","Davies","0878987676","daviescarl@gmail.com","12 Raily Road");
insert into customer values("2","Hayden","Williams","0987672345","williamshayden@gmail.com","13 Red Lane");

create table architect (ARCH_ID varchar(5),ARCH_NAME varchar(55),ARCH_CELLNUM varchar(10),ARCH_EMAIL varchar(120),ARCH_ADDRESS varchar(120));
insert into architect values("1","Dennis Parker","0867567654","parkerD@gmail.com","12 Blue Road");
insert into architect values("2","Steve Garner","0987342561","stevieG@icloud.com","14 Green Avenue");

create table project_manager(PROJ_MAN_ID varchar(5),PROJ_MAN_NAME varchar(55),PROJ_MAN_CELLNUM varchar(10),PROJ_MAN_EMAIL varchar(120),PROJ_MAN_ADDRESS varchar(120));
insert into project_manager values("1","Mason Mount","0231425365","mount@chelsea.com","122 blue lane");
insert into project_manager values("2","Jadon Sancho","0564787653","sancho@united.com","25 old trafford");

create table structural_engineer(STRUC_ENG_ID varchar(5),STRUC_ENG_NAME varchar(55),STRUC_ENG_CELLNUM varchar(10),STRUC_ENG_EMAIL varchar(120),STRUC_ENG_ADDRESS varchar(120));
insert into structural_engineer values("1","Jesse Lingard","0546764536","JLings@united.com","23 old trafford");
insert into structural_engineer values("2","Paul Pogba","0934527812","pogba@juvetus.com","16 Juve Road");


SELECT * FROM customer;




