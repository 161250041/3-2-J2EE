-- MySQL dump 10.13  Distrib 8.0.12, for osx10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: J2EEOrderPj
-- ------------------------------------------------------
-- Server version	8.0.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Commodities`
--

DROP TABLE IF EXISTS `Commodities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Commodities` (
  `commodityID` int(11) NOT NULL AUTO_INCREMENT,
  `commodityName` varchar(20) NOT NULL,
  `commodityPrice` double NOT NULL,
  `url` varchar(200) NOT NULL DEFAULT 'http://img.hb.aicdn.com/1622ec4ffca2a4109bbce662fb45acb6301fcc72128e0-dnjEMs_fw658',
  PRIMARY KEY (`commodityID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Commodities`
--

INSERT INTO `Commodities` VALUES (1,'透明玻璃瓶',25,'http://img.hb.aicdn.com/d02663fb6d9ab28892dc4028b9d118db38c55cc250de-qJczgP_fw658'),(2,'手账本',20,'http://img.hb.aicdn.com/135c5d22aac15c68d2ab3de17057b2b0516bbb2018db8-DcngQL_fw658'),(3,'宜家气泡水',6.5,'http://img.hb.aicdn.com/9aed7a02da34fa8e8da33f7d83070ebda8b5e7e8dee2-627JQA_fw658'),(4,'装饰品',30,'http://img.hb.aicdn.com/0cf3cdc3824067506b4b38f94b648bb0ed0e432313460-9zA0BH_fw658'),(5,'储物架',150.5,'http://img.hb.aicdn.com/00a4387af27cd2467a6a3db23a5a90b309a2e0f5b913-gOfNTH_fw658'),(6,'香薰蜡烛',80,'http://img.hb.aicdn.com/8bc88a7964fc9c98da4bda3c245fcb0de2ab5ad0ada0-j1ctzD_fw658'),(7,'床上用品套装',540,'http://img.hb.aicdn.com/b214c1812cbd621ef39e8f178c1e99f36fb76dd810720-14tUdg_fw658');

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Orders` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` varchar(20) NOT NULL,
  `commodityID` int(11) NOT NULL,
  `isValid` tinyint(1) NOT NULL,
  `time` timestamp NOT NULL,
  `discountPrice` double NOT NULL DEFAULT '0',
  `price` double NOT NULL DEFAULT '0',
  `num` int(11) NOT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

INSERT INTO `Orders` VALUES (1,'hyh',2,0,'2018-12-24 03:39:44',20,20,1),(2,'hyh',1,1,'2018-12-24 03:40:58',25,25,1),(3,'hyh',2,1,'2018-12-24 03:42:06',20,20,1),(4,'hyh',1,1,'2018-12-24 03:42:37',25,25,1),(5,'hyh',3,1,'2018-12-24 03:42:37',6.5,6.5,1),(6,'hyh',2,0,'2018-12-24 04:40:54',20,20,1),(7,'hyh',2,1,'2018-12-24 16:50:35',20,20,1),(8,'侯韵晗',2,0,'2018-12-24 17:48:01',20,20,1),(9,'侯韵晗',1,1,'2018-12-24 17:48:09',25,25,1),(10,'侯韵晗',2,1,'2018-12-24 19:11:51',20,20,1),(11,'侯韵晗',3,1,'2018-12-24 19:11:51',13,13,2),(12,'侯韵晗',5,0,'2018-12-24 21:32:25',150.5,150.5,1),(13,'侯韵晗',1,1,'2019-01-04 21:29:19',25,25,1),(14,'侯韵晗',2,1,'2019-01-04 21:29:19',20,20,1),(15,'hyh',1,1,'2019-01-05 00:58:17',25,25,1),(16,'hyh',1,0,'2019-01-05 02:53:27',25,25,1),(17,'hyh',5,0,'2019-01-05 02:53:27',150.5,150.5,1),(18,'hyh',1,0,'2019-01-05 02:55:09',25,25,1),(19,'hyh',5,0,'2019-01-05 02:55:09',150.5,150.5,1),(20,'hyh',7,0,'2019-01-05 02:55:17',432,540,1),(21,'hyh',5,0,'2019-01-05 02:55:17',120.4,150.5,1),(22,'hyh',7,0,'2019-01-05 02:55:27',1296,1620,3),(23,'侯韵晗',2,0,'2019-01-05 02:56:06',20,20,1),(24,'hyh',2,0,'2019-01-05 02:57:44',20,20,1),(25,'hyh',1,1,'2019-01-05 02:57:47',25,25,1),(26,'侯韵晗',3,0,'2019-01-05 03:03:13',6.5,6.5,1),(27,'侯韵晗',3,1,'2019-01-05 03:03:21',6.5,6.5,1),(28,'侯韵晗',2,0,'2019-01-05 03:05:41',20,20,1),(29,'侯韵晗',3,1,'2019-01-05 03:05:53',6.5,6.5,1),(30,'侯韵晗',3,0,'2019-01-05 03:06:06',6.5,6.5,1),(31,'侯韵晗',1,0,'2019-01-05 03:06:06',25,25,1),(32,'侯韵晗',1,1,'2019-01-05 03:06:15',50,50,2),(33,'侯韵晗',3,1,'2019-01-05 03:06:15',6.5,6.5,1),(34,'侯韵晗',1,0,'2019-01-05 03:06:38',25,25,1),(35,'侯韵晗',2,0,'2019-01-05 03:06:38',40,40,2);

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userID` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `User_userID_uindex` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

INSERT INTO `User` VALUES (1,'hyh','123',603.5),(2,'fbr','123',20),(3,'侯韵晗','123',307.5);
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-06  1:07:39
