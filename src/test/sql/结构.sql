/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : sg

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-06-25 15:14:41
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
