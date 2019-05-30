/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.111
Source Server Version : 50725
Source Host           : 192.168.1.111:3306
Source Database       : xht_ywp

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-05-29 18:05:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xh_zc_tb
-- ----------------------------
DROP TABLE IF EXISTS `xh_zc_tb`;
CREATE TABLE `xh_zc_tb` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ZC_TIME` datetime DEFAULT CURRENT_TIMESTAMP,
  `DW_CODE` varchar(20) DEFAULT NULL COMMENT '按照一定规则自动生成，作为入驻结构的唯一编码',
  `ZC_NAME` varchar(50) DEFAULT NULL,
  `ZC_LXR` varchar(50) DEFAULT NULL COMMENT '入驻机构初始化管理员',
  `ZC_TEL` varchar(50) DEFAULT NULL COMMENT '注册成功后初始管理员账号，一点注册成功不可修改',
  `ACTIVE` int(11) DEFAULT '0' COMMENT '是否激活（是否审核）',
  `LIC_END_DATE` date DEFAULT NULL COMMENT '许可到期时间',
  `ADMIN_COUNT` int(4) DEFAULT '1' COMMENT '登录账号分配数量',
  `USER_COUNT` int(11) DEFAULT '1' COMMENT '护林员数量限制',
  `DW_ROLE` varchar(1000) DEFAULT NULL COMMENT 'BS单位权限控制',
  `SALES` varchar(50) DEFAULT NULL COMMENT '销售人员',
  `TYPE` int(1) DEFAULT '0' COMMENT '账户类型0：试用，1：正式',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
