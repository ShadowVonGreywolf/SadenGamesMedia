-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: sgmdatabase
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `role` enum('user','admin') DEFAULT 'user',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `imagePath` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,'admin','admin@gmail.com','admin','admin','2025-07-04 16:51:25','profile_images/user_1.png'),(46,'ShadowVonGreywolf','shadowvongreywolf@gmail.com','shadyBastard','user','2025-07-09 14:53:56','profile_images/user_46.png'),(57,'Denis','denis@gmail.com','parola','user','2025-07-10 11:05:48',NULL),(58,'Korias','koriasf@gmail.com','kingdomMaker','user','2025-07-10 11:07:07','profile_images/user_58.png'),(59,'Sassan','sassanid@gmail.com','E=mc^2','user','2025-07-10 11:11:38',NULL);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `games`
--

DROP TABLE IF EXISTS `games`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `games` (
  `games_id` int NOT NULL,
  `platform` varchar(20) DEFAULT NULL,
  `studio` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`games_id`),
  CONSTRAINT `games_ibfk_1` FOREIGN KEY (`games_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `games`
--

LOCK TABLES `games` WRITE;
/*!40000 ALTER TABLE `games` DISABLE KEYS */;
INSERT INTO `games` VALUES (1,'PS4','Activision'),(2,'PS4','Ubisoft'),(3,'Xbox One','Klei Entertainmnent'),(4,'Xbox One','Ubisoft'),(7,'PS4','Santa Monica'),(10,'PS4','Sucker Punch Production'),(11,'PS4','CD Projekt Red'),(14,'PS4','Rockstar North'),(15,'Xbox One','Rocksteady Studios'),(16,'XBox One','FromSoftware'),(29,'Xbox One','MachineGames');
/*!40000 ALTER TABLE `games` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movies`
--

DROP TABLE IF EXISTS `movies`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movies` (
  `movie_id` int NOT NULL,
  `director` varchar(100) DEFAULT NULL,
  `duration` int DEFAULT NULL,
  PRIMARY KEY (`movie_id`),
  CONSTRAINT `movies_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `products` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movies`
--

LOCK TABLES `movies` WRITE;
/*!40000 ALTER TABLE `movies` DISABLE KEYS */;
INSERT INTO `movies` VALUES (5,'Joe Russo',143),(9,'Gary Shore',92),(13,'Stephen Sommers',124),(27,'Michael Curtiz',102);
/*!40000 ALTER TABLE `movies` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `genre` varchar(20) NOT NULL,
  `rating` float NOT NULL,
  `description` text,
  `price` decimal(10,2) NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `product_type` varchar(20) NOT NULL,
  `stock` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'Call of Duty WWII','Historical',8.5,'Call of Duty: WWII brings the franchise back to its roots, offering a powerful reimagining of World War II for a new generation. You’ll take part in iconic battles across Europe—from the Normandy beaches to the Hürtgen Forest—through the eyes of a young recruit. This gripping campaign delivers the emotional highs and brutal realities of the war that shaped history.',23.99,'images/COD.png','Videogame',10),(2,'Far Cry 3','Survival, action',10,'Far Cry 3 Classic Edition brings the iconic Rook Island to next-gen consoles for the first time. Uncover the island’s bloody secrets and fight ruthless enemies with cunning and creativity, using the environment to your advantage. To rescue your friends from disturbed and dangerous locals, you’ll need more than luck—survival demands wit, instinct, and a touch of madness.',20.87,'images/farCry3.png','Videogame',10),(3,'Don\'t Starve Together','Survival',5,'Don\'t Starve Together is a multiplayer survival adventure where players are dropped into a mysterious, hand-drawn wilderness teeming with bizarre creatures, ancient secrets, and relentless dangers. You’ll gather resources, craft tools, build shelters, and cooperate (or compete) with others to stay alive through harsh seasons and supernatural threats. Whether you explore solo or with friends, the goal is simple but brutal: don’t starve—together.',22.00,'images/Don\'t Starve Together.png','Videogame',7),(4,'FAR CRY 6','Survival',4.4,'Far Cry 6 immerses players in the turbulent world of modern guerrilla warfare on the tropical island of Yara. As Dani Rojas, a local rebel, you\'ll confront the tyrannical rule of Anton Castillo through explosive combat and strategic resistance. Armed with improvised weapons, wild vehicles, and unexpected allies—including animal companions—you’ll lead a revolution to liberate your homeland.',32.00,'images/FarCry6.png','Videogame',9),(5,'The Avengers','Action',8,'Earth\'s mightiest heroes must come together and learn to fight as a team if they are going to stop the mischievous Loki and his alien army from enslaving humanity.',12.00,'images/avengers.png','Movie',8),(7,'God of War','Action-adventure',9.5,'A gripping journey through Norse mythology, God of War follows Kratos and his son Atreus as they battle gods and monsters across the Nine Realms. With cinematic storytelling, brutal combat, and emotional depth, this reimagining of the franchise delivers a powerful tale of redemption and fatherhood.',59.99,'product_images/product_7_1752489704938.png','Videogame',20),(9,'Dracula Untold','Fantasy / Horror',6.2,'To save his family and kingdom from invading Turks, Prince Vlad Tepes makes a dark pact that transforms him into the legendary vampire Dracula. This origin tale fuses war, mythology, and supernatural power, depicting the tragic rise of a monster feared by the very people he swore to protect.',12.87,'product_images/product_9_1752490963266.png','Movie',20),(10,'Ghost of Tsushima','Action/Stealth',9,'Set in 13th-century Japan, Ghost of Tsushima follows samurai Jin Sakai as he defends his homeland from Mongol invaders. Torn between the path of honor and the way of the Ghost, players explore a vast, beautifully crafted open world filled with intense combat, stealth gameplay, and unforgettable storytelling.',45.99,'product_images/product_10_1752490135524.png','Videogame',10),(11,'The Witcher 3:Wild Hunt','RPG / Adventure',10,'Step into the boots of Geralt of Rivia, a monster hunter navigating a morally complex world inspired by Slavic mythology. In this award-winning open-world RPG, you’ll track down your missing adopted daughter while battling beasts, unraveling political intrigue, and shaping the fate of kingdoms through your choices.',60.00,'product_images/product_11_1752490505056.png','Videogame',25),(13,'The Mummy','Adventure/Fantasy',7.1,'Set in 1926 Egypt, this thrilling adventure follows Rick O’Connell and librarian Evelyn Carnahan as they accidentally awaken a cursed high priest, Imhotep. With ancient plagues unleashed and a race against time, they must stop the resurrected mummy before he regains full power and brings destruction to the world.',12.50,'product_images/product_13_1752490726045.png','Movie',15),(14,'Grand Theft Auto V','Open-world',9.7,'Dive into the sprawling city of Los Santos with three distinct protagonists—Michael, Franklin, and Trevor—as they pull off high-stakes heists and navigate a world of crime, corruption, and chaos. With a massive open world, cinematic storytelling, and a dynamic online mode, GTA V redefines the sandbox genre.',59.99,'product_images/product_14_1752491353205.png','Videogame',12),(15,'Batman: Arkham Knight','Superhero/Action',8.5,'In the explosive finale to the Arkham trilogy, Batman faces his greatest threat yet as Scarecrow unites Gotham’s most notorious villains to unleash fear across the city. With the debut of the Batmobile as a playable vehicle and seamless dual-play combat with allies like Robin and Nightwing, players dive into a dark, cinematic open-world experience that tests the limits of the Dark Knight’s resolve.',42.79,'product_images/product_15_1752491490179.png','Videogame',9),(16,'Elden Ring','RPG/Fantasy',10,'Enter the vast, mysterious Lands Between in Elden Ring, a dark fantasy RPG crafted by FromSoftware and George R. R. Martin. As a Tarnished warrior guided by grace, you’ll explore haunting landscapes, battle grotesque foes, and uncover ancient secrets in a world where every choice shapes your fate. With deep lore, punishing combat, and boundless freedom, this is a journey unlike any other.',39.99,'product_images/product_16_1752491591850.png','Videogame',10),(27,'The Adventures of Robin Hood','Action/Romance',7.9,'In 12th-century England, as Prince John oppresses the Saxon people during King Richard’s absence, nobleman Robin Hood rises as an outlaw leader of a daring resistance. With swashbuckling action, vibrant Technicolor visuals, and unforgettable performances by Errol Flynn and Olivia de Havilland, this classic Hollywood adventure remains a timeless tale of heroism, rebellion, and romance.',18.00,'product_images/product_27_1752491842503.png','Movie',10),(29,'Wolfenstein: The New Order','FPS/ Action',8.2,'Set in an alternate 1960s where the Nazis won World War II, Wolfenstein: The New Order follows war hero B.J. Blazkowicz as he joins a resistance movement to overthrow the regime. With intense gunplay, stealth mechanics, and a gripping narrative, the game blends retro sci-fi with brutal combat in a dystopian world ruled by fear and technology.',38.99,'product_images/product_29_1752492005397.png','Videogame',10);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sales`
--

DROP TABLE IF EXISTS `sales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales` (
  `sale_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `account_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `price` decimal(10,2) DEFAULT NULL,
  `total_amount` decimal(10,2) GENERATED ALWAYS AS ((`quantity` * `price`)) STORED,
  `sale_timestamp` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`sale_id`),
  KEY `product_id` (`product_id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `sales_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`),
  CONSTRAINT `sales_ibfk_2` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sales`
--

LOCK TABLES `sales` WRITE;
/*!40000 ALTER TABLE `sales` DISABLE KEYS */;
INSERT INTO `sales` (`sale_id`, `product_id`, `account_id`, `quantity`, `price`, `sale_timestamp`) VALUES (1,1,46,1,79.99,'2025-07-12 23:45:38'),(2,1,46,3,79.99,'2025-07-12 23:45:56'),(4,15,58,1,222.00,'2025-07-13 18:27:37'),(5,7,58,1,99.99,'2025-07-13 18:27:45'),(8,29,46,2,123.00,'2025-07-13 19:27:52'),(9,3,46,3,89.00,'2025-07-13 19:29:41');
/*!40000 ALTER TABLE `sales` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-14 14:46:23
