/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50620
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2019-05-24 19:18:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_tb
-- ----------------------------
DROP TABLE IF EXISTS `user_tb`;
CREATE TABLE `user_tb` (
  `id` int(11) NOT NULL,
  `user_name` varchar(30) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `age` int(2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
