/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 80011
Source Host           : 127.0.0.1:3306
Source Database       : vip

Target Server Type    : MYSQL
Target Server Version : 80011
File Encoding         : 65001

Date: 2020-01-15 15:58:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for log_login
-- ----------------------------
DROP TABLE IF EXISTS `log_login`;
CREATE TABLE `log_login` (
  `id` char(32) NOT NULL,
  `login_time` datetime DEFAULT NULL,
  `login_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录人帐号',
  `login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录IP',
  `login_account_name` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录人帐号名称',
  `login_device` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '登录设备，对应user-agent',
  `logoff_time` datetime DEFAULT NULL COMMENT '下线时间',
  `online_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '在线时间，单位s',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of log_login
-- ----------------------------

-- ----------------------------
-- Table structure for sys_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_account`;
CREATE TABLE `sys_account` (
  `id` char(32) NOT NULL,
  `account` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `name` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号名称',
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `avatar` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像',
  `last_modify_password_time` datetime DEFAULT NULL COMMENT '最后密码修改时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `remark` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  `last_login_agent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后登录设备',
  `last_login_ip` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '最后登录IP',
  `role` tinyint(1) DEFAULT NULL COMMENT '所属角色（0=vip 1=店员 2=经理）',
  `status` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '用户状态 0正常，1=锁定',
  `create_account_id` char(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_account
-- ----------------------------
INSERT INTO `sys_account` VALUES ('00000000000000000000000000000000', 'admin', '经理', '123456', null, null, null, '', null, null, '2', '1', '0', '2016-08-14 11:49:39');
INSERT INTO `sys_account` VALUES ('07d2ab1f870f8579ab0449f4e3783bc5', 'clerk', '店员', '123456', null, null, null, null, null, null, '1', '1', '0', '2015-10-30 13:52:13');
INSERT INTO `sys_account` VALUES ('0fd00151d1d9b3196954961d22929568', '13888888888', '王老五', '123456', null, null, null, '1', null, null, '0', '1', '0', '2017-11-03 16:14:39');

-- ----------------------------
-- Table structure for sys_clerk
-- ----------------------------
DROP TABLE IF EXISTS `sys_clerk`;
CREATE TABLE `sys_clerk` (
  `id` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '和sys_account共用主键',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '真实姓名',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `emp_no` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '员工编号',
  `work_mobile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工作手机',
  `work_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '工作邮箱',
  `birthday` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sys_clerk
-- ----------------------------

-- ----------------------------
-- Table structure for vip_consume
-- ----------------------------
DROP TABLE IF EXISTS `vip_consume`;
CREATE TABLE `vip_consume` (
  `id` char(32) NOT NULL DEFAULT '',
  `vip_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会员ID',
  `consume_brief` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消费商品简略信息',
  `consume_time` datetime NOT NULL COMMENT '消费时间',
  `order_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '关联订单id',
  `discount_money` int(11) NOT NULL COMMENT '优惠金额(元分)',
  `actual_pay_money` int(255) NOT NULL COMMENT '实际付款(元分)',
  `consume_money` int(11) unsigned NOT NULL COMMENT '消费金额(元分)',
  `status` tinyint(1) NOT NULL COMMENT '状态0=正常 1=作废',
  `remark` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `enjoy_discount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '享受折扣',
  `create_time` datetime NOT NULL,
  `create_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '录入人',
  PRIMARY KEY (`id`),
  KEY `vip_id` (`vip_id`),
  KEY `operator_id` (`create_user_id`),
  CONSTRAINT `vip_consume_ibfk_1` FOREIGN KEY (`vip_id`) REFERENCES `vip_detail` (`id`),
  CONSTRAINT `vip_consume_ibfk_2` FOREIGN KEY (`create_user_id`) REFERENCES `sys_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip_consume
-- ----------------------------
INSERT INTO `vip_consume` VALUES ('7fbf8a77777714a9d744c8861d934f7e', '39ab3e0961bf11e9b28e98e7f42fc1dc', null, '2019-04-19 17:01:52', 'orderId9004', '42532', '868', '43400', '0', 'remark7257', '98', '2020-01-03 11:06:57', '00000000000000000000000000000000');
INSERT INTO `vip_consume` VALUES ('a85af898b781ae891374aa8beeba8f15', '39ab3e0961bf11e9b28e98e7f42fc1dc', null, '2019-04-19 16:55:50', 'orderId9004', '4253200', '-4209800', '43400', '1', 'remark7257', '98', '2020-01-03 11:06:57', '00000000000000000000000000000000');
INSERT INTO `vip_consume` VALUES ('c287c21a913b86084604aabb1d8b3fcf', '39ab3e0961bf11e9b28e98e7f42fc1dc', null, '2019-04-19 17:05:18', 'orderId9004', '42532', '868', '43400', '0', 'remark7257', '98', '2020-01-03 11:06:57', '00000000000000000000000000000000');
INSERT INTO `vip_consume` VALUES ('c6dd94fd8c623d2a5a044e51f6ef9ac1', '39ab3e0961bf11e9b28e98e7f42fc1dc', null, '2019-04-19 17:02:44', 'orderId9004', '42532', '868', '43400', '0', 'remark7257', '98', '2020-01-03 11:06:57', '00000000000000000000000000000000');
INSERT INTO `vip_consume` VALUES ('d6375c15a7d11e7ad1c4f348ed13c96d', '39ab3e0961bf11e9b28e98e7f42fc1dc', null, '2019-04-19 17:26:19', '133341231321323', '980000', '20000', '1000000', '1', '消费10000', '98', '2020-01-03 11:06:57', '00000000000000000000000000000000');
INSERT INTO `vip_consume` VALUES ('dd9aab20aeb632299044ba648f449c5d', '39ab3e0961bf11e9b28e98e7f42fc1dc', null, '2019-04-19 17:01:24', 'orderId9004', '42532', '868', '43400', '0', 'remark7257', '98', '2020-01-03 11:06:57', '00000000000000000000000000000000');
INSERT INTO `vip_consume` VALUES ('eab06ea468684f6b0de4d20bad287bae', '39ab3e0961bf11e9b28e98e7f42fc1dc', null, '2019-04-25 15:13:45', 'orderId9004', '0', '43400', '43400', '0', 'remark7257', '97', '2020-01-03 11:06:57', '00000000000000000000000000000000');

-- ----------------------------
-- Table structure for vip_detail
-- ----------------------------
DROP TABLE IF EXISTS `vip_detail`;
CREATE TABLE `vip_detail` (
  `id` char(32) NOT NULL DEFAULT '',
  `no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'vip号',
  `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号，可作为会员凭证',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号名称(客户姓名)',
  `sex` bit(1) NOT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日(如321，1231)',
  `qq` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `zip_code` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮编',
  `rank_id` tinyint(2) unsigned NOT NULL COMMENT '等级代号',
  `total_discount_money` int(11) DEFAULT NULL COMMENT '总优惠金额',
  `total_consume_money` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '目前消费总金额，单位分元',
  `remark` varchar(256) DEFAULT NULL,
  `create_user_id` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建人',
  `last_consume_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `account` (`no`),
  KEY `create_user_id` (`create_user_id`),
  CONSTRAINT `vip_detail_ibfk_1` FOREIGN KEY (`create_user_id`) REFERENCES `sys_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip_detail
-- ----------------------------
INSERT INTO `vip_detail` VALUES ('39ab379d61bf11e9b28e98e7f42fc1dc', '13888888866', '13888888866', '小石头', '\0', '2021-04-18', '4322323', '4322323@qq.com', null, '1212123', '5', '0', '0', '颠三倒四', '00000000000000000000000000000000', null, '2019-04-18 14:40:28');
INSERT INTO `vip_detail` VALUES ('39ab3e0961bf11e9b28e98e7f42fc1dc', '13888888888', '13888888888', '王老五', '', '2019-04-17', '10000', '10000@qq.com', '北京中关村xxx路xx号', '2000000', '1', null, '15000', null, '00000000000000000000000000000000', null, '2019-03-29 09:31:31');
INSERT INTO `vip_detail` VALUES ('39ab414c61bf11e9b28e98e7f42fc1dc', '11312312312', '11312312312', 'dsadasd', '\0', '2019-04-19', '4325233241', '12312312', null, '3123123', '3', '0', '0', '3213', '00000000000000000000000000000000', null, '2019-04-18 14:37:57');
INSERT INTO `vip_detail` VALUES ('39ab43c861bf11e9b28e98e7f42fc1dc', '13888888899', '13888888899', '喵喵喵', '', '2019-04-18', '12233444', '21321323@qq.com', null, '21321323@qq.com', '2', '0', '0', '啦啦啦啦啦', '00000000000000000000000000000000', null, '2019-04-18 14:39:14');
INSERT INTO `vip_detail` VALUES ('39ab469361bf11e9b28e98e7f42fc1dc', '2313123123', '2313123123', 'dsadasd', '\0', '2019-04-16', '4325233241', '12312312', null, '3123123', '3', '0', '0', '3213', '00000000000000000000000000000000', null, '2019-04-18 14:38:20');
INSERT INTO `vip_detail` VALUES ('39ab49d261bf11e9b28e98e7f42fc1dc', '27637433', '27637433', '晴儿', '\0', '2019-04-18', '122345', 'eqwe2323', null, 'eew', '1', '0', '0', '漂亮', '00000000000000000000000000000000', null, '2019-04-18 14:40:26');
INSERT INTO `vip_detail` VALUES ('39ab4c7a61bf11e9b28e98e7f42fc1dc', '123213123d', '123213123d', 'weewe', '', null, 'ewqe', 'eqwe', null, '二位r', '1', '0', '0', '如w', '00000000000000000000000000000000', null, '2019-04-18 14:38:50');
INSERT INTO `vip_detail` VALUES ('39ab512f61bf11e9b28e98e7f42fc1dc', '23212143', '23212143', 'SherlockR', '\0', '2019-05-03', '4325233241', '12312312', null, 'dsa214121', '5', '0', '0', 'dsadas', '00000000000000000000000000000000', null, '2019-04-18 14:39:13');
INSERT INTO `vip_detail` VALUES ('39ab53d761bf11e9b28e98e7f42fc1dc', '121111234', '121111234', '武松打虎', '', '2019-04-10', '1231652410984', '10086@qq.com', null, '000', '5', '0', '0', '00000', '00000000000000000000000000000000', null, '2019-04-18 14:39:36');
INSERT INTO `vip_detail` VALUES ('39ab564e61bf11e9b28e98e7f42fc1dc', '1722323236292', '1722323236292', '杭杭杭杭', '', '2019-04-21', '435425345', '2354435343@qq.com', null, '400800', '2', '0', '0', null, '00000000000000000000000000000000', null, '2019-04-18 14:40:14');
INSERT INTO `vip_detail` VALUES ('39ab591061bf11e9b28e98e7f42fc1dc', '13983838383', '13983838383', '吕布', '', '2019-04-18', '654321', 'ada213@yeah.net', null, '400900', '4', '0', '0', '一骑当千', '00000000000000000000000000000000', null, '2019-04-18 14:39:46');
INSERT INTO `vip_detail` VALUES ('39ab5d6c61bf11e9b28e98e7f42fc1dc', '18888888888', '18888888888', '寒雪风影', '', '2019-04-08', '88888888', '2390785393@qq.com', null, '546456', '1', '0', '0', null, '00000000000000000000000000000000', null, '2019-04-18 14:39:15');
INSERT INTO `vip_detail` VALUES ('39ab5fec61bf11e9b28e98e7f42fc1dc', '12345678546', '12345678546', '月儿', '\0', '2019-04-11', '122345', 'eqwe2323', null, 'e', '1', '0', '0', '漂亮', '00000000000000000000000000000000', null, '2019-04-18 14:39:59');
INSERT INTO `vip_detail` VALUES ('39ab62c961bf11e9b28e98e7f42fc1dc', '17985642236', '17985642236', '刘黄二叔', '', '2019-04-03', '3267748958', '35967@qq.com', null, '00000', '1', '0', '0', 'qwe', '00000000000000000000000000000000', null, '2019-04-18 14:39:53');
INSERT INTO `vip_detail` VALUES ('39ab657a61bf11e9b28e98e7f42fc1dc', '17985642237', '17985642237', '刘黄书', '', '2019-04-03', '3267748951', '35967@qq.com', null, '00000', '1', '0', '0', 'qwe', '00000000000000000000000000000000', null, '2019-04-18 14:39:34');
INSERT INTO `vip_detail` VALUES ('39ab682b61bf11e9b28e98e7f42fc1dc', '12345678901', '12345678901', '李杭', '', '2019-04-10', '1231652410984', '10086@qq.com', null, '000', '5', '0', '0', '00000', '00000000000000000000000000000000', null, '2019-04-18 14:38:54');
INSERT INTO `vip_detail` VALUES ('39ab6ab461bf11e9b28e98e7f42fc1dc', '21321312321', '21321312321', 'sadsadda', '\0', '2019-04-24', '4325233241', '12312312', null, 'dsa214121', '3', '0', '0', 'dsadas', '00000000000000000000000000000000', null, '2019-04-18 14:38:45');
INSERT INTO `vip_detail` VALUES ('39ab6d2f61bf11e9b28e98e7f42fc1dc', '1112143242545435643', '1112143242545435643', 'SherlockT', '\0', '2019-05-03', '4325233241', '12312312', null, 'dsa214121', '5', '0', '0', 'dsadas', '00000000000000000000000000000000', null, '2019-04-18 14:39:35');
INSERT INTO `vip_detail` VALUES ('39ab6f9961bf11e9b28e98e7f42fc1dc', '13888888887', '13888888887', '张飞飞', '\0', '2019-04-17', '321', '2', '354', '43', '1', null, '0', '543', '00000000000000000000000000000000', null, '2019-03-29 09:31:34');
INSERT INTO `vip_detail` VALUES ('39ab733e61bf11e9b28e98e7f42fc1dc', '121111231223', '121111231223', '王伟', '', '2019-04-10', '1231652410984', '10086@qq.com', null, '000', '5', '0', '0', '00000', '00000000000000000000000000000000', null, '2019-04-18 14:40:05');
INSERT INTO `vip_detail` VALUES ('39ab76fe61bf11e9b28e98e7f42fc1dc', '1555555555', '1555555555', '凌风', '', '2019-04-03', '99999999', '456465453@qq.com', null, '46465', '1', '0', '0', null, '00000000000000000000000000000000', null, '2019-04-18 14:40:19');
INSERT INTO `vip_detail` VALUES ('39ab7aac61bf11e9b28e98e7f42fc1dc', '1888888888', '1888888888', '石不归', '', '2019-04-13', '1225382428', '1225382428@qq.com', null, '000000', '5', '0', '0', null, '00000000000000000000000000000000', null, '2019-04-18 14:39:01');
INSERT INTO `vip_detail` VALUES ('39ab7de261bf11e9b28e98e7f42fc1dc', '13689676767', '13689676767', '石文超', '\0', '2001-07-14', '1575778578', '2768786876788168@qq.com', null, '693257325', '1', '0', '0', 'hbtkkkikk', '00000000000000000000000000000000', null, '2019-04-18 14:40:19');
INSERT INTO `vip_detail` VALUES ('39ab817a61bf11e9b28e98e7f42fc1dc', '1364856225', '1364856225', '段成兴', '\0', '1998-07-18', '19458452', '28585989888168@qq.com', null, '694685', '3', '0', '0', 'hbtyhtyhjtnjt', '00000000000000000000000000000000', null, '2019-04-18 14:39:43');
INSERT INTO `vip_detail` VALUES ('39ab841061bf11e9b28e98e7f42fc1dc', '17989642236', '17989642236', '刘黄三叔', '', '2019-04-03', '56987411689', '35967@qq.com', null, '00000', '1', '0', '0', 'qwe', '00000000000000000000000000000000', null, '2019-04-18 14:40:11');
INSERT INTO `vip_detail` VALUES ('39ab867a61bf11e9b28e98e7f42fc1dc', '15879985469', '15879985469', 'hahah', '', '2019-04-03', '789456123', 'asd@qq.com', null, '00000', '1', '0', '0', 'qwe', '00000000000000000000000000000000', null, '2019-04-18 14:38:04');
INSERT INTO `vip_detail` VALUES ('39ab88f161bf11e9b28e98e7f42fc1dc', '13696416674', '13696416674', '罗杰费德勒', '', '2019-04-09', '824281101', 'dsfsf01@qq.com', null, '401147fdsd', '5', '0', '0', '哈哈哈哈哈哈', '00000000000000000000000000000000', null, '2019-04-18 14:39:08');
INSERT INTO `vip_detail` VALUES ('39ab8c0861bf11e9b28e98e7f42fc1dc', '15086900253', '15086900253', '伍斯龙', '', '2019-04-18', '123456', '123@yeah.net', null, '400900', '5', '0', '0', '备注备注', '00000000000000000000000000000000', null, '2019-04-18 14:38:35');
INSERT INTO `vip_detail` VALUES ('39ab8e7b61bf11e9b28e98e7f42fc1dc', '13856972045', '13856972045', '9527', '', '2019-04-03', '654987321', '654987@qq.com', null, '00000', '1', '0', '0', 'qwe', '00000000000000000000000000000000', null, '2019-04-18 14:38:38');
INSERT INTO `vip_detail` VALUES ('39ab910c61bf11e9b28e98e7f42fc1dc', '18875125259', '18875125259', '李杭', '', '2019-04-18', '824281101', '824281101@qq.com', null, '401147', '5', '0', '0', '哈哈哈哈哈哈', '00000000000000000000000000000000', null, '2019-04-18 14:38:10');
INSERT INTO `vip_detail` VALUES ('39ab93bd61bf11e9b28e98e7f42fc1dc', '21991991232321908904', '21991991232321908904', 'SherlockX', '\0', '2019-05-03', '4325233241', '12312312', null, 'dsa214121', '5', '0', '0', 'dskjfkjdsofkj', '00000000000000000000000000000000', null, '2019-04-18 14:40:13');
INSERT INTO `vip_detail` VALUES ('39ab969261bf11e9b28e98e7f42fc1dc', '172825273629', '172825273629', '小杭杭', '\0', '2019-04-09', '213123123123', '213123123123@qq.com', null, '400800', '5', '0', '0', null, '00000000000000000000000000000000', null, '2019-04-18 14:38:51');
INSERT INTO `vip_detail` VALUES ('39ab990461bf11e9b28e98e7f42fc1dc', '13666666666', '13666666666', 'hahah', '', '1994-04-18', '19465165165', '285895888168@qq.com', null, '694685', '4', '0', '0', 'afwfewfewfew', '00000000000000000000000000000000', null, '2019-04-18 14:38:59');
INSERT INTO `vip_detail` VALUES ('39ab9b7761bf11e9b28e98e7f42fc1dc', '17985642235', '17985642235', '9527', '', '2019-04-03', '3267748954', '35987@qq.com', null, '00000', '1', '0', '0', 'qwe', '00000000000000000000000000000000', null, '2019-04-18 14:39:02');
INSERT INTO `vip_detail` VALUES ('39ab9dc661bf11e9b28e98e7f42fc1dc', '17589008997', '17589008997', '左慈', '', '2019-04-01', '55667788', '55667788@qq.com', null, '000000', '3', '0', '0', '中华第一符师', '00000000000000000000000000000000', null, '2019-04-18 14:40:24');
INSERT INTO `vip_detail` VALUES ('39aba01961bf11e9b28e98e7f42fc1dc', '+8613637799479', '+8613637799479', 'ben yi', '', '2019-04-17', '222222222222', 'yizhuoyan@hotmail.com', null, '400000', '1', '0', '0', 'www', '00000000000000000000000000000000', null, '2019-04-16 10:48:53');
INSERT INTO `vip_detail` VALUES ('39aba2e561bf11e9b28e98e7f42fc1dc', '13333334444', '13333334444', '关羽', '', '2019-04-09', '824281101', 'dsfsf01@qq.com', null, '401147fdsd', '5', '0', '0', '哈哈哈哈哈哈', '00000000000000000000000000000000', null, '2019-04-18 14:39:41');
INSERT INTO `vip_detail` VALUES ('39aba54f61bf11e9b28e98e7f42fc1dc', 'account3148', 'mobile8685', 'name9024', '\0', null, 'qq9734', 'email7592', 'address4963', 'zipCode7067', '1', '73', '27', 'remark2960', '00000000000000000000000000000000', '2019-04-16 09:09:21', '2019-04-16 09:09:21');
INSERT INTO `vip_detail` VALUES ('49131fbb3316a7dbe564bbcf0e74fc28', 'mobile9075', 'mobile9075', 'name6161', '', '1964-01-01', 'qq9240', 'email3689', 'address883', 'zipCode521', '1', '0', '0', 'remark2965', '00000000000000000000000000000000', null, '2019-04-25 15:13:45');

-- ----------------------------
-- Table structure for vip_rank
-- ----------------------------
DROP TABLE IF EXISTS `vip_rank`;
CREATE TABLE `vip_rank` (
  `id` tinyint(2) unsigned NOT NULL COMMENT '等级代号',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '等级名称',
  `need_consume` int(11) unsigned NOT NULL COMMENT '升级需消费总金额(单位元)',
  `enjoy_discount` tinyint(2) unsigned NOT NULL COMMENT '可享受折扣',
  `show_order` int(11) DEFAULT '0' COMMENT '展示顺序',
  `remark` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of vip_rank
-- ----------------------------
INSERT INTO `vip_rank` VALUES ('1', '普通VIP', '500', '98', '1', '消费满500即可成为普通会员');
INSERT INTO `vip_rank` VALUES ('2', '黄金VIP', '5000', '95', '2', null);
INSERT INTO `vip_rank` VALUES ('3', '白金VIP', '20000', '90', '3', null);
INSERT INTO `vip_rank` VALUES ('4', '钻石VIP', '100000', '85', '4', null);
INSERT INTO `vip_rank` VALUES ('5', '终身VIP', '500000', '80', '5', null);
