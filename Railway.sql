-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 28, 2021 at 08:57 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `Railway`
--

-- --------------------------------------------------------

--
-- Table structure for table `Routes`
--

CREATE TABLE `Routes` (
  `rt_id` int(11) NOT NULL,
  `dist` int(11) DEFAULT NULL,
  `fares` int(11) DEFAULT NULL,
  `dep_time` time DEFAULT NULL,
  `s_stID` int(11) DEFAULT NULL,
  `e_stID` int(11) DEFAULT NULL,
  `tr_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Routes`
--

INSERT INTO `Routes` (`rt_id`, `dist`, `fares`, `dep_time`, `s_stID`, `e_stID`, `tr_id`) VALUES
(6000, 367, 300, '08:00:00', 7000, 7001, 9000),
(6001, 367, 300, '20:00:00', 7000, 7001, 9003),
(6002, 70, 65, '08:30:00', 7005, 7000, 9002);

-- --------------------------------------------------------

--
-- Stand-in structure for view `rtView`
-- (See below for the actual view)
--
CREATE TABLE `rtView` (
`rt_id` int(11)
,`dist` int(11)
,`fares` int(11)
,`dep_time` time
,`startID` int(11)
,`startName` varchar(25)
,`endID` int(11)
,`endName` varchar(25)
,`trainID` int(11)
,`trainName` varchar(25)
);

-- --------------------------------------------------------

--
-- Table structure for table `Station`
--

CREATE TABLE `Station` (
  `st_id` int(11) NOT NULL,
  `st_name` varchar(25) DEFAULT NULL,
  `st_tel` varchar(13) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Station`
--

INSERT INTO `Station` (`st_id`, `st_name`, `st_tel`) VALUES
(7000, 'Manglore', '8695765412'),
(7001, 'Banglore', '9674582130'),
(7002, 'Udupi', '8796584631'),
(7003, 'Ujire', '8695743085'),
(7004, 'Dharmastala', '8675943820'),
(7005, 'Kakkinje', '7685436790');

-- --------------------------------------------------------

--
-- Table structure for table `Tickets`
--

CREATE TABLE `Tickets` (
  `tk_id` int(11) NOT NULL,
  `ps_name` varchar(25) DEFAULT NULL,
  `ps_age` int(11) DEFAULT NULL,
  `ps_gender` varchar(5) DEFAULT NULL,
  `platform` varchar(10) DEFAULT NULL,
  `tk_class` varchar(10) DEFAULT NULL,
  `amt` int(11) DEFAULT NULL,
  `tk_date` date DEFAULT NULL,
  `rt_id` int(11) DEFAULT NULL,
  `seat_no` varchar(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Tickets`
--

INSERT INTO `Tickets` (`tk_id`, `ps_name`, `ps_age`, `ps_gender`, `platform`, `tk_class`, `amt`, `tk_date`, `rt_id`, `seat_no`) VALUES
(1000, 'Dwayne Jhonson', 48, 'M', '1', 'SL', 450, '2021-01-10', 6000, '6'),
(1001, 'Robert Downey', 22, 'M', '3', '1A', 400, '2021-01-20', 6001, '22');

-- --------------------------------------------------------

--
-- Stand-in structure for view `tkView`
-- (See below for the actual view)
--
CREATE TABLE `tkView` (
`tk_id` int(11)
,`ps_name` varchar(25)
,`ps_age` int(11)
,`ps_gender` varchar(5)
,`platform` varchar(10)
,`tk_class` varchar(10)
,`tk_date` date
,`amt` int(11)
,`rt_id` int(11)
,`start_st` varchar(25)
,`end_st` varchar(25)
,`seat_no` varchar(5)
);

-- --------------------------------------------------------

--
-- Table structure for table `Train`
--

CREATE TABLE `Train` (
  `Tr_id` int(11) NOT NULL,
  `Tr_name` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `Train`
--

INSERT INTO `Train` (`Tr_id`, `Tr_name`) VALUES
(9000, 'Phoenix'),
(9001, 'Thunder Bird'),
(9002, 'Mockingjay'),
(9003, 'Nimbus');

-- --------------------------------------------------------

--
-- Table structure for table `User`
--

CREATE TABLE `User` (
  `user_id` varchar(15) NOT NULL,
  `user_name` varchar(25) DEFAULT NULL,
  `user_pass` varchar(15) DEFAULT NULL,
  `user_mail` varchar(30) DEFAULT NULL,
  `user_add` varchar(50) DEFAULT NULL,
  `user_phone` varchar(15) DEFAULT NULL,
  `user_uid` decimal(15,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `User`
--

INSERT INTO `User` (`user_id`, `user_name`, `user_pass`, `user_mail`, `user_add`, `user_phone`, `user_uid`) VALUES
('418019', 'Deepak Sharma', 'deepak19', 'deepus656@gmail.com', 'Vitla', '8762598330', '432198765012'),
('418069', 'Rachan N Jadav', 'rachan069', 'rachan2000@gmail.com', 'kasargod', '7658913204', '956874582325'),
('418076', 'Sahan KJ', 'sahan076', 'sahan.k.j2000@gmail.com', 'Neria', '9591594510', '652894120546'),
('418083', 'Shibin Joseph', 'shibin083', 'shibinjoseph1999@gmail.com', 'Kannur', '65874623135', '203145269874'),
('418096', 'Sujay Naik', 'sujay096', 'sujaynaik2K@gmail.com', 'UK', '9876543210', '298765410230');

-- --------------------------------------------------------

--
-- Structure for view `rtView`
--
DROP TABLE IF EXISTS `rtView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `rtView`  AS SELECT `R`.`rt_id` AS `rt_id`, `R`.`dist` AS `dist`, `R`.`fares` AS `fares`, `R`.`dep_time` AS `dep_time`, `S`.`startID` AS `startID`, `S`.`startName` AS `startName`, `E`.`endID` AS `endID`, `E`.`endName` AS `endName`, `T`.`trainID` AS `trainID`, `T`.`trainName` AS `trainName` FROM (((`Routes` `R` join (select `Station`.`st_id` AS `startID`,`Station`.`st_name` AS `startName` from `Station`) `S`) join (select `Station`.`st_id` AS `endID`,`Station`.`st_name` AS `endName` from `Station`) `E`) join (select `Train`.`Tr_id` AS `trainID`,`Train`.`Tr_name` AS `trainName` from `Train`) `T`) WHERE `R`.`s_stID` = `S`.`startID` AND `R`.`e_stID` = `E`.`endID` AND `R`.`tr_id` = `T`.`trainID` ;

-- --------------------------------------------------------

--
-- Structure for view `tkView`
--
DROP TABLE IF EXISTS `tkView`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `tkView`  AS SELECT `T`.`tk_id` AS `tk_id`, `T`.`ps_name` AS `ps_name`, `T`.`ps_age` AS `ps_age`, `T`.`ps_gender` AS `ps_gender`, `T`.`platform` AS `platform`, `T`.`tk_class` AS `tk_class`, `T`.`tk_date` AS `tk_date`, `T`.`amt` AS `amt`, `R`.`rt_id` AS `rt_id`, `R`.`start_st` AS `start_st`, `R`.`end_st` AS `end_st`, `T`.`seat_no` AS `seat_no` FROM (`Tickets` `T` join (select `RO`.`rt_id` AS `rt_id`,`S`.`s_stName` AS `start_st`,`E`.`e_stName` AS `end_st` from ((`Routes` `RO` join (select `Station`.`st_id` AS `st_id`,`Station`.`st_name` AS `s_stName` from `Station`) `S`) join (select `Station`.`st_id` AS `st_id`,`Station`.`st_name` AS `e_stName` from `Station`) `E`) where `S`.`st_id` = `RO`.`s_stID` and `E`.`st_id` = `RO`.`e_stID`) `R`) WHERE `R`.`rt_id` = `T`.`rt_id` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Routes`
--
ALTER TABLE `Routes`
  ADD PRIMARY KEY (`rt_id`),
  ADD KEY `s_stID` (`s_stID`),
  ADD KEY `e_stID` (`e_stID`),
  ADD KEY `tr_id` (`tr_id`);

--
-- Indexes for table `Station`
--
ALTER TABLE `Station`
  ADD PRIMARY KEY (`st_id`);

--
-- Indexes for table `Tickets`
--
ALTER TABLE `Tickets`
  ADD PRIMARY KEY (`tk_id`),
  ADD KEY `rt_id` (`rt_id`);

--
-- Indexes for table `Train`
--
ALTER TABLE `Train`
  ADD PRIMARY KEY (`Tr_id`);

--
-- Indexes for table `User`
--
ALTER TABLE `User`
  ADD PRIMARY KEY (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Routes`
--
ALTER TABLE `Routes`
  ADD CONSTRAINT `Routes_ibfk_1` FOREIGN KEY (`s_stID`) REFERENCES `Station` (`st_id`) ON DELETE SET NULL,
  ADD CONSTRAINT `Routes_ibfk_2` FOREIGN KEY (`e_stID`) REFERENCES `Station` (`st_id`) ON DELETE SET NULL,
  ADD CONSTRAINT `Routes_ibfk_3` FOREIGN KEY (`tr_id`) REFERENCES `Train` (`Tr_id`) ON DELETE SET NULL;

--
-- Constraints for table `Tickets`
--
ALTER TABLE `Tickets`
  ADD CONSTRAINT `Tickets_ibfk_1` FOREIGN KEY (`rt_id`) REFERENCES `Routes` (`rt_id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
