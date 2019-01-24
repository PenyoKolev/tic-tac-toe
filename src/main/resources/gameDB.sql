DROP DATABASE IF EXISTS `game_db`;

CREATE DATABASE `game_db`;
USE `game_db`;

CREATE TABLE `players` (
	`player_id` int(5) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	`score` int(5) NOT NULL DEFAULT 1,
	PRIMARY KEY (`player_id`)
) ENGINE=InnoDB;

CREATE TABLE `games` (
	`game_id` int(5) NOT NULL AUTO_INCREMENT,
	`end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`result` enum('PLAYER_WIN','COMPUTER_WIN'),
	PRIMARY KEY (`game_id`)
) ENGINE=InnoDB;
