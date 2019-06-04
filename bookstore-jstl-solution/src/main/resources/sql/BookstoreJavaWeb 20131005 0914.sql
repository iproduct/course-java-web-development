-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.16


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema javaweb
--

CREATE DATABASE IF NOT EXISTS javaweb;
USE javaweb;

--
-- Definition of table `books`
--

DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `authors` varchar(256) NOT NULL,
  `description` text,
  `publisher` varchar(45) DEFAULT NULL,
  `year` int(10) unsigned DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(10) unsigned DEFAULT NULL,
  `onsale` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `books`
--

/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`id`,`name`,`authors`,`description`,`publisher`,`year`,`price`,`quantity`,`onsale`) VALUES 
 (1,'Core Servlets','Marty Hall','Best Intro to Servlets','Prentice Hall',2005,15,30,0),
 (2,'Thinking In Java','Bruce Eckel','Best Java tutorial','Sams',2009,25,50,0);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;


--
-- Definition of table `sequence_gen`
--

DROP TABLE IF EXISTS `sequence_gen`;
CREATE TABLE `sequence_gen` (
  `GEN_KEY` varchar(50) NOT NULL,
  `GEN_VALUE` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`GEN_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sequence_gen`
--

/*!40000 ALTER TABLE `sequence_gen` DISABLE KEYS */;
INSERT INTO `sequence_gen` (`GEN_KEY`,`GEN_VALUE`) VALUES 
 ('PHONE_ID','3');
/*!40000 ALTER TABLE `sequence_gen` ENABLE KEYS */;


--
-- Definition of table `todo`
--

DROP TABLE IF EXISTS `todo`;
CREATE TABLE `todo` (
  `ID` bigint(20) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `DUEDATE` datetime DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TITLE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `todo`
--

/*!40000 ALTER TABLE `todo` DISABLE KEYS */;
INSERT INTO `todo` (`ID`,`DESCRIPTION`,`DUEDATE`,`PRIORITY`,`TITLE`) VALUES 
 (2,'asdsadas','2013-05-12 02:00:00',0,'asdaasd'),
 (3,'todo','2013-07-25 02:00:00',0,'todo');
/*!40000 ALTER TABLE `todo` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
