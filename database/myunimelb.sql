-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: myunimelb
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `map`
--

DROP TABLE IF EXISTS `map`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `map` (
  `MID` int(11) NOT NULL AUTO_INCREMENT,
  `buildName` varchar(45) COLLATE utf8_bin NOT NULL,
  `address` varchar(80) COLLATE utf8_bin NOT NULL,
  `lastTime` datetime NOT NULL,
  PRIMARY KEY (`MID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `map`
--

LOCK TABLES `map` WRITE;
/*!40000 ALTER TABLE `map` DISABLE KEYS */;
INSERT INTO `map` VALUES (1,'Kathleen Fitzpatrick Theatre','Arts West- West Wing : Building 148B','2021-10-02 21:48:56'),(2,'The David P. Derham Theatre','Melbourne Law School : Building 106','2021-10-02 21:51:21');
/*!40000 ALTER TABLE `map` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `markrecord`
--

DROP TABLE IF EXISTS `markrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `markrecord` (
  `RID` int(11) NOT NULL AUTO_INCREMENT,
  `subjID` int(11) NOT NULL,
  `practiScore` float NOT NULL DEFAULT '0',
  `theoryScore` float NOT NULL DEFAULT '0',
  `diffiScore` float NOT NULL DEFAULT '0',
  `comment` text COLLATE utf8_bin,
  `comUID` int(11) NOT NULL,
  `lastTime` datetime NOT NULL,
  PRIMARY KEY (`RID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `markrecord`
--

LOCK TABLES `markrecord` WRITE;
/*!40000 ALTER TABLE `markrecord` DISABLE KEYS */;
INSERT INTO `markrecord` VALUES (1,1,5,5,5,'this is a good sub.',1,'2021-10-02 21:03:01'),(2,1,1,1,1,'this is a bad sub.',2,'2021-10-02 21:11:01');
/*!40000 ALTER TABLE `markrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `SID` int(11) NOT NULL AUTO_INCREMENT,
  `subjName` varchar(45) NOT NULL,
  `subjCode` varchar(45) NOT NULL,
  `descrip` mediumtext,
  `priSID` varchar(50) DEFAULT NULL,
  `course` varchar(45) DEFAULT NULL,
  `practiScore` float NOT NULL DEFAULT '0',
  `theoryScore` float NOT NULL DEFAULT '0',
  `diffiScore` float NOT NULL DEFAULT '0',
  `lastTime` datetime NOT NULL,
  PRIMARY KEY (`SID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Algorithms and Complexity','COMP90038','The aim of this subject is for students to develop familiarity and competence in assessing and designing computer programs for computational efficiency. Although computers manipulate data very quickly, to solve large-scale problems, we must design strategies so that the calculations combine effectively. Over the latter half of the 20th century, an elegant theory of computational efficiency developed. This subject introduces students to the fundamentals of this theory and to many of the classical algorithms and data structures that solve key computational questions. These questions include distance computations in networks, searching items in large collections, and sorting them in order.',NULL,'MIT',0,0,0,'2021-10-02 18:21:01'),(2,'Software Processes and Management','SWEN90016','The aim of this subject is to introduce students to the software engineering principles, processes, tools and techniques for analysing and managing complex software projects.','1','MIT',0,0,0,'2021-10-02 21:03:01');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjmap`
--

DROP TABLE IF EXISTS `subjmap`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjmap` (
  `SMID` int(11) NOT NULL AUTO_INCREMENT,
  `subjID` int(11) NOT NULL,
  `addrID` int(11) NOT NULL,
  `type` varchar(45) COLLATE utf8_bin NOT NULL,
  `lastTime` datetime NOT NULL,
  PRIMARY KEY (`SMID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjmap`
--

LOCK TABLES `subjmap` WRITE;
/*!40000 ALTER TABLE `subjmap` DISABLE KEYS */;
INSERT INTO `subjmap` VALUES (1,1,2,'lec','2021-10-02 21:03:01'),(2,1,1,'lab','2021-10-02 20:02:11'),(3,2,2,'lab','2021-10-02 20:22:22');
/*!40000 ALTER TABLE `subjmap` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `UID` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(45) COLLATE utf8_bin NOT NULL,
  `email` varchar(45) COLLATE utf8_bin NOT NULL,
  `phone` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `course` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `lastTime` datetime NOT NULL,
  PRIMARY KEY (`UID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin@qq.com','111111111111','MIT','2021-10-02 20:21:03'),(2,'misaka','mikoto','bilibili@gmail.com','22222222222','','2021-10-02 20:31:01'),(3,'royal','no1','0214@qq.com','','MIT','2021-10-02 19:48:56');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'myunimelb'
--

--
-- Dumping routines for database 'myunimelb'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-17  0:16:11
