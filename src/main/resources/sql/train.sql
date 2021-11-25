/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50730
Source Host           : localhost:3306
Source Database       : train

Target Server Type    : MYSQL
Target Server Version : 50730
File Encoding         : 65001

Date: 2021-09-30 16:34:38
*/

SET
FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `name`     varchar(255) DEFAULT NULL COMMENT '姓名',
    `phone`    char(11)     DEFAULT NULL COMMENT '手机号码',
    `password` varchar(255) DEFAULT NULL,
    `state`    int(11) DEFAULT NULL COMMENT '管理员状态：0-禁用，1-启动',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_all_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_all_ticket`;
CREATE TABLE `t_all_ticket`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
    `train_id`    int(11) DEFAULT NULL COMMENT '车次',
    `carriage_id` int(11) DEFAULT NULL COMMENT '车厢编号',
    `seat_id`     int(11) DEFAULT NULL COMMENT '座位编号',
    `state`       int(11) DEFAULT NULL COMMENT '是否可用',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_carriage
-- ----------------------------
DROP TABLE IF EXISTS `t_carriage`;
CREATE TABLE `t_carriage`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `carriage_number` varchar(255) DEFAULT NULL COMMENT '车厢编号',
    `price_base`      bigint(20) DEFAULT NULL COMMENT '价格基数',
    `left_seat`       int(11) DEFAULT NULL COMMENT '左侧座位数',
    `right_seat`      int(11) DEFAULT NULL COMMENT '右侧座位数',
    `row_seat`        int(11) DEFAULT NULL COMMENT '排数',
    `seat_count`      int(11) DEFAULT NULL COMMENT '座位数',
    `level_car`       int(11) DEFAULT NULL COMMENT '级别：0-经济型，1-普通型，2-豪华型',
    `adapt_model`     int(11) DEFAULT NULL COMMENT '适配车型：0-动车，1-高铁，2-动车，高铁',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_line
-- ----------------------------
DROP TABLE IF EXISTS `t_line`;
CREATE TABLE `t_line`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `train_id`     int(11) DEFAULT NULL COMMENT '车次',
    `site_id`      int(11) DEFAULT NULL COMMENT '站点名称',
    `time_consume` varchar(255) DEFAULT NULL COMMENT '耗时',
    `site_order`   int(11) DEFAULT NULL COMMENT '站点顺序',
    `site_value`   int(11) DEFAULT NULL COMMENT '站点值',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `order_number`   varchar(255) DEFAULT NULL COMMENT '订单编号',
    `order_time`     bigint(20) DEFAULT NULL COMMENT '下单时间',
    `train_id`       int(11) DEFAULT NULL COMMENT '车次',
    `start_time`     varchar(255) DEFAULT NULL COMMENT '出发时间',
    `start_id`       varchar(255) DEFAULT NULL COMMENT '起点站',
    `end_id`         varchar(255) DEFAULT NULL COMMENT '终点站',
    `end_time`       varchar(255) DEFAULT NULL COMMENT '到达时间',
    `level_car`      int(11) DEFAULT NULL COMMENT '级别：0-经济型，1-普通型，2-豪华型',
    `price`          double       DEFAULT NULL COMMENT '价格',
    `order_status`   int(11) DEFAULT NULL COMMENT '订单状态：0-未支付，1-已支付，2-已取消',
    `passenger_id`   int(11) DEFAULT NULL COMMENT '订单所属用户',
    `seat_id`        int(11) DEFAULT NULL COMMENT '座位编号',
    `carriage_order` int(11) DEFAULT NULL COMMENT '车厢顺序',
    `start_date`     varchar(255) DEFAULT NULL,
    `end_date`       varchar(255) DEFAULT NULL,
    `user_id`        int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_passenger
-- ----------------------------
DROP TABLE IF EXISTS `t_passenger`;
CREATE TABLE `t_passenger`
(
    `id`              int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `user_id`         int(11) DEFAULT NULL COMMENT ' 乘客所属的用户',
    `passenger_name`  varchar(255) DEFAULT NULL COMMENT '乘客姓名',
    `passenger_phone` char(11)     DEFAULT NULL COMMENT '乘客手机号',
    `passenger_card`  varchar(255) DEFAULT NULL COMMENT '身份证',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_seat
-- ----------------------------
DROP TABLE IF EXISTS `t_seat`;
CREATE TABLE `t_seat`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
    `seat_number` varchar(255) DEFAULT NULL COMMENT '座位编号',
    `carriage_id` int(11) DEFAULT NULL COMMENT '车厢编号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=126 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_site
-- ----------------------------
DROP TABLE IF EXISTS `t_site`;
CREATE TABLE `t_site`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `site_name`    varchar(255) DEFAULT NULL COMMENT '站点名称',
    `initials`     varchar(255) DEFAULT NULL COMMENT '首字母',
    `abbreviation` varchar(255) DEFAULT NULL COMMENT '简拼',
    `longitude`    varchar(255) DEFAULT NULL COMMENT '经度',
    `latitude`     varchar(255) DEFAULT NULL COMMENT '纬度',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_sold_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_sold_ticket`;
CREATE TABLE `t_sold_ticket`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `ticket_id`    int(11) DEFAULT NULL,
    `ticket_value` int(11) DEFAULT NULL,
    `end_date`     varchar(255) DEFAULT NULL,
    `train_id`     int(11) DEFAULT NULL,
    `carriage_id`  int(11) DEFAULT NULL,
    `order_id`     int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_ticket
-- ----------------------------
DROP TABLE IF EXISTS `t_ticket`;
CREATE TABLE `t_ticket`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `train_id`       int(11) DEFAULT NULL COMMENT '车次',
    `start_date`     bigint(20) DEFAULT NULL COMMENT '出发日期',
    `start_time`     varchar(255) DEFAULT NULL COMMENT '出发时间',
    `start_id`       int(11) DEFAULT NULL COMMENT '起点站',
    `end_id`         int(11) DEFAULT NULL COMMENT '终点站',
    `end_date`       bigint(20) DEFAULT NULL COMMENT '达到日期',
    `end_time`       varchar(255) DEFAULT NULL COMMENT '到达时间',
    `passenger_name` varchar(255) DEFAULT NULL COMMENT '乘客姓名',
    `passenger_id`   char(18)     DEFAULT NULL COMMENT '乘客身份证号',
    `carriage_id`    int(11) NOT NULL COMMENT '车厢编号',
    `seat_type`      int(11) DEFAULT NULL COMMENT '级别：0-经济型，1-普通型，2-豪华型',
    `seat_id`        int(11) DEFAULT NULL COMMENT '座位号',
    `order_time`     bigint(20) DEFAULT NULL COMMENT '下单时间',
    `payment_status` varchar(255) DEFAULT NULL COMMENT '支付状态：0-未支付，1-已支付',
    `user_id`        int(11) DEFAULT NULL COMMENT '车票所属用户',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train
-- ----------------------------
DROP TABLE IF EXISTS `t_train`;
CREATE TABLE `t_train`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `train_number` varchar(255) DEFAULT NULL COMMENT '车次',
    `is_return`    int(11) DEFAULT NULL COMMENT '是否返程：0-否，1-是',
    `type`         int(11) DEFAULT NULL COMMENT '类别：0-特快(T)，1-动车(D)，2-高铁(G)',
    `start_time`   varchar(255) DEFAULT NULL COMMENT '出发日期',
    `end_time`     varchar(255) DEFAULT NULL COMMENT '出发时间',
    `shift`        int(11) DEFAULT NULL COMMENT '班车数量',
    `start_id`     int(11) DEFAULT NULL COMMENT '起点',
    `end_id`       int(11) DEFAULT NULL COMMENT '终点',
    `time_consume` varchar(255) DEFAULT NULL COMMENT '耗时',
    `carriage`     int(11) DEFAULT NULL COMMENT '车箱数',
    `train_status` int(11) DEFAULT NULL COMMENT '车次状态：0-禁用，1-启动',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_train_carriage
-- ----------------------------
DROP TABLE IF EXISTS `t_train_carriage`;
CREATE TABLE `t_train_carriage`
(
    `id`             int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `train_id`       int(11) DEFAULT NULL COMMENT '车次',
    `carriage_id`    int(11) DEFAULT NULL COMMENT '车厢编号',
    `carriage_order` int(11) DEFAULT NULL COMMENT '车厢顺序',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`       int(11) NOT NULL AUTO_INCREMENT COMMENT '主键，自增',
    `phone`    char(11)     DEFAULT NULL COMMENT '手机号码',
    `password` varchar(255) DEFAULT NULL COMMENT '密码',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
