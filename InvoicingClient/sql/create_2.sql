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
-- Create schema java32ed
--

CREATE DATABASE IF NOT EXISTS java32ed;
USE java32ed;

--
-- Definition of table `contragent`
--

DROP TABLE IF EXISTS `contragent`;
CREATE TABLE `contragent` (
  `IDNUMBER` bigint(20) NOT NULL,
  `ACCOUNTABLEPERSON` varchar(255) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `BIC` varchar(255) DEFAULT NULL,
  `IBAN` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDNUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contragent`
--

/*!40000 ALTER TABLE `contragent` DISABLE KEYS */;
/*!40000 ALTER TABLE `contragent` ENABLE KEYS */;


--
-- Definition of table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE `invoice` (
  `number` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `issuer` varchar(255) NOT NULL,
  `receiver` varchar(255) NOT NULL,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `invoice`
--

/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;


--
-- Definition of table `items`
--

DROP TABLE IF EXISTS `items`;
CREATE TABLE `items` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(80) NOT NULL,
  `vendor` varchar(80) DEFAULT NULL,
  `price` double unsigned DEFAULT NULL,
  `group` varchar(20) NOT NULL DEFAULT 'Other',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `items`
--

/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` (`id`,`name`,`vendor`,`price`,`group`) VALUES 
 (1,'Thinking in Java 4ed.','Prentice Hall',25.6,'it_books'),
 (2,'UML Distilled','SoftPress',15,'it_books'),
 (3,'Whiteboard Marker','BIC',2.5,'consumables'),
 (4,'Unified Process','Prentice Hall',28.7,'it_books'),
 (6,'Dummy Product','Noname Vendor',15.6,'Other');
/*!40000 ALTER TABLE `items` ENABLE KEYS */;


--
-- Definition of table `position`
--

DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `number` int(10) unsigned NOT NULL,
  `invoice_number` bigint(20) unsigned NOT NULL,
  `quantity` double NOT NULL,
  `price` double NOT NULL,
  `item_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`number`,`invoice_number`),
  KEY `FK_position_item` (`item_id`),
  KEY `FK_position_invoice` (`invoice_number`),
  CONSTRAINT `FK_position_invoice` FOREIGN KEY (`invoice_number`) REFERENCES `invoice` (`number`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_position_item` FOREIGN KEY (`item_id`) REFERENCES `items` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `position`
--

/*!40000 ALTER TABLE `position` DISABLE KEYS */;
/*!40000 ALTER TABLE `position` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
