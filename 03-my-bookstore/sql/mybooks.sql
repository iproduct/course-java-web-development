-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: javaweb
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mybooks`
--

DROP TABLE IF EXISTS `mybooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `mybooks` (
  `id` int(11) NOT NULL,
  `title` varchar(150) NOT NULL,
  `authors` varchar(150) NOT NULL,
  `format` varchar(30) DEFAULT NULL,
  `isbn` varchar(13) DEFAULT NULL,
  `publisher` varchar(30) DEFAULT NULL,
  `publishedDate` date DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mybooks`
--

LOCK TABLES `mybooks` WRITE;
/*!40000 ALTER TABLE `mybooks` DISABLE KEYS */;
INSERT INTO `mybooks` VALUES (1,'The Big Score','Peter Corris','Paperback','9781741752236','Allen and Unwin','2007-01-01',22.95),(2,'Split','Tara Moss','Paperback','732268133','Harper Collins','2003-01-01',26.70),(3,'The Broken Shore','Peter Temple','Hardback','9781921656774','Text','2010-09-27',19.50),(4,'Truth','Peter Temple','Hardback','9781921656620','Allen and Unwin','2010-12-10',29.00),(5,'The Fictional Woman','Tara Moss','Paperback','732297893','Harper Collins','2014-02-10',15.90),(6,'Thinking in Java','Bruce Eckel','Paperback','1234456789','Random House','2016-06-03',45.90);
/*!40000 ALTER TABLE `mybooks` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-22 20:54:31
