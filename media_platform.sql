-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 19, 2025 at 09:26 PM
-- Server version: 8.3.0
-- PHP Version: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `media_platform`
--

-- --------------------------------------------------------

--
-- Table structure for table `advertisements`
--

DROP TABLE IF EXISTS `advertisements`;
CREATE TABLE IF NOT EXISTS `advertisements` (
  `AdvertisementID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL,
  `Description` text,
  `ImageURL` varchar(500) DEFAULT NULL,
  `TargetURL` varchar(500) DEFAULT NULL,
  `Status` enum('Active','Inactive') DEFAULT 'Active',
  `CreatedBy` int DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`AdvertisementID`),
  KEY `CreatedBy` (`CreatedBy`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `advertisements`
--

INSERT INTO `advertisements` (`AdvertisementID`, `Title`, `Description`, `ImageURL`, `TargetURL`, `Status`, `CreatedBy`, `CreatedAt`) VALUES
(1, 'Visit Rwanda - Land of Thousand Hills', 'Discover beautiful Rwanda', '/ads/visit_rwanda.jpg', 'https://www.visitrwanda.com', 'Active', 1, '2025-11-05 06:16:29'),
(2, 'Kigali Convention Center', 'Premium event venue', '/ads/kcc.jpg', 'https://www.kigaliconvention.com', 'Active', 1, '2025-11-05 06:16:29'),
(3, 'Visit Rwanda - Land of Thousand Hills', 'Discover beautiful Rwanda', '/ads/visit_rwanda.jpg', 'https://www.visitrwanda.com', 'Active', 1, '2025-11-05 06:26:03'),
(4, 'Kigali Convention Center', 'Premium event venue', '/ads/kcc.jpg', 'https://www.kigaliconvention.com', 'Active', 1, '2025-11-05 06:26:03');

-- --------------------------------------------------------

--
-- Table structure for table `articles`
--

DROP TABLE IF EXISTS `articles`;
CREATE TABLE IF NOT EXISTS `articles` (
  `ArticleID` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) NOT NULL,
  `Description` text,
  `Content` text,
  `CategoryID` int DEFAULT NULL,
  `PriceOrValue` decimal(10,2) DEFAULT NULL,
  `Status` enum('Draft','Published','Archived') DEFAULT 'Draft',
  `CreatedBy` int DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ArticleID`),
  KEY `CategoryID` (`CategoryID`),
  KEY `CreatedBy` (`CreatedBy`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `articles`
--

INSERT INTO `articles` (`ArticleID`, `Title`, `Description`, `Content`, `CategoryID`, `PriceOrValue`, `Status`, `CreatedBy`, `CreatedAt`) VALUES
(1, 'Rwanda Economic Growth Reaches 8%', 'Strong economic performance continues', 'Rwanda economy shows remarkable growth...', 2, 0.00, 'Published', 2, '2025-11-05 06:16:25'),
(2, 'Kwita Izina 2023 Celebrates Baby Gorillas', 'Annual gorilla naming ceremony', 'This years Kwita Izina ceremony celebrated...', 4, 0.00, 'Published', 2, '2025-11-05 06:16:25'),
(3, 'Kigali Innovation City Progress', 'Tech hub development underway', 'Kigali Innovation City is taking shape...', 5, 0.00, 'Draft', 2, '2025-11-05 06:16:25'),
(4, 'Rwanda Economic Growth Reaches 8%', 'Strong economic performance continues', 'Rwanda economy shows remarkable growth with GDP increasing by 8% this year. The government initiatives in agriculture and technology are paying off.', 2, 0.00, 'Published', 2, '2025-11-05 06:25:57'),
(5, 'Kwita Izina 2023 Celebrates Baby Gorillas', 'Annual gorilla naming ceremony', 'This years Kwita Izina ceremony celebrated 25 baby gorillas in Volcanoes National Park. The event highlights Rwanda commitment to conservation.', 4, 0.00, 'Published', 2, '2025-11-05 06:25:57'),
(6, 'Kigali Innovation City Progress', 'Tech hub development underway', 'Kigali Innovation City is taking shape with several tech companies already operating. This project aims to make Rwanda a tech hub in Africa.', 5, 0.00, 'Draft', 2, '2025-11-05 06:25:57');

-- --------------------------------------------------------

--
-- Table structure for table `articletags`
--

DROP TABLE IF EXISTS `articletags`;
CREATE TABLE IF NOT EXISTS `articletags` (
  `ArticleID` int NOT NULL,
  `TagID` int NOT NULL,
  PRIMARY KEY (`ArticleID`,`TagID`),
  KEY `TagID` (`TagID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `articletags`
--

INSERT INTO `articletags` (`ArticleID`, `TagID`) VALUES
(1, 1),
(1, 2),
(2, 4),
(3, 1),
(3, 5);

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `CategoryID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  `Description` text,
  `CreatedBy` int DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CategoryID`),
  KEY `CreatedBy` (`CreatedBy`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`CategoryID`, `Name`, `Description`, `CreatedBy`, `CreatedAt`) VALUES
(1, 'Politics', 'Rwandan political news and analysis', 1, '2025-11-05 06:16:25'),
(2, 'Economy', 'Economic developments in Rwanda', 1, '2025-11-05 06:16:25'),
(3, 'Culture', 'Rwandan culture and traditions', 1, '2025-11-05 06:16:25'),
(4, 'Tourism', 'Tourism attractions in Rwanda', 1, '2025-11-05 06:16:25'),
(5, 'Technology', 'Tech innovations in Rwanda', 1, '2025-11-05 06:16:25'),
(6, 'Politics', 'Rwandan political news and analysis', 1, '2025-11-05 06:25:57'),
(7, 'Economy', 'Economic developments in Rwanda', 1, '2025-11-05 06:25:57'),
(8, 'Culture', 'Rwandan culture and traditions', 1, '2025-11-05 06:25:57'),
(9, 'Tourism', 'Tourism attractions in Rwanda', 1, '2025-11-05 06:25:57'),
(10, 'Technology', 'Tech innovations in Rwanda', 1, '2025-11-05 06:25:57');

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `CommentID` int NOT NULL AUTO_INCREMENT,
  `ArticleID` int DEFAULT NULL,
  `UserID` int DEFAULT NULL,
  `Content` text NOT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CommentID`),
  KEY `ArticleID` (`ArticleID`),
  KEY `UserID` (`UserID`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`CommentID`, `ArticleID`, `UserID`, `Content`, `CreatedAt`) VALUES
(1, 1, 3, 'Great news for our economy!', '2025-11-05 06:16:25'),
(2, 2, 3, 'Beautiful ceremony, proud of our conservation efforts.', '2025-11-05 06:16:25'),
(3, 1, 3, 'Great news for our economy! Keep up the good work.', '2025-11-05 06:25:57'),
(4, 2, 3, 'Beautiful ceremony, proud of our conservation efforts.', '2025-11-05 06:25:57');

-- --------------------------------------------------------

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
CREATE TABLE IF NOT EXISTS `tags` (
  `TagID` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(50) NOT NULL,
  `Description` text,
  `CreatedBy` int DEFAULT NULL,
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TagID`),
  KEY `CreatedBy` (`CreatedBy`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `tags`
--

INSERT INTO `tags` (`TagID`, `Name`, `Description`, `CreatedBy`, `CreatedAt`) VALUES
(1, 'Kigali', 'News from Kigali city', 1, '2025-11-05 06:16:25'),
(2, 'Agriculture', 'Agricultural developments', 1, '2025-11-05 06:16:25'),
(3, 'Umuganda', 'Community work activities', 1, '2025-11-05 06:16:25'),
(4, 'Gorillas', 'Mountain gorilla conservation', 1, '2025-11-05 06:16:25'),
(5, 'ICT', 'Information technology news', 1, '2025-11-05 06:16:25'),
(6, 'Kigali', 'News from Kigali city', 1, '2025-11-05 06:25:57'),
(7, 'Agriculture', 'Agricultural developments', 1, '2025-11-05 06:25:57'),
(8, 'Umuganda', 'Community work activities', 1, '2025-11-05 06:25:57'),
(9, 'Gorillas', 'Mountain gorilla conservation', 1, '2025-11-05 06:25:57'),
(10, 'ICT', 'Information technology news', 1, '2025-11-05 06:25:57');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `UserID` int NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) NOT NULL,
  `PasswordHash` varchar(255) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `FullName` varchar(100) NOT NULL,
  `Role` enum('Admin','Editor','Viewer') DEFAULT 'Viewer',
  `CreatedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `LastLogin` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE KEY `Username` (`Username`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Username`, `PasswordHash`, `Email`, `FullName`, `Role`, `CreatedAt`, `LastLogin`) VALUES
(1, 'admin', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'admin@rwandamedia.rw', 'System Administrator', 'Admin', '2025-11-05 06:16:25', NULL),
(2, 'editor1', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'editor@newtimes.rw', 'Jean de Dieu', 'Editor', '2025-11-05 06:16:25', NULL),
(3, 'viewer1', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'reader@igihe.rw', 'Marie Claire', 'Viewer', '2025-11-05 06:16:25', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
