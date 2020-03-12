-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 12, 2020 at 01:07 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospitalris`
--

-- --------------------------------------------------------

--
-- Table structure for table `patient_information`
--

CREATE TABLE `patient_information` (
  `First_Name` varchar(50) DEFAULT NULL,
  `Middle_initial` varchar(5) DEFAULT NULL,
  `Last_Name` varchar(50) DEFAULT NULL,
  `birthMonth` int(2) DEFAULT NULL,
  `birthYear` int(4) NOT NULL,
  `birthDay` int(2) NOT NULL,
  `City` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `zipcode` int(12) NOT NULL,
  `employerName` varchar(30) NOT NULL,
  `workPhoneNumber` int(25) NOT NULL,
  `homePhoneNumber` int(25) NOT NULL,
  `socSecNum` int(10) NOT NULL,
  `employerAddress` varchar(50) NOT NULL,
  `employerCity` varchar(50) NOT NULL,
  `employerZip` int(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_information`
--

INSERT INTO `patient_information` (`First_Name`, `Middle_initial`, `Last_Name`, `birthMonth`, `birthYear`, `birthDay`, `City`, `address`, `zipcode`, `employerName`, `workPhoneNumber`, `homePhoneNumber`, `socSecNum`, `employerAddress`, `employerCity`, `employerZip`) VALUES
('Erick', 'A', 'Ortiz', 12, 12, 12, 'Suwanee', '2217 Suwanee Road', 30506, 'UNG', 11111111, 21312313, 32213213, 'UNG Dahlonega', 'Dahlonega', 32131);

-- --------------------------------------------------------

--
-- Table structure for table `responsible_information`
--

CREATE TABLE `responsible_information` (
  `ResponsibleName` varchar(30) NOT NULL,
  `responsibleRelationship` varchar(30) NOT NULL,
  `responsibleAddressInput` varchar(30) NOT NULL,
  `responsibleCity` varchar(30) NOT NULL,
  `responsibleZipCode` int(12) NOT NULL,
  `responsibleWorkNumber` int(25) NOT NULL,
  `responsibleHomeNumber` int(25) NOT NULL,
  `responsibleSSN` int(10) NOT NULL,
  `dependentSSN` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `patient_information`
--
ALTER TABLE `patient_information`
  ADD PRIMARY KEY (`socSecNum`);

--
-- Indexes for table `responsible_information`
--
ALTER TABLE `responsible_information`
  ADD PRIMARY KEY (`responsibleSSN`),
  ADD KEY `dependentSSN` (`dependentSSN`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `responsible_information`
--
ALTER TABLE `responsible_information`
  ADD CONSTRAINT `responsible_information_ibfk_1` FOREIGN KEY (`dependentSSN`) REFERENCES `patient_information` (`socSecNum`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
