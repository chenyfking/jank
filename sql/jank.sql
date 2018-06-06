/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : jank

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2018-06-06 10:48:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL COMMENT '权限编码',
  `name` varchar(255) NOT NULL COMMENT '权限名称',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('1', 'user:add', '添加用户', '2018-06-06 09:50:06', null, null);
INSERT INTO `t_permission` VALUES ('2', 'user:view', '查询用户', '2018-06-06 09:50:21', null, null);
INSERT INTO `t_permission` VALUES ('3', 'user:edit', '编辑用户', '2018-06-06 09:50:34', null, null);
INSERT INTO `t_permission` VALUES ('4', 'user:delete', '删除用户', '2018-06-06 09:50:57', null, null);
INSERT INTO `t_permission` VALUES ('5', 'role:view', '查询角色', '2018-06-06 09:53:28', null, null);
INSERT INTO `t_permission` VALUES ('6', 'role:add', '添加角色', '2018-06-06 09:53:49', null, null);
INSERT INTO `t_permission` VALUES ('7', 'role:edit', '编辑角色', '2018-06-06 09:54:07', null, null);
INSERT INTO `t_permission` VALUES ('8', 'role:delete', '删除角色', '2018-06-06 09:54:27', null, null);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL COMMENT '角色编码',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '普通用户', '2018-06-06 09:41:40', null, 'ptyh', null);
INSERT INTO `t_role` VALUES ('2', '管理员', '2018-06-06 09:41:58', null, 'gly', null);
INSERT INTO `t_role` VALUES ('3', '其他用户', '2018-06-06 09:42:12', null, 'special', null);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('1', '2', '1');
INSERT INTO `t_role_permission` VALUES ('2', '2', '2');
INSERT INTO `t_role_permission` VALUES ('3', '2', '3');
INSERT INTO `t_role_permission` VALUES ('4', '2', '4');
INSERT INTO `t_role_permission` VALUES ('5', '2', '5');
INSERT INTO `t_role_permission` VALUES ('6', '2', '6');
INSERT INTO `t_role_permission` VALUES ('7', '2', '7');
INSERT INTO `t_role_permission` VALUES ('8', '2', '8');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `is_delete` tinyint(255) DEFAULT NULL COMMENT '逻辑删除标志',
  `role` int(11) NOT NULL DEFAULT '1' COMMENT '权限 1.普通用户 2.管理员 3.特殊用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', '2018-06-05 15:53:49', null, null, '1');
