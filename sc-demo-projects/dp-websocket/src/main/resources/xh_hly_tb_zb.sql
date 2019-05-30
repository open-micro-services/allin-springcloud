/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.111
Source Server Version : 50725
Source Host           : 192.168.1.111:3306
Source Database       : xht_ywp

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-05-29 18:06:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xh_hly_tb_zb
-- ----------------------------
DROP TABLE IF EXISTS `xh_hly_tb_zb`;
CREATE TABLE `xh_hly_tb_zb` (
  `OBJECTID` int(20) NOT NULL AUTO_INCREMENT,
  `HLY_ID` bigint(20) DEFAULT NULL,
  `USER_XM` varchar(50) DEFAULT NULL,
  `USER_SFZH` varchar(50) DEFAULT NULL,
  `USER_XB` int(11) DEFAULT NULL,
  `USER_TEL` varchar(20) DEFAULT NULL,
  `USER_PYQK` int(11) DEFAULT NULL,
  `USER_SFHT` int(11) DEFAULT NULL,
  `USER_XL` int(11) DEFAULT NULL,
  `USER_MZ` int(11) DEFAULT NULL,
  `USER_HYZK` int(11) DEFAULT NULL,
  `USER_ZW` int(11) DEFAULT NULL,
  `USER_ZB` int(11) DEFAULT NULL,
  `USER_MM` varchar(50) DEFAULT 'e10adc3949ba59abbe56e057f20f883e',
  `USER_ZT` int(11) DEFAULT NULL COMMENT '0',
  `USER_GHFW` text,
  `USER_JJLXR_TEL` varchar(20) DEFAULT NULL,
  `USER_DXQF` varchar(200) DEFAULT NULL,
  `DW_CODE` varchar(200) DEFAULT NULL,
  `NSJGID` varchar(200) DEFAULT NULL COMMENT '组织机构ID',
  PRIMARY KEY (`OBJECTID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
