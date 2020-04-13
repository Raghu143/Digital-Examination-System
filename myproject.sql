-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 02, 2018 at 08:28 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `myproject`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `name` varchar(30) NOT NULL,
  `contact` text NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`name`, `contact`, `username`, `password`) VALUES
('Vedprakash', '7870090184', 'ved123', '12345'),
('Raghuvansh', '9570017896', 'raghu123', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `c`
--

CREATE TABLE IF NOT EXISTS `c` (
  `qid` varchar(10) NOT NULL DEFAULT '',
  `qdesc` varchar(3500) DEFAULT NULL,
  `opt1` varchar(50) DEFAULT NULL,
  `opt2` varchar(50) DEFAULT NULL,
  `opt3` varchar(50) DEFAULT NULL,
  `opt4` varchar(50) DEFAULT NULL,
  `correctans` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `c`
--

INSERT INTO `c` (`qid`, `qdesc`, `opt1`, `opt2`, `opt3`, `opt4`, `correctans`) VALUES
('C101', 'Who is father of C Language?', 'Bjarne Stroustrup', 'James A. Gosling', 'Dennis Ritchie', 'Dr. E.F. Codd', 'Dennis Ritchie'),
('C102', 'C programs are converted into machine language with the help of', ' An Editor', 'A Compiler', 'An operating system', 'None of these', 'A Compiler'),
('C103', 'For 16-bit compiler allowable range for integer constants is ________?', '-3.4e38 to 3.4e38', '-32767 to 32768', '-32668 to 32667', '-32768 to 32767', '-32768 to 32767');

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `coursename` varchar(30) NOT NULL,
  `coursetime` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`coursename`, `coursetime`) VALUES
('Java', '30'),
('C', '15');

-- --------------------------------------------------------

--
-- Table structure for table `java`
--

CREATE TABLE IF NOT EXISTS `java` (
  `qid` varchar(10) DEFAULT NULL,
  `qdesc` varchar(3500) NOT NULL,
  `opt1` varchar(50) NOT NULL,
  `opt2` varchar(50) NOT NULL,
  `opt3` varchar(50) NOT NULL,
  `opt4` varchar(50) NOT NULL,
  `correctans` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `java`
--

INSERT INTO `java` (`qid`, `qdesc`, `opt1`, `opt2`, `opt3`, `opt4`, `correctans`) VALUES
('Java101', 'In System.out.println, System is a .............', 'Class', 'Interface', 'Method', 'Object', 'Class'),
('Java102', 'Java supports multiple inheritance ..............', 'Yes', 'No', 'through inteface', 'through class', 'through inteface'),
('Java103', 'Java is a .......', 'OOP language', 'POP language', 'Programming Language', 'None of these', 'OOP language'),
('Java104', 'Method Signature means', 'Combination of method name and return type', 'Combination of method name and parameter list', 'Combination of return type and parameter list', 'All of the above', 'Combination of method name and parameter list'),
('Java105', 'java.util.Scanner is a .........', 'Class', 'Interface', 'Package', 'None of these', 'Class'),
('Java106', 'Java offers database programming through ........ package', 'java.util', 'java.lang', 'java.sql', 'java.mysql', 'java.sql'),
('Java107', 'String is a .........', 'Data type', 'Interface', 'Both Option 1 and Option 4', 'Class', 'Both Option 1 and Option 4'),
('Java108', 'Java developed by', 'Microsoft', 'Oracle', 'Sun Micro System', 'Google', 'Sun Micro System'),
('Java109', 'We can create .... instance of abstract class in Java.', '1', 'Any number', '0', 'All of the above', '0'),
('Java110', 'In java, package is a .....', 'Collection of classes', 'Collection of interfaces', 'Collection of Methods', 'Collection of classes and interfaces', 'Collection of classes and interfaces'),
('Java111', 'JVM stands for', 'Java Virtual Machine', 'JDK Virtual Machine', 'JRE Virtual Machine', 'All of the above', 'Java Virtual Machine');

-- --------------------------------------------------------

--
-- Table structure for table `userinfo`
--

CREATE TABLE IF NOT EXISTS `userinfo` (
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `mobile` text NOT NULL,
  `dob` varchar(20) NOT NULL,
  `course` varchar(20) NOT NULL,
  `semester` varchar(15) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `college` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `verifieduser`
--

CREATE TABLE IF NOT EXISTS `verifieduser` (
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `fname` varchar(20) NOT NULL,
  `lname` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `mobile` text NOT NULL,
  `dob` varchar(20) NOT NULL,
  `course` varchar(20) NOT NULL,
  `semester` varchar(15) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `college` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `verifieduser`
--

INSERT INTO `verifieduser` (`username`, `password`, `fname`, `lname`, `gender`, `mobile`, `dob`, `course`, `semester`, `address`, `college`) VALUES
('Raghu143', '12345', 'Raghuvansh', 'Gupta', 'Male', '2147483647', '10/Jul/1995', 'MCA', '3', 'Daltonganj', 'Guru Ghasidas Vishwavidyalya'),
('Raghu143', '12345', 'Raghuvansh', 'Gupta', 'Male', '9570017896', '10/Jul/1995', 'MCA', 'Third', 'Daltonganj', 'Guru Ghasidas Vishwavidyalya'),
('surbhi123', '12345', 'Surbhi', 'Singh', 'Female', '7870090184', '06/May/1989', 'MCA', 'Fourth', 'Ranchi, Jharkhand', 'Xavier College Ranchi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `c`
--
ALTER TABLE `c`
 ADD PRIMARY KEY (`qid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
