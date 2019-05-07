/*
Navicat MySQL Data Transfer

Source Server         : XAMPP
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : sqlite

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2019-04-28 23:02:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_pwd` varchar(32) NOT NULL,
  `salt` varchar(6) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `register_time` datetime NOT NULL,
  `rid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  KEY `rid` (`rid`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `person` (`id`),
  CONSTRAINT `account_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for account_power
-- ----------------------------
DROP TABLE IF EXISTS `account_power`;
CREATE TABLE `account_power` (
  `uid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`,`pid`),
  KEY `power_id` (`pid`),
  CONSTRAINT `account_id` FOREIGN KEY (`uid`) REFERENCES `account` (`id`),
  CONSTRAINT `power_id` FOREIGN KEY (`pid`) REFERENCES `power` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for database_info
-- ----------------------------
DROP TABLE IF EXISTS `database_info`;
CREATE TABLE `database_info` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `uname` varchar(255) DEFAULT NULL,
  `upwd` varchar(255) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `driver_class_name` varchar(255) NOT NULL,
  `driver_file_id` bigint(20) DEFAULT NULL COMMENT '驱动程序文件',
  `database_type` varchar(255) NOT NULL,
  `database_file_id` bigint(20) DEFAULT NULL COMMENT '文档类数据库的文件ID',
  `add_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_file_id` (`driver_file_id`),
  KEY `database_file_id` (`database_file_id`),
  CONSTRAINT `database_info_ibfk_1` FOREIGN KEY (`driver_file_id`) REFERENCES `file_info` (`id`),
  CONSTRAINT `database_info_ibfk_2` FOREIGN KEY (`database_file_id`) REFERENCES `file_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_set
-- ----------------------------
DROP TABLE IF EXISTS `data_set`;
CREATE TABLE `data_set` (
  `id` varchar(255) NOT NULL,
  `data_set_name` varchar(255) DEFAULT NULL,
  `add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '插入时间',
  `account_id` int(11) DEFAULT NULL COMMENT '创建人id',
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `data_set_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for data_table_info
-- ----------------------------
DROP TABLE IF EXISTS `data_table_info`;
CREATE TABLE `data_table_info` (
  `id` varchar(255) NOT NULL,
  `table_name` varchar(255) NOT NULL,
  `table_zh_ch_name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `db_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `db_id` (`db_id`),
  CONSTRAINT `data_table_info_ibfk_1` FOREIGN KEY (`db_id`) REFERENCES `database_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名称',
  `file_size` double DEFAULT NULL COMMENT '文件大小，单位KB',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mime` varchar(255) DEFAULT NULL COMMENT '媒体查询类型',
  `status` int(11) DEFAULT '0' COMMENT '文件状态：0表示临时文件，1表示持久文件，2表示已删除文件',
  `body` blob COMMENT '文件体，一般只有小于10MB的文件才会存到数据库中',
  `path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `account_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `account_id` (`account_id`),
  CONSTRAINT `file_info_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for foreign_key_info
-- ----------------------------
DROP TABLE IF EXISTS `foreign_key_info`;
CREATE TABLE `foreign_key_info` (
  `id` varchar(255) NOT NULL,
  `foreign_key_name` varchar(255) DEFAULT NULL,
  `field_id` varchar(255) NOT NULL,
  `tb_id` varchar(255) NOT NULL,
  `ref_tb_id` varchar(255) NOT NULL,
  `ref_fd_id` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `cellphone` varchar(13) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `photo` blob,
  `gender` varchar(2) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birthday` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for power
-- ----------------------------
DROP TABLE IF EXISTS `power`;
CREATE TABLE `power` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `add_time` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for table_field_info
-- ----------------------------
DROP TABLE IF EXISTS `table_field_info`;
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
  PRIMARY KEY (`id`),
  KEY `tb_id` (`tb_id`),
  CONSTRAINT `table_field_info_ibfk_1` FOREIGN KEY (`tb_id`) REFERENCES `data_table_info` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for table_view
-- ----------------------------
DROP TABLE IF EXISTS `table_view`;
CREATE TABLE `table_view` (
  `id` varchar(255) NOT NULL,
  `view_name` varchar(255) NOT NULL,
  `view_zh_ch_name` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `data_set_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `data_set_id` (`data_set_id`),
  CONSTRAINT `table_view_ibfk_1` FOREIGN KEY (`data_set_id`) REFERENCES `data_set` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for view_field
-- ----------------------------
DROP TABLE IF EXISTS `view_field`;
CREATE TABLE `view_field` (
  `id` int(11) NOT NULL,
  `field_name` varchar(255) NOT NULL,
  `field_zh_ch_name` varchar(255) DEFAULT NULL,
  `data_type` varchar(255) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  `field_type` varchar(255) DEFAULT NULL,
  `view_field_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `view_field_id` (`view_field_id`),
  CONSTRAINT `view_field_ibfk_1` FOREIGN KEY (`view_field_id`) REFERENCES `table_view` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for view_field_item
-- ----------------------------
DROP TABLE IF EXISTS `view_field_item`;
CREATE TABLE `view_field_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL COMMENT '数据项类型，可以是操作符，运算数，字段ID，函数名等',
  `order` int(11) NOT NULL,
  `table_field_id` varchar(255) DEFAULT NULL,
  `view_field_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
