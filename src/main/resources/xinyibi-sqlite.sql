/*
Navicat SQLite Data Transfer

Source Server         : itcast
Source Server Version : 30808
Source Host           : :0

Target Server Type    : SQLite
Target Server Version : 30808
File Encoding         : 65001

Date: 2019-05-04 10:56:39
*/

PRAGMA foreign_keys = OFF;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS "main"."account";
CREATE TABLE `account` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_pwd` varchar(32) NOT NULL,
  `salt` varchar(6) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `register_time` datetime NOT NULL,
  `rid` int(11) DEFAULT NULL,
  
  
  
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `person` (`id`),
  CONSTRAINT `account_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
);

-- ----------------------------
-- Table structure for account_power
-- ----------------------------
DROP TABLE IF EXISTS "main"."account_power";
CREATE TABLE `account_power` (
  `uid` INTEGER ,
  `pid` INTEGER ,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`,`pid`),
  
  CONSTRAINT `account_id` FOREIGN KEY (`uid`) REFERENCES `account` (`id`),
  CONSTRAINT `power_id` FOREIGN KEY (`pid`) REFERENCES `power` (`id`)
);

-- ----------------------------
-- Table structure for database_info
-- ----------------------------
DROP TABLE IF EXISTS "main"."database_info";
CREATE TABLE `database_info` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `uname` varchar(255) DEFAULT NULL,
  `upwd` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `driver_class_name` varchar(255) NOT NULL,
  `driver_file_id` bigint(20) DEFAULT NULL ,
  `database_type` varchar(255) NOT NULL,
  `database_file_id` bigint(20) DEFAULT NULL ,
  `add_time` datetime,
  `comment` varchar(255) DEFAULT NULL,
  
  
  
  CONSTRAINT `database_info_ibfk_1` FOREIGN KEY (`driver_file_id`) REFERENCES `file_info` (`id`),
  CONSTRAINT `database_info_ibfk_2` FOREIGN KEY (`database_file_id`) REFERENCES `file_info` (`id`)
);

-- ----------------------------
-- Table structure for data_set
-- ----------------------------
DROP TABLE IF EXISTS "main"."data_set";
CREATE TABLE `data_set` (
  `id` varchar(255) NOT NULL,
  `data_set_name` varchar(255) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ,
  `account_id` int(11) DEFAULT NULL ,
  
  
  CONSTRAINT `data_set_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
);

-- ----------------------------
-- Table structure for data_table_info
-- ----------------------------
DROP TABLE IF EXISTS "main"."data_table_info";
CREATE TABLE `data_table_info` (
  `id` varchar(255) NOT NULL,
  `table_name` varchar(255) NOT NULL,
  `table_zh_ch_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `db_id` varchar(255) NOT NULL,
  
  
  CONSTRAINT `data_table_info_ibfk_1` FOREIGN KEY (`db_id`) REFERENCES `database_info` (`id`)
);

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS "main"."file_info";
CREATE TABLE `file_info` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `file_name` varchar(255) DEFAULT NULL ,
  `file_size` double DEFAULT NULL ,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP ,
  `mime` varchar(255) DEFAULT NULL ,
  `status` int(11) DEFAULT '0' ,
  `body` blob ,
  `path` varchar(255) DEFAULT NULL ,
  `account_id` int(11) DEFAULT NULL,
  
  
  CONSTRAINT `file_info_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
);

-- ----------------------------
-- Table structure for foreign_key_info
-- ----------------------------
DROP TABLE IF EXISTS "main"."foreign_key_info";
CREATE TABLE `foreign_key_info` (
  `id` varchar(255) NOT NULL,
  `foreign_key_name` varchar(255) DEFAULT NULL,
  `field_id` varchar(255) NOT NULL,
  `tb_id` varchar(255) NOT NULL,
  `ref_tb_id` varchar(255) NOT NULL,
  `ref_fd_id` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP);

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS "main"."person";
CREATE TABLE `person` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `cellphone` varchar(13) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `photo` blob,
  `gender` varchar(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL);

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS "main"."power";
CREATE TABLE `power` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `name` varchar(255) NOT NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS "main"."role";
CREATE TABLE `role` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `add_time` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL);


-- ----------------------------
-- Table structure for table_field_info
-- ----------------------------
DROP TABLE IF EXISTS "main"."table_field_info";
CREATE TABLE `table_field_info` (
  `id` varchar(255) NOT NULL,
  `field_name` varchar(255) NOT NULL,
  `field_zh_ch_name` varchar(255) DEFAULT NULL,
  `jdbc_type` varchar(255) NOT NULL,
  `java_type` varchar(255) NOT NULL,
  `length` int(11) DEFAULT NULL,
  `primary_key` tinyint(1) NOT NULL DEFAULT '0',
  `foreign_key` tinyint(1) NOT NULL DEFAULT '0',
  `comment` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `tb_id` varchar(255) NOT NULL,
  
  
  CONSTRAINT `table_field_info_ibfk_1` FOREIGN KEY (`tb_id`) REFERENCES `data_table_info` (`id`)
);

-- ----------------------------
-- Table structure for table_view
-- ----------------------------
DROP TABLE IF EXISTS "main"."table_view";
CREATE TABLE `table_view` (
  `id` varchar(255) NOT NULL,
  `view_name` varchar(255) NOT NULL,
  `view_zh_ch_name` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `data_set_id` varchar(255) NOT NULL,
  
  
  CONSTRAINT `table_view_ibfk_1` FOREIGN KEY (`data_set_id`) REFERENCES `data_set` (`id`)
);

-- ----------------------------
-- Table structure for view_field
-- ----------------------------
DROP TABLE IF EXISTS "main"."view_field";
CREATE TABLE `view_field` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `field_name` varchar(255) NOT NULL,
  `field_zh_ch_name` varchar(255) DEFAULT NULL,
  `data_type` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `field_type` varchar(255) DEFAULT NULL,
  `view_field_id` varchar(255) NOT NULL,
  
  
  CONSTRAINT `view_field_ibfk_1` FOREIGN KEY (`view_field_id`) REFERENCES `table_view` (`id`)
);

-- ----------------------------
-- Table structure for view_field_item
-- ----------------------------
DROP TABLE IF EXISTS "main"."view_field_item";
CREATE TABLE `view_field_item` (
  `id` INTEGER PRIMARY KEY AUTOINCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL ,
  `order` INTEGER ,
  `table_field_id` varchar(255) DEFAULT NULL,
  `view_field_id` varchar(255) NOT NULL);

-- ----------------------------
-- Table structure for view_path_header
-- ----------------------------
DROP TABLE IF EXISTS "main"."view_path_header";
CREATE TABLE `view_path_header` (
  `id` INTEGER primary key AUTOINCREMENT,
  `table_id` varchar(255) DEFAULT NULL,
  `view_id` varchar(255) DEFAULT NULL, 
  CONSTRAINT `view_path_header_ibfk_1` FOREIGN KEY (`table_id`) REFERENCES `data_table_info` (`id`),
  CONSTRAINT `view_path_header_ibfk_2` FOREIGN KEY (`view_id`) REFERENCES `table_view` (`id`)
);

-- ----------------------------
-- Table structure for view_path_vertex
-- ----------------------------
DROP TABLE IF EXISTS "main"."view_path_vertex";
CREATE TABLE `view_path_vertex` (
  `id` INTEGER primary key AUTOINCREMENT,
  `table_id` varchar(255) DEFAULT NULL,
  `left_field_id` varchar(255) DEFAULT NULL,
  `right_field_id` varchar(255) DEFAULT NULL,
  `header_id` bigint(20) DEFAULT NULL 
);
