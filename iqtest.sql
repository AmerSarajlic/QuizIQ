-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: iqtest
-- ------------------------------------------------------
-- Server version	5.7.18-0ubuntu0.16.04.1

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

CREATE DATABASE IF NOT EXISTS iqtest;
USE iqtest;
--
-- Table structure for table `Admins`
--

DROP TABLE IF EXISTS `Admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Admins` (
  `name` varchar(25) NOT NULL,
  `password` varchar(45) NOT NULL,
  `dateAdded` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `moderator` int(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Admins`
--

LOCK TABLES `Admins` WRITE;
/*!40000 ALTER TABLE `Admins` DISABLE KEYS */;
INSERT INTO `Admins` VALUES ('amer','amer','2017-05-25 21:56:54',2);
/*!40000 ALTER TABLE `Admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Answers`
--

DROP TABLE IF EXISTS `Answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Answers` (
  `idAnswers` int(11) NOT NULL AUTO_INCREMENT,
  `a` varchar(255) NOT NULL,
  `b` varchar(255) NOT NULL,
  `c` varchar(255) NOT NULL,
  `d` varchar(255) NOT NULL,
  `correctAnswer` varchar(1) NOT NULL,
  `Questions_idQuestions` int(11) NOT NULL,
  `explanation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idAnswers`,`Questions_idQuestions`),
  KEY `fk_Answers_Questions1_idx` (`Questions_idQuestions`),
  CONSTRAINT `fk_Answers_Questions1` FOREIGN KEY (`Questions_idQuestions`) REFERENCES `Questions` (`idQuestions`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Answers`
--

LOCK TABLES `Answers` WRITE;
/*!40000 ALTER TABLE `Answers` DISABLE KEYS */;
INSERT INTO `Answers` VALUES (56,'4','4.5','5','5.5','c',55,'With 4 trips he can carry 16 bottles. For the 17th bottle, he will have to make fifth trip. There is no such thing as half trip.'),(57,'true','false','-----','-----','a',56,'The letters of the word \'sponge\' do appear in the reverse alphabetical order.'),(58,'true','false','----','----','b',57,'First saturday would be sixth day. Thus, next saturday would be thirteenth day of the month.'),(59,'true','false','----','----','b',58,'It is the letter \'e\'.'),(60,'2','5','7','9','c',59,'A circle is made of one continuous curve. Heptagon has seven sides, therefore it is made of 7 different curves.'),(61,'true','false','----','----','a',60,'P --> Q R S T <-- U'),(62,'54','60','75','63','d',61,'Every number is three times the number two positions before it. The next number would be 21 x 3 = 63.'),(63,'3500','3600','----','----','b',62,'Amer likes perfect squares. 3600 is perfect square.'),(64,'Car','Swimming pool','Aeroplane','Football stadium','c',63,'We get the word \'PARACHUTE\' which is used in an aeroplane.'),(65,'True','False','----','----','a',64,'A day contains 24 hours. 16:24 is a ratio of 2:3. June contains 30 days. A 20:30 is also 2:3.'),(67,'14','15','17','18','c',66,'The series is a sequence of prime numbers. The next prime number after 13 is 17.'),(68,'55','60','64','72','c',67,'The numbers are perfect cubes.'),(69,'27594','27954','79254','24957','a',68,'The letter of \'ALERT\' are jumbled in the same way to form letter \'LATER\' as numbers 72945 are jumbled to form 27594.'),(70,'26','24','20','28','a',69,'Hypotenuse of triangle with sides 24 and 10 is 26.'),(71,'True','False','----','----','b',70,'MOTHERHOOD contains ten letters. You need to remove six letters from it to arrive at the word HOME'),(72,'60','75','90','100','d',71,'6 people will decorate 30 cupcakes in the same 18 minutes. At this rate, they decorate 10 cupcakes in 6 minutes. Hence in 60 minutes they can decorate 100 cupcakes.'),(73,'Poem','Building','Flower','Statue','c',72,'All the objects except \'Flower\' are man made.'),(74,'10, 5','11, 5','10, 4','11, 6','b',73,'The number at the odd positions are increasing by 2, and at the even positions are decreasing by 1.'),(75,'120','150','165','180','c',74,'A hand rotates through 360 degrees when it covers all 12 numbers. Thous, one number/hour is 30 degrees, half hour is 15 degrees. 5 x 30 = 150 + 15 = 165.'),(76,'True','False','----','----','a',75,'Triangle 3 sides, Glove 5 fingers, Bicycle 2 wheels, Octopus 8 arms.  '),(77,'1/3','2/8','1/8','1/6','c',76,'Each number is one half of the previous number. One half of 1/4 is 1/8.'),(78,'21','42','12','6','a',77,'There are total 7 people. The first person will perform 6 unique handshakes, second 5, third 4 etc. 6 + 5 + 4 + 3 + 2 +1 = 21.'),(79,'True','False','----','----','a',78,'Write on peace of paper and you will see :) .'),(80,'True','False','Uncertain','----','b',79,'If Tanya is older than Eric, and Cliff is older than Tany, Eric cannot be older than Cliff.'),(81,'7','8','15','30','b',80,'The series has a pattern of \'plus1\' , \'multiply by 2\' .'),(82,'15','7','10','14','a',81,'The differences between the numbers in the series alternate between 1 and 3.'),(83,'18','25','28','32','c',82,'2 + 3 + 5 + 7 + 11 = 28 '),(84,'750','875','1000','1050','c',83,'In two thirds of the time (40 seconds) train will cover two thirds of the distance (1000 meters).'),(85,'City','Metal','State','Bird','d',84,'PARAKEET is a bird.'),(86,'600','240','180','100','b',85,'The newspaper man has 60% of his newspapers left, which amount to 360. 360 is 60% of 600. Thus, he sold 600 - 360 = 240 newspapers.');
/*!40000 ALTER TABLE `Answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Questions`
--

DROP TABLE IF EXISTS `Questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Questions` (
  `idQuestions` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) NOT NULL,
  `Admins_name` varchar(25) NOT NULL,
  PRIMARY KEY (`idQuestions`,`Admins_name`),
  KEY `fk_Questions_Admins1_idx` (`Admins_name`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Questions`
--

LOCK TABLES `Questions` WRITE;
/*!40000 ALTER TABLE `Questions` DISABLE KEYS */;
INSERT INTO `Questions` VALUES (55,'John has to buy 17 bottles, but he can carry only 4 at a time. The minimum number of trips he has to make to get all bottles is:','amer'),(56,'The letters of the word \'sponge\' appear in the reverse alphabetical order (true or false) ?','amer'),(57,'If monday is the first day of the month, the second Saturday after that monday is the fourteenth day of the month (true or false) ?','amer'),(58,'The seventh vowel appearing in this sentence is the letter \'a\' (true or false) ?','amer'),(59,'If a circle is one, how many is an heptagon ?','amer'),(60,'In the English aplhabet, there are exactly four letters between \'P\' and \'U\' (true or false) ?','amer'),(61,'Find the next number in the serie 5, 7, 15, 21, 45, ___ ?','amer'),(62,'Amer likes 324 but not 325; he likes 900 but not 800; he likes 169 but not 170. Which does he like 3600 or 3500 ?','amer'),(63,'When the following letters \'H C P R A A T E U\' are unscrambled, what you get can be used in a: ','amer'),(64,'Sixteen hours are to one day as twenty days are to June\'s length (true or false) ?','amer'),(66,'Which number comes next in the series of numbers \'2, 3, 5, 7, 11, 13, ___ \' ?','amer'),(67,'Following the patter shown in the number sequence \' 1, 8, 27, ___ , 125, 216 \' what is the missing number ?','amer'),(68,'ALERT is to LATER as 72945 it to ?','amer'),(69,'Amer and Ensar, starting at the same point, walk in opposite directions for 12 meters, turn left and walk another 5 meters. What is the distance between them ?','amer'),(70,'By removing seven letters from the word \' MOTHERHOOD \', the word \' HOME \' can be formed (true or false) ?','amer'),(71,'It takes 3 people 18 minutes to decorate 15 cupcakes. How many cupcakes can 6 people complete within an hour ?','amer'),(72,'Which of the following objects is least like the others ?','amer'),(73,'Continue the following number series with the pair of numbers which best continues the series \' 1 10 3 9 5 8 7 7 9 6 ___ ___:','amer'),(74,'A clock shows 2pm. Through how many degrees has the hour hand rotated when the clock is showing 7:30pm ?','amer'),(75,'The sequence: \'Triangle\', \'Glove\', \'Clock\', \'Bicycle\', \'Octopus\', corresponds to 3, 5, 12, 2, 8 (true or false)?','amer'),(76,'What number should come next in the series 2, 1, 1/2, 1/4, ___ ?','amer'),(77,'If each person shakes hands with six other persons, how many unique handshakes were performed in total ?','amer'),(78,'If the word \'TAN\' is written under the word \'SLY\' and the word \'TOT\' is written under \'TAN\', then the word \'SAT\' is formed diagonally (true or false) ?','amer'),(79,'Tanya is older than Eric. Cliff is older than Tanya. Eric is older than Cliff. If the first two statements are true, the third statement is: ','amer'),(80,'Which of the following does not belong to the series: 2, 3, 6, 8, 14, 15, 30 ?','amer'),(81,'Which number comes next in the series: 3, 4, 7, 8, 11, 12, ___ ?','amer'),(82,'The sum of the first five prime numbers is: ','amer'),(83,'The train covers 1.5 kilometers in 1 minute. How many meters does it cover in 40 seconds ?','amer'),(84,'If you rearrange the letters \'ETKARPEA\', you would have the name of a:','amer'),(85,'A newspaper man sold 40% of his newspapers today. He now has 360 newspaper left. How many newspapers did he sell today ?','amer');
/*!40000 ALTER TABLE `Questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ScoreBoard`
--

DROP TABLE IF EXISTS `ScoreBoard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ScoreBoard` (
  `idScoreBoard` int(11) NOT NULL AUTO_INCREMENT,
  `score` int(11) NOT NULL,
  `Users_name` varchar(25) NOT NULL,
  `dateAdded` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idScoreBoard`,`Users_name`),
  KEY `fk_ScoreBoard_Users_idx` (`Users_name`),
  CONSTRAINT `fk_ScoreBoard_Users` FOREIGN KEY (`Users_name`) REFERENCES `Users` (`name`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ScoreBoard`
--

LOCK TABLES `ScoreBoard` WRITE;
/*!40000 ALTER TABLE `ScoreBoard` DISABLE KEYS */;
/*!40000 ALTER TABLE `ScoreBoard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Users` (
  `name` varchar(25) NOT NULL,
  `password` int(11) NOT NULL,
  `dateRegistered` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'iqtest'
--

--
-- Dumping routines for database 'iqtest'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-01 16:15:15
