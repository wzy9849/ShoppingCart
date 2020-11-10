/*
 Navicat Premium Data Transfer

 Source Server         : MYSQL5.6
 Source Server Type    : MySQL
 Source Server Version : 50622
 Source Host           : localhost:3306
 Source Schema         : shopping_cart

 Target Server Type    : MySQL
 Target Server Version : 50622
 File Encoding         : 65001

 Date: 01/11/2020 22:58:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` float(10, 2) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `num` int(10) NOT NULL,
  `price` float(10, 2) NOT NULL,
  `order_id` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_Goods_ID`(`goods_id`) USING BTREE,
  INDEX `FK_Order_ID`(`order_id`) USING BTREE,
  CONSTRAINT `FK_Goods_ID` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Order_ID` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userInfo_id` int(11) NOT NULL,
  `sum_price` float(10, 2) NOT NULL,
  `createTime` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `order_no`(`order_no`) USING BTREE,
  INDEX `FK_UserInfo_ID`(`userInfo_id`) USING BTREE,
  CONSTRAINT `FK_UserInfo_ID` FOREIGN KEY (`userInfo_id`) REFERENCES `userinfo` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(55) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
