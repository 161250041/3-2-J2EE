-- MySQL dump 10.13  Distrib 8.0.12, for osx10.14 (x86_64)
--
-- Host: 127.0.0.1    Database: Yummy
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
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Address` (
  `addressId` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(30) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `position` int(11) NOT NULL,
  PRIMARY KEY (`addressId`),
  KEY `FK9toieb92e469f29v3orcebix4` (`userId`),
  CONSTRAINT `Address_User_userId_fk` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK9toieb92e469f29v3orcebix4` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

INSERT INTO `Address` VALUES (1,'地址1汉口路',10,0),(2,'地址2',1,0);

--
-- Table structure for table `CommodityInfo`
--

DROP TABLE IF EXISTS `CommodityInfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `CommodityInfo` (
  `commodityId` int(11) NOT NULL AUTO_INCREMENT,
  `restaurantId` int(11) NOT NULL,
  `commodityName` varchar(20) DEFAULT NULL,
  `commodityPrice` double DEFAULT NULL,
  `commodityNum` int(11) DEFAULT NULL,
  `beginTime` timestamp NULL DEFAULT NULL,
  `endTime` timestamp NULL DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`commodityId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CommodityInfo`
--

INSERT INTO `CommodityInfo` VALUES (1,1,'宫保鸡丁',20,3,'2019-03-08 07:32:28','2019-03-19 07:32:33','家常菜'),(2,1,'鱼香肉丝',25.5,10,'2019-03-08 07:32:28','2019-03-19 07:32:33','家常菜'),(3,1,'狮子头',10,8,'2019-03-08 07:32:28','2019-03-20 07:32:33','招牌菜'),(4,1,'盐水鸭',60,3,'2019-03-08 07:32:28','2019-03-20 07:32:33','招牌菜'),(5,1,'酒酿圆子',5,7,'2019-03-08 07:32:28','2019-03-18 07:32:33','招牌菜'),(6,1,'梅花糕',3,2,'2019-03-08 07:32:28','2019-03-20 07:32:33','甜点'),(11,1,'套餐1',40,3,'2019-03-11 15:00:00','2019-03-20 15:00:00','套餐'),(12,2,'菜品2',20,30,'2019-03-12 15:00:00','2019-03-21 15:00:00','类型1'),(13,2,'套餐1',50,20,'2019-03-12 15:00:00','2019-03-19 15:00:00','套餐');

--
-- Table structure for table `OnlineBanking`
--

DROP TABLE IF EXISTS `OnlineBanking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `OnlineBanking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `balance` double DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OnlineBanking`
--

INSERT INTO `OnlineBanking` VALUES (1,'871068655@qq.com','123456',498.674),(2,'835421460@qq.com','123456',492.58);

--
-- Table structure for table `Order`
--

DROP TABLE IF EXISTS `Order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Order` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) DEFAULT NULL,
  `commoditys` varchar(100) DEFAULT NULL,
  `addressId` int(11) DEFAULT NULL,
  `beginTime` timestamp NULL DEFAULT NULL,
  `expectedDeliveryTime` timestamp NULL DEFAULT NULL,
  `actualDeliveryTime` timestamp NULL DEFAULT NULL,
  `price` double DEFAULT NULL,
  `actualPrice` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `refund` double DEFAULT '0',
  `restaurantProfit` double DEFAULT '0',
  `restaurantId` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

INSERT INTO `Order` VALUES (38,'835421460@qq.com','宫保鸡丁*2,套餐1*1,',2,'2019-03-14 14:42:01','2019-03-14 09:00:00',NULL,80,80,3,0,65.6,1),(39,'835421460@qq.com','菜品2*1,',2,'2019-03-14 14:42:27','2019-03-14 15:00:00',NULL,20,20,4,15.579999999999998,0.8200000000000003,2),(40,'835421460@qq.com','梅花糕*1,',2,'2019-03-14 14:42:47','2019-03-14 15:00:00','2019-03-14 14:43:08',3,3,2,0,2.46,1);

--
-- Table structure for table `Restaurant`
--

DROP TABLE IF EXISTS `Restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Restaurant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `restaurantId` varchar(7) NOT NULL,
  `restaurantName` varchar(15) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Restaurant`
--

INSERT INTO `Restaurant` VALUES (1,'0000001','家菜馆','123123','123123','d','美食',1),(2,'0000002','餐厅2','123123','111','汉口路22','美食',1);

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `User` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(20) NOT NULL,
  `userName` varchar(15) DEFAULT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `consumption` double NOT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

INSERT INTO `User` VALUES (1,'835421460@qq.com','123123','方渤镕','18851826191',226),(10,'871068655@qq.com','123123','侯韵晗','111',0);

--
-- Table structure for table `Verification`
--

DROP TABLE IF EXISTS `Verification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `Verification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) NOT NULL,
  `password` varchar(20) DEFAULT NULL,
  `state` int(11) NOT NULL DEFAULT '0',
  `code` varchar(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Verification`
--

INSERT INTO `Verification` VALUES (1,'871068655@qq.com','123123',1,'VFA0'),(3,'835421460@qq.com','123123',1,'5FQ9');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-18 18:49:15
