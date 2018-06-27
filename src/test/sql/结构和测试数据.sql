/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : sg

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-25 15:14:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for game
-- ----------------------------
DROP TABLE IF EXISTS `game`;
CREATE TABLE `game` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(255) DEFAULT NULL COMMENT '游戏ip地址',
  `game_name` varchar(255) DEFAULT NULL COMMENT '游戏名称',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='游戏表';

-- ----------------------------
-- Records of game
-- ----------------------------
INSERT INTO `game` VALUES ('5', '192.168.1.1', 'A游戏', '0', 'A游戏', '2018-06-22 16:33:28');
INSERT INTO `game` VALUES ('6', '192.168.1.2', 'B游戏', '0', 'B游戏', '2018-06-22 16:33:44');
INSERT INTO `game` VALUES ('7', '192.168.1.3', 'C游戏', '0', 'C游戏', '2018-06-22 16:34:15');
INSERT INTO `game` VALUES ('8', '192.168.1.4', 'D游戏', '0', 'D游戏', '2018-06-22 16:34:30');
INSERT INTO `game` VALUES ('9', '192.168.1.5', 'E游戏', '0', 'E游戏', '2018-06-22 16:34:49');

-- ----------------------------
-- Table structure for gateway_address
-- ----------------------------
DROP TABLE IF EXISTS `gateway_address`;
CREATE TABLE `gateway_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ip` varchar(255) NOT NULL COMMENT '网关ip地址',
  `gateway_address_name` varchar(255) DEFAULT NULL COMMENT '网关服务器名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='网关服务器地址';

-- ----------------------------
-- Records of gateway_address
-- ----------------------------
INSERT INTO `gateway_address` VALUES ('1', '192.168.0.1', '1号网关服务器', '1号网关服务器', '0', '2018-06-19 16:34:48');
INSERT INTO `gateway_address` VALUES ('2', '192.168.0.2', '2号网关服务器', '2号网关服务器', '-1', '2018-06-19 17:29:44');
INSERT INTO `gateway_address` VALUES ('3', '192.168.0.3', '3号网关服务器', '3号网关服务器', '0', '2018-06-19 17:29:49');
INSERT INTO `gateway_address` VALUES ('4', '192.168.0.4', '4号网关服务器', '4号网关服务器', '0', '2018-06-19 17:29:55');
INSERT INTO `gateway_address` VALUES ('5', '192.168.0.5', '5号网关服务器', '5号网关服务器', '0', '2018-06-19 17:30:01');
INSERT INTO `gateway_address` VALUES ('6', '192.168.0.6', '6号网关服务器', '6号为中间节点', '0', '2018-06-19 17:30:23');
INSERT INTO `gateway_address` VALUES ('7', '192.168.0.7', '7号网关服务器', '7号为被备用服务器', '0', '2018-06-19 17:31:43');
INSERT INTO `gateway_address` VALUES ('8', '192.168.0.7', '8号服务器', '8号服务器', '-1', '2018-06-19 18:15:15');
INSERT INTO `gateway_address` VALUES ('9', '192.168.0.8', '8号网关服务器', '8号网关服务器！！！', '0', '2018-06-22 17:14:56');

-- ----------------------------
-- Table structure for rule_group
-- ----------------------------
DROP TABLE IF EXISTS `rule_group`;
CREATE TABLE `rule_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `rule_group_name` varchar(255) DEFAULT NULL COMMENT '规则组名',
  `gateway_address_ids` varchar(255) DEFAULT NULL COMMENT '所属该组的网关id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='规则组';

-- ----------------------------
-- Records of rule_group
-- ----------------------------
INSERT INTO `rule_group` VALUES ('5', '红中规则组', '[\"1\",\"3\"]', '用于红中游戏', '0', '2018-06-22 16:32:13');
INSERT INTO `rule_group` VALUES ('6', '斗地主规则组', '[\"4\",\"6\",\"7\"]', '用于斗地主', '0', '2018-06-22 16:32:46');
INSERT INTO `rule_group` VALUES ('7', '高级斗地主规则组', '[\"5\"]', '高级斗地主规则组使用', '0', '2018-06-25 14:25:32');

-- ----------------------------
-- Table structure for rules
-- ----------------------------
DROP TABLE IF EXISTS `rules`;
CREATE TABLE `rules` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `game_id` bigint(20) DEFAULT NULL COMMENT '游戏表ID',
  `rule_group_id` bigint(20) NOT NULL COMMENT '规则组ID',
  `rule_name` varchar(255) DEFAULT NULL COMMENT '规则名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='规则表';

-- ----------------------------
-- Records of rules
-- ----------------------------
INSERT INTO `rules` VALUES ('5', '5', '5', '红中规则', '红中规则！！！', '0', '2018-06-22 16:43:53');
INSERT INTO `rules` VALUES ('6', '8', '5', '红中规则组 D游戏', '红中规则组 D游戏！！！', '0', '2018-06-25 10:33:28');

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_group_name` varchar(255) DEFAULT NULL COMMENT '用户组名',
  `rule_group_ids` varchar(255) DEFAULT NULL COMMENT '所属规则组的id',
  `game_id` bigint(20) DEFAULT NULL COMMENT '游戏id',
  `gateway_address_ids` varchar(255) DEFAULT NULL COMMENT '所属该组的网关id',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0：正常使用、-1：已被删除',
  `crtTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户组';

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('5', '红中初级组', '5', '5', '[\"1\"]', '红中初级组用户使用', '0', '2018-06-22 16:39:14');
INSERT INTO `user_group` VALUES ('6', '斗地主初级组', '6', '6', '[\"4\"]', '斗地主初级组用户', '0', '2018-06-22 16:40:16');
INSERT INTO `user_group` VALUES ('7', '斗地主中级组用户', '6', '6', '[\"6\",\"7\"]', '斗地主中级组用户', '0', '2018-06-22 16:40:48');
INSERT INTO `user_group` VALUES ('8', '斗地主高级组', '6', '6', '[]', '斗地主高级组！！！', '-1', '2018-06-25 12:09:24');
INSERT INTO `user_group` VALUES ('9', '斗地主高级用户', '7', '6', '[\"5\"]', '斗地主高级用户！！！', '0', '2018-06-25 14:26:08');
