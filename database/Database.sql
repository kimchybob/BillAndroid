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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `map`
--

LOCK TABLES `map` WRITE;
/*!40000 ALTER TABLE `map` DISABLE KEYS */;
INSERT INTO `map` VALUES (1,'Kathleen Fitzpatrick Theatre','Arts West- West Wing : Building 148B','2021-10-02 21:48:56'),(2,'The David P. Derham Theatre','Melbourne Law School : Building 106','2021-10-02 21:51:21'),(3,'Elisabeth Murdoch Theatre A','Elisabeth Murdoch : Building 134','2021-10-02 21:51:22'),(4,'Barbara Falk Room','Elisabeth Murdoch : Building 134','2021-10-02 21:52:23'),(5,'Law G08','Melbourne Law School : Building 106','2021-10-02 22:11:12'),(6,'Senior Theatre','Chemistry : Building 153','2021-10-02 22:12:12'),(7,'Carrillo Gantner Theatre','Sidney Myer Asia Centre : Building 158','2021-10-02 22:13:12'),(8,'Yasuko Hiraoka Myer Room','Sidney Myer Asia Centre : Building 158','2021-10-02 22:14:11'),(9,'Union Hall','Union House : Building 130','2021-10-03 12:14:11'),(10,'Student Union','Union House : Building 130','2021-10-04 15:14:11');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin KEY_BLOCK_SIZE=1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `markrecord`
--

LOCK TABLES `markrecord` WRITE;
/*!40000 ALTER TABLE `markrecord` DISABLE KEYS */;
INSERT INTO `markrecord` VALUES (1,1,5,5,2,'this is a good sub.',1,'2021-10-02 21:03:01'),(2,1,3,4,5,'this is a difficult sub.',2,'2021-10-02 21:11:01'),(3,2,3,2,2,'Not so difficult.',1,'2021-10-17 00:25:07'),(4,1,4,4,4,'difficult but useful!',1,'2021-10-25 16:15:15'),(5,2,5,3,2,'Will be useful in the future',1,'2021-10-25 16:30:33'),(6,3,3,2,2,'Normal difficulty',3,'2021-10-25 16:31:33');
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
  `course` varchar(1000) DEFAULT NULL,
  `practiScore` float NOT NULL DEFAULT '0',
  `theoryScore` float NOT NULL DEFAULT '0',
  `diffiScore` float NOT NULL DEFAULT '0',
  `lastTime` datetime NOT NULL,
  PRIMARY KEY (`SID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Algorithms and Complexity','COMP90038','The aim of this subject is for students to develop familiarity and competence in assessing and designing computer programs for computational efficiency. Although computers manipulate data very quickly, to solve large-scale problems, we must design strategies so that the calculations combine effectively. Over the latter half of the 20th century, an elegant theory of computational efficiency developed. This subject introduces students to the fundamentals of this theory and to many of the classical algorithms and data structures that solve key computational questions. These questions include distance computations in networks, searching items in large collections, and sorting them in order.',NULL,'Master of Information Technology',4,4.33333,3.66667,'2021-11-02 17:15:57'),(2,'Software Processes and Management','SWEN90016','The aim of this subject is to introduce students to the software engineering principles, processes, tools and techniques for analysing and managing complex software projects.','1','Master of Information Technology',4,2.5,2,'2021-11-02 17:15:57'),(3,'Programming and Software Development','COMP90041','The aims for this subject is for students to develop an understanding of approaches to solving moderately complex problems with computers, and to be able to demonstrate proficiency in designing and writing programs. The programming language used is Java.',NULL,'Master of Information Technology',3,2,2,'2021-11-02 17:15:57'),(4,'Internet Technologies','COMP90007','The subject will introduce the basics of computer networks to students through a study of layered models of computer networks and applications. The first half of the subject deals with data communication protocols in the lower layers of OSI and TCP/IP reference models. The students will be exposed to the working of various fundamental networking technologies such as wireless, LAN, RFID and sensor networks. The second half of the subject deals with the upper layers of the TCP/IP reference model through a study of several Internet applications.',NULL,'Master of Information Technology',0,0,0,'2021-10-25 16:32:34'),(5,'Database Systems & Information Modelling','INFO90002','The subject introduces key topics in modern information organisation, particularly with regard to structured databases. The well-founded relational theory behind modern structured query language (SQL) engines, has given them as much a place behind the web site of an organisation and on the desktop, as they traditionally enjoyed on corporate mainframes. Topics covered may include: the managerial view of data, information and knowledge; conceptual, logical and physical data modelling; normalisation and de-normalisation; the SQL language; data integrity; transaction processing, data warehousing, web services and organisational memory technologies. This is a core foundation subject for both the Master of Information Systems and Master of Information Technology.',NULL,'Master of Information Technology,Master of Information System',0,0,0,'2021-10-25 16:34:34'),(6,'Distributed Systems','COMP90015','The subject aims to provide an understanding of the principles on which the Web, Email, DNS and other interesting distributed systems are based. Questions concerning distributed architecture, concepts and design; and how these meet the demands of contemporary distributed applications will be addressed.',NULL,'Master of Information Technology',0,0,0,'2021-10-25 17:34:34'),(7,'Declarative Programming','COMP90048','Declarative programming languages provide elegant and powerful programming paradigms which every programmer should know. This subject presents declarative programming languages and techniques.','1','Master of Information Technology',0,0,0,'2021-10-25 17:24:34'),(8,'Cryptography and Security','COMP90043','The subject will explore foundational knowledge in the area of cryptography and information security. The overall aim is to gain an understanding of fundamental cryptographic concepts like encryption and signatures and use it to build and analyse security in computers, communications and networks. This subject covers fundamental concepts in information security on the basis of methods of modern cryptography, including encryption, signatures and hash functions.','1,3','Master of Information Technology',0,0,0,'2021-10-25 17:25:34'),(9,'Advanced Database Systems','COMP90050','Many applications require access to very large amounts of data. These applications often require reliability (data must not be lost even in the presence of hardware failures), and the ability to retrieve and process the data very efficiently.','5','Master of Information Technology',0,0,0,'2021-10-25 16:25:34'),(10,'Introduction to Machine Learning','COMP90049','Machine Learning is the study of making accurate, computationally efficient, interpretable and robust inferences from data, often drawing on principles from statistics. This subject aims to introduce students to the intellectual foundations of machine learning, including the mathematical principles of learning from data, algorithms and data structures for machine learning, and practical skills of data analysis.','1','Master of Information Technology',0,0,0,'2021-10-26 16:25:34'),(11,'AI Planning for Autonomy','COMP90054','The key focus of this subject is the foundations of autonomous agents that reason about action, applying techniques such as automated planning, reinforcement learning, game theory, and their real-world applications. Autonomous agents are active entities that perceive their environment, reason, plan and execute appropriate actions to achieve their goals, in service of their users (the real world, human beings, or other agents). The subject focuses on the foundations that enable agents to reason autonomously about goals & rewards, perception, actions, strategy, and the knowledge of other agents during collaborative task execution, and the ethical impacts of agents with this ability.','1','Master of Information Technology',0,0,0,'2021-10-26 13:22:34'),(12,'Cluster and Cloud Computing','COMP90024','The growing popularity of the Internet along with the availability of powerful computers and high-speed networks as low-cost commodity components are changing the way we do parallel and distributed computing (PDC). Cluster and Cloud Computing are two approaches for PDC. Clusters employ cost-effective commodity components for building powerful computers within local-area networks. Recently, “cloud computing” has emerged as the new paradigm for delivery of computing as services in a pay-as-you-go-model via the Internet. These approaches are used to tackle may research problems with particular focus on \"big data\" challenges that arise across a variety of domains.','1;3;4','Master of Information Technology',0,0,0,'2021-10-26 17:25:34'),(13,'Introduction to Programming','COMP90059','This subject introduces the fundamental concepts of computing programming, and how to solve simple problems using high-level procedural language, with a specific emphasis on data manipulation, transformation, and visualisation of data.',NULL,'Master of Information System',0,0,0,'2021-10-26 18:25:34'),(14,'Fundamentals of Information Systems','ISYS90026','Information Technology now impacts on people and processes within and beyond organisational boundaries. The discipline of Information Systems is concerned with the effective use of IT by people and organisations. This subject provides context on Information Systems practice and use viewed through a range of roles that interact with these systems, including those of system developers, users, business managers, IT managers, and vendors. It provides students with a foundation that is further built on in other information systems subjects.',NULL,'Master of Information System',0,0,0,'2021-10-26 18:31:34');
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjmap`
--

LOCK TABLES `subjmap` WRITE;
/*!40000 ALTER TABLE `subjmap` DISABLE KEYS */;
INSERT INTO `subjmap` VALUES (1,1,2,'lec','2021-10-02 21:03:01'),(2,1,1,'lab','2021-10-02 20:02:11'),(3,2,2,'lab','2021-10-02 20:22:22'),(4,2,3,'lec','2021-10-24 20:22:22'),(5,3,4,'lec','2021-10-25 20:22:22'),(6,3,5,'lab','2021-10-25 20:23:22'),(7,4,6,'lec','2021-10-25 20:25:22'),(8,4,1,'lab','2021-10-25 21:25:22'),(9,5,7,'lec','2021-10-26 21:25:22'),(10,6,3,'lec','2021-10-26 21:26:22'),(11,8,8,'lec','2021-10-26 22:26:22'),(12,8,4,'lab','2021-10-26 22:27:22'),(13,9,3,'lec','2021-10-26 23:27:22'),(14,10,2,'lec','2021-10-26 21:22:22'),(15,10,6,'lab','2021-10-26 21:23:22'),(16,11,1,'lec','2021-10-26 21:24:22'),(17,11,2,'lab','2021-10-26 21:25:22'),(18,11,3,'lab','2021-10-26 21:26:22'),(19,12,4,'lec','2021-10-26 21:27:22'),(20,13,5,'lec','2021-10-26 21:28:22'),(21,14,7,'lec','2021-10-26 21:31:22'),(22,14,3,'lab','2021-10-26 21:32:22');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','admin','admin@gmail.com','111111111111','Master of Information Technology','2021-10-02 20:21:03'),(2,'misaka','mikoto','bilibili@gmail.com','22222222222','Master of Information Technology','2021-10-02 20:31:01'),(3,'royal','no1','0214@qq.com','','Master of Information Technology','2021-10-02 19:48:56'),(4,'ashley','123456','ashley@student.unimelb.edu.au','0412345678','Master of Information System','2021-10-02 19:49:56');
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

-- Dump completed on 2021-11-02 17:17:28
