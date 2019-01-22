DROP DATABASE IF EXISTS `game_db`;

CREATE DATABASE `game_db`;
USE `game_db`;

CREATE TABLE `players` (
	`player_id` int(5) NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY (`player_id`)
) ENGINE=InnoDB;

CREATE TABLE `games` (
	`game_id` int(5) NOT NULL AUTO_INCREMENT,
	`player_id` int(5),
	`end_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`result` enum('win','lose'),
	PRIMARY KEY (`game_id`),
	FOREIGN KEY (`player_id`) REFERENCES `players`(`player_id`)
) ENGINE=InnoDB;
