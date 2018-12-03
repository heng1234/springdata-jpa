-- create database db_example DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
USE db_example

 CREATE TABLE `user` (
	`id` INT (11) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR (50) NOT NULL,
	`email` VARCHAR (200) NOT NULL,
	PRIMARY KEY (`id`)
)



-- 创建存储过程

create procedure pluslinout(IN arg int, OUT res int)
BEGIN
select (arg+10) into res;
END