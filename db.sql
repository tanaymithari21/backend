CREATE DATABASE  IF NOT EXISTS `mangaverse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mangaverse`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: mangaverse
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `manga_id` bigint NOT NULL,
  `number` int NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_manga` (`manga_id`),
  CONSTRAINT `fk_manga` FOREIGN KEY (`manga_id`) REFERENCES `manga` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

LOCK TABLES `chapter` WRITE;
/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES (15,7,1,'FUBUKI');
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chapter_page`
--

DROP TABLE IF EXISTS `chapter_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter_page` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `chapter_id` bigint NOT NULL,
  `page_number` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `chapter_id` (`chapter_id`),
  CONSTRAINT `chapter_page_ibfk_1` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=203 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter_page`
--

LOCK TABLES `chapter_page` WRITE;
/*!40000 ALTER TABLE `chapter_page` DISABLE KEYS */;
INSERT INTO `chapter_page` VALUES (5,15,1,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774120/FUBUKI_page_1.jpg'),(6,15,2,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774125/FUBUKI_page_2.jpg'),(7,15,3,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774131/FUBUKI_page_3.jpg'),(8,15,4,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774139/FUBUKI_page_4.jpg'),(9,15,5,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774149/FUBUKI_page_5.jpg'),(10,15,6,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774161/FUBUKI_page_6.jpg'),(11,15,7,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774171/FUBUKI_page_7.jpg'),(12,15,8,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774180/FUBUKI_page_8.jpg'),(13,15,9,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774187/FUBUKI_page_9.jpg'),(14,15,10,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774193/FUBUKI_page_10.jpg'),(15,15,11,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774199/FUBUKI_page_11.jpg'),(16,15,12,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774204/FUBUKI_page_12.jpg'),(17,15,13,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774209/FUBUKI_page_13.jpg'),(18,15,14,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774215/FUBUKI_page_14.jpg'),(19,15,15,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774224/FUBUKI_page_15.jpg'),(20,15,16,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774235/FUBUKI_page_16.jpg'),(21,15,17,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774243/FUBUKI_page_17.jpg'),(22,15,18,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774633/FUBUKI_page_18.jpg'),(23,15,19,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774639/FUBUKI_page_19.jpg'),(24,15,20,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774645/FUBUKI_page_20.jpg'),(25,15,21,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774654/FUBUKI_page_21.jpg'),(26,15,22,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774664/FUBUKI_page_22.jpg'),(27,15,23,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774670/FUBUKI_page_23.jpg'),(28,15,24,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774676/FUBUKI_page_24.jpg'),(29,15,25,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774686/FUBUKI_page_25.jpg'),(30,15,26,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774691/FUBUKI_page_26.jpg'),(31,15,27,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774699/FUBUKI_page_27.jpg'),(32,15,28,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774704/FUBUKI_page_28.jpg'),(33,15,29,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774714/FUBUKI_page_29.jpg'),(34,15,30,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774723/FUBUKI_page_30.jpg'),(35,15,31,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774729/FUBUKI_page_31.jpg'),(36,15,32,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774736/FUBUKI_page_32.jpg'),(37,15,33,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774745/FUBUKI_page_33.jpg'),(38,15,34,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774752/FUBUKI_page_34.jpg'),(39,15,35,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774763/FUBUKI_page_35.jpg'),(40,15,36,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774769/FUBUKI_page_36.jpg'),(41,15,37,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774776/FUBUKI_page_37.jpg'),(42,15,38,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774784/FUBUKI_page_38.jpg'),(43,15,39,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774788/FUBUKI_page_39.jpg'),(44,15,40,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774794/FUBUKI_page_40.jpg'),(45,15,41,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774800/FUBUKI_page_41.jpg'),(46,15,42,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774806/FUBUKI_page_42.jpg'),(47,15,43,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774813/FUBUKI_page_43.jpg'),(48,15,44,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774818/FUBUKI_page_44.jpg'),(49,15,45,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774825/FUBUKI_page_45.jpg'),(50,15,46,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774832/FUBUKI_page_46.jpg'),(51,15,47,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774840/FUBUKI_page_47.jpg'),(52,15,48,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774850/FUBUKI_page_48.jpg'),(53,15,49,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774863/FUBUKI_page_49.jpg'),(54,15,50,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774876/FUBUKI_page_50.jpg'),(55,15,51,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774886/FUBUKI_page_51.jpg'),(56,15,52,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774900/FUBUKI_page_52.jpg'),(57,15,53,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774909/FUBUKI_page_53.jpg'),(58,15,54,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774917/FUBUKI_page_54.jpg'),(59,15,55,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774928/FUBUKI_page_55.jpg'),(60,15,56,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774941/FUBUKI_page_56.jpg'),(61,15,57,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774950/FUBUKI_page_57.jpg'),(62,15,58,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774959/FUBUKI_page_58.jpg'),(63,15,59,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774968/FUBUKI_page_59.jpg'),(64,15,60,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774980/FUBUKI_page_60.jpg'),(65,15,61,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774774991/FUBUKI_page_61.jpg'),(66,15,62,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775000/FUBUKI_page_62.jpg'),(67,15,63,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775011/FUBUKI_page_63.jpg'),(68,15,64,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775022/FUBUKI_page_64.jpg'),(69,15,65,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775037/FUBUKI_page_65.jpg'),(70,15,66,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775046/FUBUKI_page_66.jpg'),(71,15,67,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775057/FUBUKI_page_67.jpg'),(72,15,68,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775063/FUBUKI_page_68.jpg'),(73,15,69,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775069/FUBUKI_page_69.jpg'),(74,15,70,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775077/FUBUKI_page_70.jpg'),(75,15,71,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775085/FUBUKI_page_71.jpg'),(76,15,72,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775091/FUBUKI_page_72.jpg'),(77,15,73,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775097/FUBUKI_page_73.jpg'),(78,15,74,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775105/FUBUKI_page_74.jpg'),(79,15,75,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775114/FUBUKI_page_75.jpg'),(80,15,76,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775120/FUBUKI_page_76.jpg'),(81,15,77,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775141/FUBUKI_page_77.jpg'),(82,15,78,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775146/FUBUKI_page_78.jpg'),(83,15,79,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775152/FUBUKI_page_79.jpg'),(84,15,80,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775157/FUBUKI_page_80.jpg'),(85,15,81,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775164/FUBUKI_page_81.jpg'),(86,15,82,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775171/FUBUKI_page_82.jpg'),(87,15,83,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775180/FUBUKI_page_83.jpg'),(88,15,84,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775191/FUBUKI_page_84.jpg'),(89,15,85,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775203/FUBUKI_page_85.jpg'),(90,15,86,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775213/FUBUKI_page_86.jpg'),(91,15,87,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775219/FUBUKI_page_87.jpg'),(92,15,88,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775227/FUBUKI_page_88.jpg'),(93,15,89,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775242/FUBUKI_page_89.jpg'),(94,15,90,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775253/FUBUKI_page_90.jpg'),(95,15,91,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775262/FUBUKI_page_91.jpg'),(96,15,92,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775273/FUBUKI_page_92.jpg'),(97,15,93,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775279/FUBUKI_page_93.jpg'),(98,15,94,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775286/FUBUKI_page_94.jpg'),(99,15,95,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775294/FUBUKI_page_95.jpg'),(100,15,96,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775311/FUBUKI_page_96.jpg'),(101,15,97,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775317/FUBUKI_page_97.jpg'),(102,15,98,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775325/FUBUKI_page_98.jpg'),(103,15,99,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775335/FUBUKI_page_99.jpg'),(104,15,100,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775342/FUBUKI_page_100.jpg'),(105,15,101,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775351/FUBUKI_page_101.jpg'),(106,15,102,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775357/FUBUKI_page_102.jpg'),(107,15,103,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775364/FUBUKI_page_103.jpg'),(108,15,104,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775396/FUBUKI_page_104.jpg'),(109,15,105,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775409/FUBUKI_page_105.jpg'),(110,15,106,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775418/FUBUKI_page_106.jpg'),(111,15,107,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775433/FUBUKI_page_107.jpg'),(112,15,108,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775442/FUBUKI_page_108.jpg'),(113,15,109,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775454/FUBUKI_page_109.jpg'),(114,15,110,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775466/FUBUKI_page_110.jpg'),(115,15,111,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775479/FUBUKI_page_111.jpg'),(116,15,112,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775484/FUBUKI_page_112.jpg'),(117,15,113,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775493/FUBUKI_page_113.jpg'),(118,15,114,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775497/FUBUKI_page_114.jpg'),(119,15,115,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775503/FUBUKI_page_115.jpg'),(120,15,116,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775508/FUBUKI_page_116.jpg'),(121,15,117,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775513/FUBUKI_page_117.jpg'),(122,15,118,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775519/FUBUKI_page_118.jpg'),(123,15,119,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775526/FUBUKI_page_119.jpg'),(124,15,120,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775532/FUBUKI_page_120.jpg'),(125,15,121,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775538/FUBUKI_page_121.jpg'),(126,15,122,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775545/FUBUKI_page_122.jpg'),(127,15,123,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775550/FUBUKI_page_123.jpg'),(128,15,124,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774775553/FUBUKI_page_124.jpg'),(129,15,125,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776819/FUBUKI_page_125.jpg'),(130,15,126,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776833/FUBUKI_page_126.jpg'),(131,15,127,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776847/FUBUKI_page_127.jpg'),(132,15,128,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776866/FUBUKI_page_128.jpg'),(133,15,129,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776875/FUBUKI_page_129.jpg'),(134,15,130,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776884/FUBUKI_page_130.jpg'),(135,15,131,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776894/FUBUKI_page_131.jpg'),(136,15,132,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776902/FUBUKI_page_132.jpg'),(137,15,133,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776914/FUBUKI_page_133.jpg'),(138,15,134,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776923/FUBUKI_page_134.jpg'),(139,15,135,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776932/FUBUKI_page_135.jpg'),(140,15,136,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776942/FUBUKI_page_136.jpg'),(141,15,137,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776950/FUBUKI_page_137.jpg'),(142,15,138,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776958/FUBUKI_page_138.jpg'),(143,15,139,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776972/FUBUKI_page_139.jpg'),(144,15,140,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776979/FUBUKI_page_140.jpg'),(145,15,141,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776986/FUBUKI_page_141.jpg'),(146,15,142,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774776997/FUBUKI_page_142.jpg'),(147,15,143,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777006/FUBUKI_page_143.jpg'),(148,15,144,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777018/FUBUKI_page_144.jpg'),(149,15,145,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777028/FUBUKI_page_145.jpg'),(150,15,146,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777037/FUBUKI_page_146.jpg'),(151,15,147,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777051/FUBUKI_page_147.jpg'),(152,15,148,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777058/FUBUKI_page_148.jpg'),(153,15,149,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777066/FUBUKI_page_149.jpg'),(154,15,150,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777073/FUBUKI_page_150.jpg'),(155,15,151,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777084/FUBUKI_page_151.jpg'),(156,15,152,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777098/FUBUKI_page_152.jpg'),(157,15,153,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777117/FUBUKI_page_153.jpg'),(158,15,154,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777139/FUBUKI_page_154.jpg'),(159,15,155,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777144/FUBUKI_page_155.jpg'),(160,15,156,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777190/FUBUKI_page_156.jpg'),(161,15,157,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777233/FUBUKI_page_157.jpg'),(162,15,158,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777239/FUBUKI_page_158.jpg'),(163,15,159,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777245/FUBUKI_page_159.jpg'),(164,15,160,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777252/FUBUKI_page_160.jpg'),(165,15,161,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777261/FUBUKI_page_161.jpg'),(166,15,162,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777269/FUBUKI_page_162.jpg'),(167,15,163,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777277/FUBUKI_page_163.jpg'),(168,15,164,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777283/FUBUKI_page_164.jpg'),(169,15,165,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777288/FUBUKI_page_165.jpg'),(170,15,166,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777302/FUBUKI_page_166.jpg'),(171,15,167,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777311/FUBUKI_page_167.jpg'),(172,15,168,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777326/FUBUKI_page_168.jpg'),(173,15,169,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777337/FUBUKI_page_169.jpg'),(174,15,170,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777347/FUBUKI_page_170.jpg'),(175,15,171,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777356/FUBUKI_page_171.jpg'),(176,15,172,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777364/FUBUKI_page_172.jpg'),(177,15,173,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777374/FUBUKI_page_173.jpg'),(178,15,174,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777390/FUBUKI_page_174.jpg'),(179,15,175,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777399/FUBUKI_page_175.jpg'),(180,15,176,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777412/FUBUKI_page_176.jpg'),(181,15,177,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777424/FUBUKI_page_177.jpg'),(182,15,178,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777435/FUBUKI_page_178.jpg'),(183,15,179,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777448/FUBUKI_page_179.jpg'),(184,15,180,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777459/FUBUKI_page_180.jpg'),(185,15,181,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777469/FUBUKI_page_181.jpg'),(186,15,182,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777481/FUBUKI_page_182.jpg'),(187,15,183,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777497/FUBUKI_page_183.jpg'),(188,15,184,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777507/FUBUKI_page_184.jpg'),(189,15,185,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777518/FUBUKI_page_185.jpg'),(190,15,186,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777529/FUBUKI_page_186.jpg'),(191,15,187,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777539/FUBUKI_page_187.jpg'),(192,15,188,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777552/FUBUKI_page_188.jpg'),(193,15,189,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777559/FUBUKI_page_189.jpg'),(194,15,190,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777570/FUBUKI_page_190.jpg'),(195,15,191,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777579/FUBUKI_page_191.jpg'),(196,15,192,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777587/FUBUKI_page_192.jpg'),(197,15,193,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777595/FUBUKI_page_193.jpg'),(198,15,194,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777604/FUBUKI_page_194.jpg'),(199,15,195,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777612/FUBUKI_page_195.jpg'),(200,15,196,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777622/FUBUKI_page_196.jpg'),(201,15,197,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777632/FUBUKI_page_197.jpg'),(202,15,198,'https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774777643/FUBUKI_page_198.jpg');
/*!40000 ALTER TABLE `chapter_page` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genre`
--

DROP TABLE IF EXISTS `genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Action'),(6,'Adventure'),(4,'Comedy'),(2,'Drama'),(22,'Fantasy'),(23,'Horror'),(24,'Mystery'),(3,'Romance'),(25,'Sci-Fi'),(26,'Slice of Life'),(27,'Sports'),(5,'Superhero'),(28,'Supernatural'),(7,'Thriller'),(8,'Tsundere');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manga`
--

DROP TABLE IF EXISTS `manga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manga` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `cover` varchar(255) DEFAULT NULL,
  `description` text,
  `rating` double DEFAULT NULL,
  `status` enum('Ongoing','Completed') NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `year` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manga`
--

LOCK TABLES `manga` WRITE;
/*!40000 ALTER TABLE `manga` DISABLE KEYS */;
INSERT INTO `manga` VALUES (7,'onepunch man','https://res.cloudinary.com/dh5yuvk3k/image/upload/v1774705409/onepunch_man_cover.webp','op',0,'Ongoing','op',2026);
/*!40000 ALTER TABLE `manga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manga_genre`
--

DROP TABLE IF EXISTS `manga_genre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manga_genre` (
  `manga_id` bigint NOT NULL,
  `genre_id` bigint NOT NULL,
  PRIMARY KEY (`manga_id`,`genre_id`),
  KEY `fk_manga_genre_genre` (`genre_id`),
  CONSTRAINT `fk_manga_genre_genre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_manga_genre_manga` FOREIGN KEY (`manga_id`) REFERENCES `manga` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manga_genre`
--

LOCK TABLES `manga_genre` WRITE;
/*!40000 ALTER TABLE `manga_genre` DISABLE KEYS */;
INSERT INTO `manga_genre` VALUES (7,1),(7,4),(7,6);
/*!40000 ALTER TABLE `manga_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mangaverse'
--

--
-- Dumping routines for database 'mangaverse'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-30 14:49:00
