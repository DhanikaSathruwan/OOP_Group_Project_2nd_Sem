-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 18, 2026 at 04:06 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `myjavaproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `class_id` int(11) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `target_year` varchar(20) NOT NULL,
  `lesson_month` varchar(150) NOT NULL,
  `class_time` varchar(100) NOT NULL,
  `fee` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class_id`, `subject`, `target_year`, `lesson_month`, `class_time`, `fee`) VALUES
(1, 'AL SFT (Science for Technology)', '2026', 'January - Fundamental Measurements', 'Saturday 8.00 AM - 12.00 PM', 'Rs. 2500'),
(2, 'AL SFT (Science for Technology)', '2027', 'February - Mechanics & Force', 'Sunday 8.00 AM - 12.00 PM', 'Rs. 2500'),
(3, 'AL ICT (Information Comm. Tech.)', '2026', 'January - Digital Logic Gates', 'Saturday 1.30 PM - 5.30 PM', 'Rs. 2500'),
(4, 'AL ICT (Information Comm. Tech.)', '2027', 'March - SQL & Database Design', 'Sunday 1.30 PM - 5.30 PM', 'Rs. 2500'),
(5, 'AL ICT (Information Comm. Tech.)', '2027', 'March - SQL & Database Design', 'Sunday 1.30 PM - 5.30 PM', 'Rs. 2500'),
(6, 'AL ET (Engineering Tech.)', '2028', 'March - Engineering Drawings', 'Saturday 1.30 PM - 5.30 PM', 'Rs. 2500');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `user_email` varchar(100) NOT NULL,
  `payment_date` date NOT NULL,
  `subject` varchar(100) NOT NULL,
  `target_year` varchar(20) NOT NULL,
  `lesson_name` varchar(150) NOT NULL,
  `payment_method` varchar(50) NOT NULL,
  `amount` varchar(50) NOT NULL,
  `slip_path` varchar(500) NOT NULL,
  `status` varchar(50) DEFAULT 'Pending Approval'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `nic` varchar(20) NOT NULL,
  `target_year` varchar(20) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `institute` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `photo_path` varchar(500) DEFAULT 'profile_placeholder.png',
  `city` varchar(100) DEFAULT 'Badulla',
  `address` varchar(255) DEFAULT '',
  `school` varchar(150) DEFAULT '',
  `role` varchar(20) DEFAULT 'STUDENT'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `nic`, `target_year`, `mobile`, `email`, `institute`, `password`, `photo_path`, `city`, `address`, `school`, `role`) VALUES
(1, 'System', 'Admin', '200505800879', 'All', '0712345678', 'admin@edu.lk', 'Main Institute', 'admin123', 'profile_placeholder.png', 'Badulla', '', '', 'ADMIN');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
  ADD PRIMARY KEY (`class_id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
  MODIFY `class_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
