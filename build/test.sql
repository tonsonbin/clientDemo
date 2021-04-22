/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2019-06-27 16:59:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` decimal(10,0) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('03a0166623424389b45a26a168798aa0', '测试新增机构', '2');
INSERT INTO `sys_office` VALUES ('1', '管理平台', '10');
INSERT INTO `sys_office` VALUES ('f92c51c980224da495febdd21ec2f76d', '测试机构', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(64) NOT NULL COMMENT '编号',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `name` varchar(100) NOT NULL COMMENT '姓名',
  `sex` smallint(6) DEFAULT NULL COMMENT '性别',
  PRIMARY KEY (`id`),
  KEY `sys_user_login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('21c180157a844a6aac5e3691c7c1c679', '33', '3345', null);
INSERT INTO `sys_user` VALUES ('415e85296f5f4e6e8ea814efa446bf88', '4', '44', null);
INSERT INTO `sys_user` VALUES ('7bdfb993e7da4c0c86ae066b523aa1d3', '33', '33', null);
INSERT INTO `sys_user` VALUES ('7da94fdccf7240d5aa494d1fa168dea8', '1', '1', null);
INSERT INTO `sys_user` VALUES ('b626fa0670bd449598f83569a31e7dc5', '2', '2', null);
INSERT INTO `sys_user` VALUES ('b6570ffeae8f4613afc7337d41b48e9f', '5', '5', null);
INSERT INTO `sys_user` VALUES ('bf1a1b996eeb4f2a83b7c7c12810b5eb', '6', '6', null);
