/*
SQLyog Community v12.12 (64 bit)
MySQL - 5.6.25-log : Database - bss_pda
*********************************************************************

Openshift MySQL access
MySQL 5.5 database added.  Please make note of these credentials:

       Root User: adminZXXiSuP
   Root Password: UcdrrvRfC_u7
   Database Name: e

Connection URL: mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/

You can manage your new MySQL database by also embedding phpmyadmin.
The phpmyadmin username and password will be the same as the MySQL credentials above.

*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bss_pda` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bss_pda`;

/*Table structure for table `expense_type` */

DROP TABLE IF EXISTS `expense_type`;

CREATE TABLE `expense_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` varchar(255) NOT NULL,
  `expense_type_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `expenses` */

DROP TABLE IF EXISTS `expenses`;

CREATE TABLE `expenses` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `amount` double NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `remarks` varchar(255) NOT NULL,
  `txn_type` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `user_account_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a40ypqkuegvyjf68r0sthyqr6` (`user_id`),
  KEY `FK_bjhgddnlv185q94ef5lr2y6eh` (`user_account_id`),
  CONSTRAINT `FK_a40ypqkuegvyjf68r0sthyqr6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FK_bjhgddnlv185q94ef5lr2y6eh` FOREIGN KEY (`user_account_id`) REFERENCES `user_accounts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `family` */

DROP TABLE IF EXISTS `family`;

CREATE TABLE `family` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `family_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `groups` */

DROP TABLE IF EXISTS `groups`;

CREATE TABLE `groups` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `group_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `loan_and_liabilities` */

DROP TABLE IF EXISTS `loan_and_liabilities`;

CREATE TABLE `loan_and_liabilities` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `actual_return_date` datetime DEFAULT NULL,
  `amount` double NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(255) NOT NULL,
  `from_to_person` varchar(255) NOT NULL,
  `planned_return_date` datetime NOT NULL,
  `type` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rb0qq1dtr98pwumc6ea8arnja` (`user_id`),
  CONSTRAINT `FK_rb0qq1dtr98pwumc6ea8arnja` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `reports` */

DROP TABLE IF EXISTS `reports`;

CREATE TABLE `reports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `dob` datetime NOT NULL,
  `family_id` bigint(20) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `is_admin` varchar(255) NOT NULL,
  `login_id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3tvt7u8scmbafolrs3hsdf68m` (`family_id`),
  CONSTRAINT `FK_3tvt7u8scmbafolrs3hsdf68m` FOREIGN KEY (`family_id`) REFERENCES `family` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `user_accounts` */

DROP TABLE IF EXISTS `user_accounts`;

CREATE TABLE `user_accounts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `account_name` varchar(255) NOT NULL,
  `account_type` varchar(255) NOT NULL,
  `current_balance` double NOT NULL,
  `effective_from_date` datetime NOT NULL,
  `effective_to_date` datetime DEFAULT NULL,
  `remarks` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_8o642jss0gbxconmpyu9hke6m` (`user_id`),
  CONSTRAINT `FK_8o642jss0gbxconmpyu9hke6m` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `user_group` */

DROP TABLE IF EXISTS `user_group`;

CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `created_date` datetime NOT NULL,
  `is_admin` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `engine_type` varchar(255) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `vehicle_number` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bofub6iyf6p4rqmrha8nti904` (`user_id`),
  CONSTRAINT `FK_bofub6iyf6p4rqmrha8nti904` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `vehicle_mileage` */

DROP TABLE IF EXISTS `vehicle_mileage`;

CREATE TABLE `vehicle_mileage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) NOT NULL,
  `cost` double NOT NULL,
  `infliated_date` datetime NOT NULL,
  `km_after_inflation` double NOT NULL,
  `km_before_inflation` double NOT NULL,
  `km_run` double NOT NULL,
  `litres_purchased` double NOT NULL,
  `millage_achieved` double NOT NULL,
  `price_per_litre` double NOT NULL,
  `vehicle_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ebnex2wck0y1y3accya9l5ul1` (`vehicle_id`),
  CONSTRAINT `FK_ebnex2wck0y1y3accya9l5ul1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

DROP TABLE IF EXISTS `currency_units`;

create table `currency_units` (
	`id` bigint (20),
	`version` bigint (20),
	`currency_desc` varchar (765),
	`currency_unit` varchar (765)
); 

DROP TABLE IF EXISTS `currency_denominations`;

create table `currency_denominations` (
	`id` bigint (20),
	`version` bigint (20),
	`comments` varchar (765),
	`currency_unit` varchar (765),
	`denomination_unit` int (11)
); 

DROP TABLE IF EXISTS `forex_exchange_rates`;

create table `forex_exchange_rates` (
	`id` bigint (20),
	`version` bigint (20),
	`date` datetime ,
	`from_currency` varchar (765),
	`rate_per_unit` double ,
	`to_currency` varchar (765)
); 

DROP TABLE IF EXISTS `properties`;

create table `properties` (
	`id` bigint (20),
	`version` bigint (20),
	`property_key` varchar (765),
	`property_value` varchar (765)
); 

