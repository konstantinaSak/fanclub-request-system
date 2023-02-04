CREATE DATABASE IF NOT EXISTS `fanclub_db`;
USE `fanclub_db`;


DROP TABLE IF EXISTS `fan`;
DROP TABLE IF EXISTS `request_gga`;
DROP TABLE IF EXISTS `request_elas`;
DROP TABLE IF EXISTS `fanclub`;
DROP TABLE IF EXISTS `gga`;
DROP TABLE IF EXISTS `elas`;
DROP TABLE IF EXISTS `admin`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `authorities`;



CREATE TABLE `fanclub` (
			`ID` int(11) NOT NULL AUTO_INCREMENT,
			`name` varchar(100) DEFAULT NULL,
			`username` varchar(45) NOT NULL UNIQUE,
			`password` varchar(100) DEFAULT NULL,
			`leader`varchar(100) DEFAULT NULL,
			`sports_club` varchar(100) DEFAULT NULL,
			`address` varchar(100) DEFAULT NULL,
			`email` varchar(45) DEFAULT NULL,
			`date_foundation` varchar(11) NOT NULL,
			PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `fanclub`(`name`, `username`, `password`, `leader`, `sports_club`, `address`, `email`, `date_foundation`) VALUES
('Olympiakos Fans','OSFP','$2a$12$BIdNtnjtXkWks7wY2JXrvufBaG7gTl4R2F.GuwTRxyofvSkpUPMxy','Kostas Kostopoulos','Olympiakos F.C.','Karaiskaki 7','osfp@gmail.con','2012-07-07'),
('Panathinaikos Fans','PAO','$2a$12$qzI0WmGSAd8sXJ.OvMkla.FxqcAm3fSnTjumO6ut01YaG25TxSzC6','Giannis Giannopoulos','Panathinaikos F.C.','Alexandras 13','pao@gmail.con','2013-03-13'),
('AEK Fans Club Athens','AEK','$2a$12$1ztKnzRU2oHSgbDkvsohzemmWv.AcKXiZZ6iFrYDqEzKcVjQ4Oj22','Petros Petroglou','AEK F.C.','Agia Sofia 21','aek@gmail.con','2008-12-21'),
('PAOK Fans Salonika','PAOK','$2a$12$A0HqbWGL.N5bnZ0lOVHNWO1h5rFV5KWdqU6VZk0KFjdNdnvBE8SOG','Georgios Georgiou','PAOK F.C.','Toumpa 4','paok@gmail.con','2011-04-04');



CREATE TABLE `fan` (
			`ID` int(11) NOT NULL AUTO_INCREMENT,
			`first_name` varchar(45) DEFAULT NULL,
                       `last_name` varchar(45) DEFAULT NULL,
                       `birth_date` varchar(11) NOT NULL,
			`AFM` int(9) NOT NULL,
			`fanclub_ID` int(11) NOT NULL,
			PRIMARY KEY (`ID`),
			CONSTRAINT `fk_fan_fanclub` FOREIGN KEY (`fanclub_ID`) REFERENCES `fanclub` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `fan`(`first_name`, `last_name`, `birth_date`, `AFM`, `fanclub_ID`) VALUES
('Kostas','Kostopoulos','1981-04-12',194833675,1),
('Christos','Sotiriadis','1987-09-18',293012529,1),
('Loukas','Arabatzis','1990-03-10',593918834,1),
('Anastasios','Boulellis','1984-07-07',503359612,1),
('Thanasis','Harriadis','1991-08-22',142638943,1),
('Giannis','Giannopoulos','1979-05-23',395844567,2),
('Iasonas','Andretsis','1989-04-03',229448651,2),
('Vasilis','Valleas','1981-05-13',294533825,2),
('Dimitrios','Gabridis','1994-07-06',598323485,2),
('Lazaros','Anastakos','1985-08-17',594322695,2),
('Petros','Petroglou','1977-01-15',496512747,3),
('Fotis','Goutas','1987-09-18',293012529,3),
('Giannis','Bouleas','1987-09-18',293012529,3),
('Sotiris','Zenoulis','1979-06-07',478124742,3),
('Alexandros','Kontelis','1982-02-08',678425725,3),
('Georgios','Georgiou','1975-01-19',495832854,4),
('Stefanos','Michaelakos','1985-02-09',294833475,4),
('Grigoris','Spirelis','1983-07-14',485833283,4),
('Panagiotis','Loratos','1976-04-18',384721944,4),
('Spyros','Simatos','1982-07-07',493822264,4);



CREATE TABLE `gga` (
			`ID` int(11) NOT NULL,
			`address` varchar(100) DEFAULT NULL,
			`email` varchar(45) DEFAULT NULL,
			`username` varchar(45) NOT NULL UNIQUE,
			`password` varchar(100) DEFAULT NULL,
			PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `gga` VALUES
(771,'El. Venizelou 49','gga@gmail.com','GGA','$2a$12$UWeeMsx9Q7WTMua4w..S6OZp7zORlbD9IL5oYZyeous9.1IncyVT2');



CREATE TABLE `elas` (
			`ID` int(11) NOT NULL AUTO_INCREMENT,
			`department` varchar(100) DEFAULT NULL,
			`address` varchar(100) DEFAULT NULL,
			`email` varchar(45) DEFAULT NULL,
			`username` varchar(45) NOT NULL UNIQUE,
			`password` varchar(100) DEFAULT NULL,
			PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `elas`(`department`, `address`, `email`, `username`, `password`) VALUES
('Zografou','Hr. Politexniou 14','at.zografou@gmail.com','ATZOG','$2a$12$wtvNde2CY7.ljzDKIj7TxOJXLVKy9pCGX5uD2/0R.QXyfw759qbsC'),
('Syntagmatos','Mimnermou 6','at.syntagmatos@gmail.com','ATSYNT','$2a$12$39KdqaUMU.ByWgqRXaaFuOYZy.qCRHHfGisE2aE5sZaaSoA8nXN9O'),
('Kallitheas','Laskaridou 44','at.kallitheas@gmail.com','ATKAL','$2a$12$9wf1U/uF0n6kKEsHBh2IfuZgmApfyyWQjkCpPOlmootstovR6S/Te');



CREATE TABLE IF NOT EXISTS `admin` (
			`ID` int(11) NOT NULL AUTO_INCREMENT,
                       `username` varchar(45) NOT NULL UNIQUE,
                       `password` varchar(100) DEFAULT NULL,
                       PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `admin`(`username`, `password`) VALUES
('ADMIN1','$2a$12$.4.L6qPrlR157VkZvuMZMuyUXwNiTDnCfir3t6qkw94CoXA8obGIS'),
('ADMIN2','$2a$12$WsNSqAXlqP5jxxImR3AzAOfmvtTpy4IWB1DPYA85t2M5Kk67/7eu2');



CREATE TABLE IF NOT EXISTS `request_gga` (
			`ID` int(11) NOT NULL AUTO_INCREMENT,
			`date` date NOT NULL,
			`text` varchar(3000) DEFAULT NULL,
			`state` varchar(45) NOT NULL,
			`fanclub_ID` int(11) NOT NULL,
			`GGA_ID` int(11) NOT NULL,
			PRIMARY KEY (`ID`),
			CONSTRAINT `fk_requestGGA_fanclub` FOREIGN KEY (`fanclub_ID`) REFERENCES `fanclub` (`ID`),
                       CONSTRAINT `fk_requestGGA_GGA` FOREIGN KEY (`GGA_ID`) REFERENCES `gga` (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `request_gga`(`date`, `text`, `state`, `fanclub_ID`, `GGA_ID`) VALUES
('2023-01-09','From PANATHINAIKOS Fan Club to GGA','Pending',2,771),
('2022-12-28','From AEK Fan Club to GGA','Pending',3,771),
('2023-01-06','From PAOK Fan Club to GGA','Pending',4,771),
('2023-01-15','From OSFP Fan Club to GGA','Pending',1,771);



CREATE TABLE IF NOT EXISTS `request_elas` (
			`ID` int(11) NOT NULL AUTO_INCREMENT,
			`date` date NOT NULL,
			`text` varchar(3000) DEFAULT NULL,
			`state` varchar(45) NOT NULL,
			`fanclub_ID` int(11) NOT NULL,
			`ELAS_ID` int(11) NOT NULL,
			PRIMARY KEY (`ID`),
			CONSTRAINT `fk_requestELAS_fanclub` FOREIGN KEY (`fanclub_ID`) REFERENCES `fanclub` (`ID`),
                       CONSTRAINT `fk_requestELAS_ELAS` FOREIGN KEY (`ELAS_ID`) REFERENCES `elas` (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO `request_elas`(`date`, `text`, `state`, `fanclub_ID`, `ELAS_ID`) VALUES
('2023-01-10','From OSFP Fan Club to AT Syntagmatos','Pending',1,2),
('2023-01-03','From PANATHINAIKOS Fan Club to AT Zografou','Pending',2,1),
('2022-12-27','From AEK Fan Club to AT Kallitheas','Pending',3,3),
('2023-01-12','From PAOK Fan Club to AT Syntagmatos','Pending',4,2),
('2023-12-29','From PAOK Fan Club to AT Kallitheas','Pending',4,3);



CREATE TABLE IF NOT EXISTS `user` (
			`username` varchar(45) NOT NULL,
			`password` varchar(100) DEFAULT NULL,
			`enabled` tinyint(1) NOT NULL,
			PRIMARY KEY (`username`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `user` SELECT username, password, 1 FROM `fanclub`;
INSERT INTO `user` SELECT username, password, 1 FROM `gga`;
INSERT INTO `user` SELECT username, password, 1 FROM `elas`;
INSERT INTO `user` SELECT username, password, 1 FROM `admin`;



CREATE TABLE IF NOT EXISTS `authorities` (
			`username` varchar(45) NOT NULL,
			`authority` varchar(45) NOT NULL,
			PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `authorities` (`username`, `authority`) VALUES
('OSFP','ROLE_FANCLUB'),
('PAO','ROLE_FANCLUB'),
('AEK','ROLE_FANCLUB'),
('PAOK','ROLE_FANCLUB'),
('GGA','ROLE_GGA'),
('ATZOG','ROLE_POLICE'),
('ATSYNT','ROLE_POLICE'),
('ATKAL','ROLE_POLICE'),
('ADMIN1','ROLE_ADMIN'),
('ADMIN2','ROLE_ADMIN');




