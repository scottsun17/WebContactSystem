create database webcontactsystem;

use webcontactsystem;

create table Contact (
	id int not null primary key auto_increment,
	firstName char(20) not null,
	lastName char(20) not null,
	gender char(1) not null,
	age tinyint not null,
	tel char(30),
	email char(30)
);