/*
 Navicat Premium Data Transfer

 Source Server         : localhsot
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : train

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 28/06/2022 15:15:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT NULL COMMENT '管理员状态：0-禁用，1-启动',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (48, '超级管理员', '18000000000', '4a7d1ed414474e4033ac29ccb8653d9b', 1);
INSERT INTO `t_admin` VALUES (49, '张三', '13777777777', '202cb962ac59075b964b07152d234b70', 2);

-- ----------------------------
-- Table structure for t_all_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_all_ticket`;
CREATE TABLE `t_all_ticket`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `train_id` int(0) NULL DEFAULT NULL COMMENT '车次',
  `carriage_id` int(0) NULL DEFAULT NULL COMMENT '车厢编号',
  `seat_id` int(0) NULL DEFAULT NULL COMMENT '座位编号',
  `state` int(0) NULL DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 226 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_all_ticket
-- ----------------------------
INSERT INTO `t_all_ticket` VALUES (176, 11, 4, 126, 1);
INSERT INTO `t_all_ticket` VALUES (177, 11, 4, 127, 1);
INSERT INTO `t_all_ticket` VALUES (178, 11, 4, 128, 1);
INSERT INTO `t_all_ticket` VALUES (179, 11, 4, 129, 1);
INSERT INTO `t_all_ticket` VALUES (180, 11, 4, 130, 1);
INSERT INTO `t_all_ticket` VALUES (181, 11, 4, 131, 1);
INSERT INTO `t_all_ticket` VALUES (182, 11, 4, 132, 1);
INSERT INTO `t_all_ticket` VALUES (183, 11, 4, 133, 1);
INSERT INTO `t_all_ticket` VALUES (184, 11, 4, 134, 1);
INSERT INTO `t_all_ticket` VALUES (185, 11, 4, 135, 1);
INSERT INTO `t_all_ticket` VALUES (186, 11, 4, 136, 1);
INSERT INTO `t_all_ticket` VALUES (187, 11, 4, 137, 1);
INSERT INTO `t_all_ticket` VALUES (188, 11, 4, 138, 1);
INSERT INTO `t_all_ticket` VALUES (189, 11, 4, 139, 1);
INSERT INTO `t_all_ticket` VALUES (190, 11, 4, 140, 1);
INSERT INTO `t_all_ticket` VALUES (191, 11, 4, 141, 1);
INSERT INTO `t_all_ticket` VALUES (192, 11, 4, 142, 1);
INSERT INTO `t_all_ticket` VALUES (193, 11, 4, 143, 1);
INSERT INTO `t_all_ticket` VALUES (194, 11, 4, 144, 1);
INSERT INTO `t_all_ticket` VALUES (195, 11, 4, 145, 1);
INSERT INTO `t_all_ticket` VALUES (196, 11, 4, 146, 1);
INSERT INTO `t_all_ticket` VALUES (197, 11, 4, 147, 1);
INSERT INTO `t_all_ticket` VALUES (198, 11, 4, 148, 1);
INSERT INTO `t_all_ticket` VALUES (199, 11, 4, 149, 1);
INSERT INTO `t_all_ticket` VALUES (200, 11, 4, 150, 1);
INSERT INTO `t_all_ticket` VALUES (201, 11, 4, 151, 1);
INSERT INTO `t_all_ticket` VALUES (202, 11, 4, 152, 1);
INSERT INTO `t_all_ticket` VALUES (203, 11, 4, 153, 1);
INSERT INTO `t_all_ticket` VALUES (204, 11, 4, 154, 1);
INSERT INTO `t_all_ticket` VALUES (205, 11, 4, 155, 1);
INSERT INTO `t_all_ticket` VALUES (206, 11, 4, 156, 1);
INSERT INTO `t_all_ticket` VALUES (207, 11, 4, 157, 1);
INSERT INTO `t_all_ticket` VALUES (208, 11, 4, 158, 1);
INSERT INTO `t_all_ticket` VALUES (209, 11, 4, 159, 1);
INSERT INTO `t_all_ticket` VALUES (210, 11, 4, 160, 1);
INSERT INTO `t_all_ticket` VALUES (211, 11, 4, 161, 1);
INSERT INTO `t_all_ticket` VALUES (212, 11, 4, 162, 1);
INSERT INTO `t_all_ticket` VALUES (213, 11, 4, 163, 1);
INSERT INTO `t_all_ticket` VALUES (214, 11, 4, 164, 1);
INSERT INTO `t_all_ticket` VALUES (215, 11, 4, 165, 1);
INSERT INTO `t_all_ticket` VALUES (216, 11, 4, 166, 1);
INSERT INTO `t_all_ticket` VALUES (217, 11, 4, 167, 1);
INSERT INTO `t_all_ticket` VALUES (218, 11, 4, 168, 1);
INSERT INTO `t_all_ticket` VALUES (219, 11, 4, 169, 1);
INSERT INTO `t_all_ticket` VALUES (220, 11, 4, 170, 1);
INSERT INTO `t_all_ticket` VALUES (221, 11, 4, 171, 1);
INSERT INTO `t_all_ticket` VALUES (222, 11, 4, 172, 1);
INSERT INTO `t_all_ticket` VALUES (223, 11, 4, 173, 1);
INSERT INTO `t_all_ticket` VALUES (224, 11, 4, 174, 1);
INSERT INTO `t_all_ticket` VALUES (225, 11, 4, 175, 1);

-- ----------------------------
-- Table structure for t_carriage
-- ----------------------------
DROP TABLE IF EXISTS `t_carriage`;
CREATE TABLE `t_carriage`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `carriage_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车厢编号',
  `price_base` bigint(0) NULL DEFAULT NULL COMMENT '价格基数',
  `left_seat` int(0) NULL DEFAULT NULL COMMENT '左侧座位数',
  `right_seat` int(0) NULL DEFAULT NULL COMMENT '右侧座位数',
  `row_seat` int(0) NULL DEFAULT NULL COMMENT '排数',
  `seat_count` int(0) NULL DEFAULT NULL COMMENT '座位数',
  `level_car` int(0) NULL DEFAULT NULL COMMENT '级别：0-经济型，1-普通型，2-豪华型',
  `adapt_model` int(0) NULL DEFAULT NULL COMMENT '适配车型：0-动车，1-高铁，2-动车，高铁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_carriage
-- ----------------------------
INSERT INTO `t_carriage` VALUES (4, '1', 5, 3, 2, 10, 50, 3, 1);

-- ----------------------------
-- Table structure for t_line
-- ----------------------------
DROP TABLE IF EXISTS `t_line`;
CREATE TABLE `t_line`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `train_id` int(0) NULL DEFAULT NULL COMMENT '车次',
  `site_id` int(0) NULL DEFAULT NULL COMMENT '站点名称',
  `time_consume` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '耗时',
  `site_order` int(0) NULL DEFAULT NULL COMMENT '站点顺序',
  `site_value` int(0) NULL DEFAULT NULL COMMENT '站点值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 63 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_line
-- ----------------------------
INSERT INTO `t_line` VALUES (60, 11, 14, '0', 0, 1);
INSERT INTO `t_line` VALUES (61, 11, 15, '30', 1, 2);
INSERT INTO `t_line` VALUES (62, 11, 16, '80', 2, 4);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `order_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单编号',
  `order_time` bigint(0) NULL DEFAULT NULL COMMENT '下单时间',
  `train_id` int(0) NULL DEFAULT NULL COMMENT '车次',
  `start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发时间',
  `start_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '起点站',
  `end_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '终点站',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达时间',
  `level_car` int(0) NULL DEFAULT NULL COMMENT '级别：0-经济型，1-普通型，2-豪华型',
  `price` double NULL DEFAULT NULL COMMENT '价格',
  `order_status` int(0) NULL DEFAULT NULL COMMENT '订单状态：0-未支付，1-已支付，2-已取消',
  `passenger_id` int(0) NULL DEFAULT NULL COMMENT '订单所属用户',
  `seat_id` int(0) NULL DEFAULT NULL COMMENT '座位编号',
  `carriage_order` int(0) NULL DEFAULT NULL COMMENT '车厢顺序',
  `start_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `end_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (65, '220627171418204444641', 1656321258757, 11, '20:30', '14', '15', '21:00', 3, NULL, 1, 11, 126, 1, '2022-06-27', '2022-06-27', 5);
INSERT INTO `t_order` VALUES (66, '2206271717161283578250', 1656321436583, 11, '20:30', '14', '15', '21:00', 3, NULL, 2, 12, 127, 1, '2022-06-27', '2022-06-27', 6);
INSERT INTO `t_order` VALUES (67, '2206271719311765798303', 1656321571600, 11, '20:30', '14', '16', '21:50', 3, NULL, 1, 14, 127, 1, '2022-06-27', '2022-06-27', 6);

-- ----------------------------
-- Table structure for t_passenger
-- ----------------------------
DROP TABLE IF EXISTS `t_passenger`;
CREATE TABLE `t_passenger`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `user_id` int(0) NULL DEFAULT NULL COMMENT ' 乘客所属的用户',
  `passenger_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乘客姓名',
  `passenger_phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乘客手机号',
  `passenger_card` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_passenger
-- ----------------------------
INSERT INTO `t_passenger` VALUES (11, 5, '刘超', '18283989856', '510811199707163879');
INSERT INTO `t_passenger` VALUES (12, 6, '张三', '15179624890', '510811199901010000');
INSERT INTO `t_passenger` VALUES (13, 6, '张三', '15654645654', '510811199901010000');
INSERT INTO `t_passenger` VALUES (14, 6, '李四', '11111111111', '516354561111111111');

-- ----------------------------
-- Table structure for t_seat
-- ----------------------------
DROP TABLE IF EXISTS `t_seat`;
CREATE TABLE `t_seat`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `seat_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '座位编号',
  `carriage_id` int(0) NULL DEFAULT NULL COMMENT '车厢编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 176 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_seat
-- ----------------------------
INSERT INTO `t_seat` VALUES (126, '1A', 4);
INSERT INTO `t_seat` VALUES (127, '1B', 4);
INSERT INTO `t_seat` VALUES (128, '1C', 4);
INSERT INTO `t_seat` VALUES (129, '1D', 4);
INSERT INTO `t_seat` VALUES (130, '1E', 4);
INSERT INTO `t_seat` VALUES (131, '2A', 4);
INSERT INTO `t_seat` VALUES (132, '2B', 4);
INSERT INTO `t_seat` VALUES (133, '2C', 4);
INSERT INTO `t_seat` VALUES (134, '2D', 4);
INSERT INTO `t_seat` VALUES (135, '2E', 4);
INSERT INTO `t_seat` VALUES (136, '3A', 4);
INSERT INTO `t_seat` VALUES (137, '3B', 4);
INSERT INTO `t_seat` VALUES (138, '3C', 4);
INSERT INTO `t_seat` VALUES (139, '3D', 4);
INSERT INTO `t_seat` VALUES (140, '3E', 4);
INSERT INTO `t_seat` VALUES (141, '4A', 4);
INSERT INTO `t_seat` VALUES (142, '4B', 4);
INSERT INTO `t_seat` VALUES (143, '4C', 4);
INSERT INTO `t_seat` VALUES (144, '4D', 4);
INSERT INTO `t_seat` VALUES (145, '4E', 4);
INSERT INTO `t_seat` VALUES (146, '5A', 4);
INSERT INTO `t_seat` VALUES (147, '5B', 4);
INSERT INTO `t_seat` VALUES (148, '5C', 4);
INSERT INTO `t_seat` VALUES (149, '5D', 4);
INSERT INTO `t_seat` VALUES (150, '5E', 4);
INSERT INTO `t_seat` VALUES (151, '6A', 4);
INSERT INTO `t_seat` VALUES (152, '6B', 4);
INSERT INTO `t_seat` VALUES (153, '6C', 4);
INSERT INTO `t_seat` VALUES (154, '6D', 4);
INSERT INTO `t_seat` VALUES (155, '6E', 4);
INSERT INTO `t_seat` VALUES (156, '7A', 4);
INSERT INTO `t_seat` VALUES (157, '7B', 4);
INSERT INTO `t_seat` VALUES (158, '7C', 4);
INSERT INTO `t_seat` VALUES (159, '7D', 4);
INSERT INTO `t_seat` VALUES (160, '7E', 4);
INSERT INTO `t_seat` VALUES (161, '8A', 4);
INSERT INTO `t_seat` VALUES (162, '8B', 4);
INSERT INTO `t_seat` VALUES (163, '8C', 4);
INSERT INTO `t_seat` VALUES (164, '8D', 4);
INSERT INTO `t_seat` VALUES (165, '8E', 4);
INSERT INTO `t_seat` VALUES (166, '9A', 4);
INSERT INTO `t_seat` VALUES (167, '9B', 4);
INSERT INTO `t_seat` VALUES (168, '9C', 4);
INSERT INTO `t_seat` VALUES (169, '9D', 4);
INSERT INTO `t_seat` VALUES (170, '9E', 4);
INSERT INTO `t_seat` VALUES (171, '10A', 4);
INSERT INTO `t_seat` VALUES (172, '10B', 4);
INSERT INTO `t_seat` VALUES (173, '10C', 4);
INSERT INTO `t_seat` VALUES (174, '10D', 4);
INSERT INTO `t_seat` VALUES (175, '10E', 4);

-- ----------------------------
-- Table structure for t_site
-- ----------------------------
DROP TABLE IF EXISTS `t_site`;
CREATE TABLE `t_site`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `site_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '站点名称',
  `initials` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '首字母',
  `abbreviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简拼',
  `longitude` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '经度',
  `latitude` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纬度',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_site
-- ----------------------------
INSERT INTO `t_site` VALUES (14, '成都', 'C', 'CD', '1245', '4545');
INSERT INTO `t_site` VALUES (15, '绵阳', 'M', 'MY', '213', '12');
INSERT INTO `t_site` VALUES (16, '德阳', 'D', 'DY', '31', '12');

-- ----------------------------
-- Table structure for t_sold_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_sold_ticket`;
CREATE TABLE `t_sold_ticket`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `ticket_id` int(0) NULL DEFAULT NULL,
  `ticket_value` int(0) NULL DEFAULT NULL,
  `end_date` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `train_id` int(0) NULL DEFAULT NULL,
  `carriage_id` int(0) NULL DEFAULT NULL,
  `order_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sold_ticket
-- ----------------------------
INSERT INTO `t_sold_ticket` VALUES (8, 176, 1, '2022-06-27', 11, 4, 65);
INSERT INTO `t_sold_ticket` VALUES (10, 177, 3, '2022-06-27', 11, 4, 67);

-- ----------------------------
-- Table structure for t_sys_config
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_config`;
CREATE TABLE `t_sys_config`  (
  `sys_config_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sys_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统配置key',
  `sys_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '系统配置value',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`sys_config_id`) USING BTREE,
  INDEX `sys_key_index`(`sys_key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_config
-- ----------------------------
INSERT INTO `t_sys_config` VALUES (1, 'token_expire_time', '30', 'token的过期时间，单位：分');

-- ----------------------------
-- Table structure for t_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_ticket`;
CREATE TABLE `t_ticket`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `train_id` int(0) NULL DEFAULT NULL COMMENT '车次',
  `start_date` bigint(0) NULL DEFAULT NULL COMMENT '出发日期',
  `start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发时间',
  `start_id` int(0) NULL DEFAULT NULL COMMENT '起点站',
  `end_id` int(0) NULL DEFAULT NULL COMMENT '终点站',
  `end_date` bigint(0) NULL DEFAULT NULL COMMENT '达到日期',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '到达时间',
  `passenger_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乘客姓名',
  `passenger_id` char(18) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '乘客身份证号',
  `carriage_id` int(0) NOT NULL COMMENT '车厢编号',
  `seat_type` int(0) NULL DEFAULT NULL COMMENT '级别：0-经济型，1-普通型，2-豪华型',
  `seat_id` int(0) NULL DEFAULT NULL COMMENT '座位号',
  `order_time` bigint(0) NULL DEFAULT NULL COMMENT '下单时间',
  `payment_status` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付状态：0-未支付，1-已支付',
  `user_id` int(0) NULL DEFAULT NULL COMMENT '车票所属用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_ticket
-- ----------------------------

-- ----------------------------
-- Table structure for t_train
-- ----------------------------
DROP TABLE IF EXISTS `t_train`;
CREATE TABLE `t_train`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `train_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '车次',
  `is_return` int(0) NULL DEFAULT NULL COMMENT '是否返程：0-否，1-是',
  `type` int(0) NULL DEFAULT NULL COMMENT '类别：0-特快(T)，1-动车(D)，2-高铁(G)',
  `start_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发日期',
  `end_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出发时间',
  `shift` int(0) NULL DEFAULT NULL COMMENT '班车数量',
  `start_id` int(0) NULL DEFAULT NULL COMMENT '起点',
  `end_id` int(0) NULL DEFAULT NULL COMMENT '终点',
  `time_consume` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '耗时',
  `carriage` int(0) NULL DEFAULT NULL COMMENT '车箱数',
  `train_status` int(0) NULL DEFAULT NULL COMMENT '车次状态：0-禁用，1-启动',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_train
-- ----------------------------
INSERT INTO `t_train` VALUES (11, 'D1212', 1, 1, '20:30', '22:20', 1, 14, 16, '01:50', 1, 1);

-- ----------------------------
-- Table structure for t_train_carriage
-- ----------------------------
DROP TABLE IF EXISTS `t_train_carriage`;
CREATE TABLE `t_train_carriage`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `train_id` int(0) NULL DEFAULT NULL COMMENT '车次',
  `carriage_id` int(0) NULL DEFAULT NULL COMMENT '车厢编号',
  `carriage_order` int(0) NULL DEFAULT NULL COMMENT '车厢顺序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_train_carriage
-- ----------------------------
INSERT INTO `t_train_carriage` VALUES (27, 11, 4, 1);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
  `phone` char(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (5, '18000000000', '4a7d1ed414474e4033ac29ccb8653d9b');
INSERT INTO `t_user` VALUES (6, '18111111111', '4a7d1ed414474e4033ac29ccb8653d9b');

SET FOREIGN_KEY_CHECKS = 1;
