/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 23/04/2021 16:40:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_office
-- ----------------------------
DROP TABLE IF EXISTS `sys_office`;
CREATE TABLE `sys_office`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sort` decimal(10, 0) NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '机构表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_office
-- ----------------------------
INSERT INTO `sys_office` VALUES ('03a0166623424389b45a26a168798aa0', '测试新增机构', 2);
INSERT INTO `sys_office` VALUES ('1', '管理平台', 10);
INSERT INTO `sys_office` VALUES ('f92c51c980224da495febdd21ec2f76d', '测试机构', 1);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '编号',
  `login_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `sex` smallint(6) NULL DEFAULT NULL COMMENT '性别',
  `login_pwd` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `sys_user_login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '44', NULL, '123456');
INSERT INTO `sys_user` VALUES ('46cdb4e395ec49beaecd93fa61a05419', 'user01', '1', NULL, '123456');
INSERT INTO `sys_user` VALUES ('bf1a1b996eeb4f2a83b7c7c12810b5eb', 'user02', '6', NULL, '123456');

SET FOREIGN_KEY_CHECKS = 1;
