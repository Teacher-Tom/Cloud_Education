/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.7.37-log : Database - cloud_manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cloud_manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `cloud_manager`;

/*Table structure for table `manager_class` */

DROP TABLE IF EXISTS `manager_class`;

CREATE TABLE `manager_class` (
  `name` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `major` varchar(20) NOT NULL,
  `id` char(10) NOT NULL,
  `gmt_create` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `manager_class` */

insert  into `manager_class`(`name`,`department`,`major`,`id`,`gmt_create`,`gmt_modified`) values 
('1','1','1','1','2023-03-10 13:01:00','2023-03-10 13:01:00'),
('2','2','2','2','2023-03-10 14:35:59','2023-03-10 14:36:03');

/*Table structure for table `manager_class_course` */

DROP TABLE IF EXISTS `manager_class_course`;

CREATE TABLE `manager_class_course` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `class_id` char(10) NOT NULL,
  `course_id` char(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `manager_class_course` */

/*Table structure for table `manager_course` */

DROP TABLE IF EXISTS `manager_course`;

CREATE TABLE `manager_course` (
  `name` varchar(20) NOT NULL,
  `credit_hour` int(11) NOT NULL,
  `credit_num` int(11) NOT NULL,
  `type` bit(1) NOT NULL,
  `year` int(4) NOT NULL,
  `department` varchar(20) NOT NULL,
  `term` int(11) NOT NULL,
  `begin_week` int(11) NOT NULL,
  `end_week` int(11) NOT NULL,
  `id` char(10) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `manager_course` */

/*Table structure for table `manager_student` */

DROP TABLE IF EXISTS `manager_student`;

CREATE TABLE `manager_student` (
  `name` varchar(20) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `major` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `id` varchar(10) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `class_id` char(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `manager_student` */

insert  into `manager_student`(`name`,`gender`,`major`,`department`,`id`,`gmt_create`,`gmt_modified`,`class_id`) values 
('01',1,'1','1','1','2023-03-10 13:03:20','2023-03-10 14:30:25','1'),
('02',0,'1','1','2','2023-03-10 13:03:18','2023-03-10 14:36:14','2');

/*Table structure for table `manager_teacher` */

DROP TABLE IF EXISTS `manager_teacher`;

CREATE TABLE `manager_teacher` (
  `id` char(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `department` varchar(20) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `manager_teacher` */

/*Table structure for table `manager_teacher_course` */

DROP TABLE IF EXISTS `manager_teacher_course`;

CREATE TABLE `manager_teacher_course` (
  `id` int(10) NOT NULL,
  `teacher_id` char(10) NOT NULL,
  `course_id` char(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `manager_teacher_course` */

/*Table structure for table `manager_timetable` */

DROP TABLE IF EXISTS `manager_timetable`;

CREATE TABLE `manager_timetable` (
  `id` char(10) NOT NULL,
  `course_id` char(10) NOT NULL,
  `date` date NOT NULL,
  `week` int(10) unsigned NOT NULL,
  `day_of_week` int(10) unsigned NOT NULL,
  `begin_index` int(10) unsigned NOT NULL,
  `end_index` int(10) unsigned NOT NULL,
  `location` varchar(20) NOT NULL,
  `teacher_id` char(10) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `manager_timetable` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
