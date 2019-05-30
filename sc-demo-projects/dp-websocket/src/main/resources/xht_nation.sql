/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.111
Source Server Version : 50725
Source Host           : 192.168.1.111:3306
Source Database       : xht_ywp

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-05-29 18:01:44
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xht_nation
-- ----------------------------
DROP TABLE IF EXISTS `xht_nation`;
CREATE TABLE `xht_nation` (
  `C_CODE` varchar(255) NOT NULL,
  `C_DESCRIBE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`C_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xht_nation
-- ----------------------------
INSERT INTO `xht_nation` VALUES ('1', '汉族');
INSERT INTO `xht_nation` VALUES ('2', '壮族');
INSERT INTO `xht_nation` VALUES ('3', '满族');
INSERT INTO `xht_nation` VALUES ('4', '回族');
INSERT INTO `xht_nation` VALUES ('5', '苗族');
INSERT INTO `xht_nation` VALUES ('6', '维吾尔族');
INSERT INTO `xht_nation` VALUES ('7', '土家族');
INSERT INTO `xht_nation` VALUES ('8', '彝族');
INSERT INTO `xht_nation` VALUES ('9', '蒙古族');
INSERT INTO `xht_nation` VALUES ('10', '藏族');
INSERT INTO `xht_nation` VALUES ('11', '布依族');
INSERT INTO `xht_nation` VALUES ('12', '侗族');
INSERT INTO `xht_nation` VALUES ('13', '瑶族');
INSERT INTO `xht_nation` VALUES ('14', '朝鲜族');
INSERT INTO `xht_nation` VALUES ('15', '白族');
INSERT INTO `xht_nation` VALUES ('16', '哈尼族');
INSERT INTO `xht_nation` VALUES ('17', '哈萨克族');
INSERT INTO `xht_nation` VALUES ('18', '黎族');
INSERT INTO `xht_nation` VALUES ('19', '傣族');
INSERT INTO `xht_nation` VALUES ('20', '畲族');
INSERT INTO `xht_nation` VALUES ('21', '傈僳族');
INSERT INTO `xht_nation` VALUES ('22', '仡佬族');
INSERT INTO `xht_nation` VALUES ('23', '东乡族');
INSERT INTO `xht_nation` VALUES ('24', '拉祜族');
INSERT INTO `xht_nation` VALUES ('25', '水族');
INSERT INTO `xht_nation` VALUES ('26', '佤族');
INSERT INTO `xht_nation` VALUES ('27', '纳西族');
INSERT INTO `xht_nation` VALUES ('28', '羌族');
INSERT INTO `xht_nation` VALUES ('29', '土族');
INSERT INTO `xht_nation` VALUES ('30', '仫佬族');
INSERT INTO `xht_nation` VALUES ('31', '锡伯族');
INSERT INTO `xht_nation` VALUES ('32', '柯尔克孜族');
INSERT INTO `xht_nation` VALUES ('33', '达斡尔族');
INSERT INTO `xht_nation` VALUES ('34', '景颇族');
INSERT INTO `xht_nation` VALUES ('35', '毛南族');
INSERT INTO `xht_nation` VALUES ('36', '撒拉族');
INSERT INTO `xht_nation` VALUES ('37', '布朗族');
INSERT INTO `xht_nation` VALUES ('38', '塔吉克族');
INSERT INTO `xht_nation` VALUES ('39', '阿昌族');
INSERT INTO `xht_nation` VALUES ('40', '普米族');
INSERT INTO `xht_nation` VALUES ('41', '鄂温克族');
INSERT INTO `xht_nation` VALUES ('42', '怒族');
INSERT INTO `xht_nation` VALUES ('43', '京族');
INSERT INTO `xht_nation` VALUES ('44', '基诺族');
INSERT INTO `xht_nation` VALUES ('45', '德昂族');
INSERT INTO `xht_nation` VALUES ('46', '保安族');
INSERT INTO `xht_nation` VALUES ('47', '俄罗斯族');
INSERT INTO `xht_nation` VALUES ('48', '裕固族');
INSERT INTO `xht_nation` VALUES ('49', '乌孜别克族');
INSERT INTO `xht_nation` VALUES ('50', '门巴族');
INSERT INTO `xht_nation` VALUES ('51', '鄂伦春族');
INSERT INTO `xht_nation` VALUES ('52', '独龙族');
INSERT INTO `xht_nation` VALUES ('53', '塔塔尔族');
INSERT INTO `xht_nation` VALUES ('54', '赫哲族');
INSERT INTO `xht_nation` VALUES ('55', '高山族');
INSERT INTO `xht_nation` VALUES ('56', '珞巴族');
