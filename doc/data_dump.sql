CREATE DATABASE  IF NOT EXISTS `pedaggio_autostradale` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `pedaggio_autostradale`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pedaggio_autostradale
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `autostrada`
--

DROP TABLE IF EXISTS `autostrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autostrada` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tariffa_classe_a` float unsigned NOT NULL,
  `tariffa_classe_b` float unsigned NOT NULL,
  `tariffa_classe_3` float unsigned NOT NULL,
  `tariffa_classe_4` float unsigned NOT NULL,
  `tariffa_classe_5` float unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome_UNIQUE` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autostrada`
--

LOCK TABLES `autostrada` WRITE;
/*!40000 ALTER TABLE `autostrada` DISABLE KEYS */;
INSERT INTO `autostrada` VALUES (1,'A24',0.1,0.12,0.14,0.16,0.18),(2,'A14',0.1,0.14,0.18,0.2,0.22);
/*!40000 ALTER TABLE `autostrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `casello`
--

DROP TABLE IF EXISTS `casello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `casello` (
  `id` int(10) unsigned NOT NULL,
  `nome` varchar(45) NOT NULL,
  `chilometro` smallint(5) unsigned NOT NULL,
  `id_autostrada` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `casello_autostrada_idx` (`id_autostrada`),
  CONSTRAINT `casello_autostrada` FOREIGN KEY (`id_autostrada`) REFERENCES `autostrada` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `casello`
--

LOCK TABLES `casello` WRITE;
/*!40000 ALTER TABLE `casello` DISABLE KEYS */;
INSERT INTO `casello` VALUES (1,'Val Vomano',300,1),(2,'Colledara',320,1),(3,'Assergi',330,1),(7,'L\'Aquila Est',345,1),(9,'L\'Aquila Ovest',350,1),(77,'Roseto',85,2),(78,'Pineto',80,2),(98,'Tornimparte',370,1),(99,'Città Sant\'Angelo',60,2),(100,'Mosciano ',95,2),(101,'Val Vibrata',110,2);
/*!40000 ALTER TABLE `casello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `veicolo`
--

DROP TABLE IF EXISTS `veicolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `veicolo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `modello` varchar(45) NOT NULL,
  `marca` varchar(45) NOT NULL,
  `altezza` smallint(6) NOT NULL,
  `numero_assi` tinyint(4) NOT NULL,
  `classe_ambiente` tinyint(4) NOT NULL,
  `targa` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `targa_UNIQUE` (`targa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `veicolo`
--

LOCK TABLES `veicolo` WRITE;
/*!40000 ALTER TABLE `veicolo` DISABLE KEYS */;
INSERT INTO `veicolo` VALUES (1,'Panda','Fiat',120,2,4,'XX999XX');
/*!40000 ALTER TABLE `veicolo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-24 19:50:04
