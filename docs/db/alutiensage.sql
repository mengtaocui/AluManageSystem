/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : activitytabl

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2017-11-05 10:47:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activitytabl
-- ----------------------------
DROP TABLE IF EXISTS `activitytabl`;
CREATE TABLE `activitytabl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activity_title` varchar(255) DEFAULT NULL,
  `activity_status` varchar(255) DEFAULT NULL,
  `activity_places` varchar(255) DEFAULT NULL,
  `activity_person` varchar(255) DEFAULT NULL,
  `activity_statime` varchar(255) DEFAULT NULL,
  `activity_endtime` varchar(255) DEFAULT NULL,
  `activity_stasign` varchar(255) DEFAULT NULL,
  `activity_endsign` varchar(255) DEFAULT NULL,
  `activity_updatedate` varchar(255) DEFAULT NULL,
  `activity_createdate` varchar(255) DEFAULT NULL,
  `activity_container` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of activitytabl
-- ----------------------------
INSERT INTO `activitytabl` VALUES ('2', '四维空间', '0', '四维空间1号室', 'admintest', '2017-11-01 上午 11:10', '2017-11-01 上午 11:10', '2017-11-01 上午 11:10', '2017-11-01 上午 11:10', '2017-11-01 11:10:16', '2017-11-01 11:10:16', '兰州新区');

-- ----------------------------
-- Table structure for alumanagetabl
-- ----------------------------
DROP TABLE IF EXISTS `alumanagetabl`;
CREATE TABLE `alumanagetabl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `adminname` varchar(50) DEFAULT NULL,
  `adminpassword` varchar(50) DEFAULT NULL,
  `adminrank` varchar(10) DEFAULT NULL,
  `adminCreatedate` varchar(50) DEFAULT NULL,
  `adminUpdatedate` varchar(50) DEFAULT NULL,
  `adminImg` varchar(4000) DEFAULT NULL,
  `adminEmail` varchar(100) DEFAULT NULL,
  `adminTel` varchar(50) DEFAULT NULL,
  `adminGroup` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alumanagetabl
-- ----------------------------
INSERT INTO `alumanagetabl` VALUES ('1', 'admin', 'P5cT6aN1Xy7haFn56xfuTA==', '1', '2017-07-11 14:39:7', '2017-07-11 14:39:7', 'userImg/2017-08-15/82d2d92f-d8d2-4e1d-bd7b-350610dced8a_bg.PNG', '13993356265@163.com', '13993356265', '超级管理员组');
INSERT INTO `alumanagetabl` VALUES ('2', 'admintest', 'sQM5mDuX1U/B/TUsBy5SIA==', '2', '2017-07-11 14:39:7', '2017-07-11 14:39:7', 'img/adminImg.png', '13993356265@163.com', '13993356265', '普通管理员组');

-- ----------------------------
-- Table structure for classmatetabl
-- ----------------------------
DROP TABLE IF EXISTS `classmatetabl`;
CREATE TABLE `classmatetabl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `classmate_name` varchar(50) DEFAULT NULL,
  `grade_id` int(11) DEFAULT NULL,
  `classmate_num` varchar(200) DEFAULT NULL,
  `classmate_tel` varchar(50) DEFAULT NULL,
  `classmate_address` varchar(1000) DEFAULT NULL,
  `classmate_email` varchar(100) DEFAULT NULL,
  `classmate_descri` varchar(4000) DEFAULT NULL,
  `classmate_password` varchar(200) DEFAULT NULL,
  `create_date` varchar(50) DEFAULT NULL,
  `update_date` varchar(50) DEFAULT NULL,
  `classmate_img` varchar(4000) DEFAULT NULL,
  `classmate_rank` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of classmatetabl
-- ----------------------------
INSERT INTO `classmatetabl` VALUES ('4', '刘小星', '1', '13700002', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-11 14:39:7', '2017-07-12 9:30:6', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('6', '颉小强', '1', '13700003', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:4:9', '2017-07-12 9:17:19', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('7', '常小明', '1', '13700004', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:5:33', '2017-07-12 9:30:20', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('8', '刘作贤', '1', '13700005', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:7:1', '2017-07-12 9:7:1', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('9', '岳文燕', '1', '13700006', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:18:26', '2017-07-12 9:18:26', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('10', '刘文斌', '1', '13700007', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:20:20', '2017-07-12 9:20:20', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('11', '李泽鹏', '1', '13700008', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:21:25', '2017-07-12 9:21:25', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('12', '擎天柱', '1', '13700009', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:22:29', '2017-07-12 9:22:29', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('13', '黑寡妇', '1', '13700010', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:24:46', '2017-07-12 9:24:46', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('14', '李云龙', '1', '13700011', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:26:29', '2017-07-12 9:26:29', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('15', '楚云飞', '1', '13700012', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:32:25', '2017-07-12 9:32:25', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('16', '李建腾', '1', '13700013', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:40:33', '2017-07-12 9:40:33', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('17', '张三丰', '1', '13700014', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:43:23', '2017-07-12 9:43:23', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('18', '张无忌', '1', '13700015', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 9:44:52', '2017-07-12 9:44:52', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('19', '王老五', '1', '13700016', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 17:29:31', '2017-07-12 17:29:31', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('20', '王三四', '1', '13700017', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 17:55:53', '2017-07-12 17:55:53', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('21', '王柳柳', '1', '13700018', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 18:0:13', '2017-07-12 18:0:13', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('22', '张大大', '1', '13700019', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-12 18:14:21', '2017-07-12 18:14:21', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('23', '张老三', '1', '13700020', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-13 9:0:15', '2017-07-13 9:0:15', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('24', '李四光', '1', '13700021', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-13 9:9:7', '2017-07-13 9:9:7', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('25', '刘虎祥', '1', '13700022', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-13 9:9:55', '2017-07-13 9:9:55', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('26', '王思聪', '1', '13700023', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-13 17:23:17', '2017-07-13 17:23:17', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('27', '王健林', '1', '13700024', '13993356265', '甘肃省兰州市永登县产业孵化大厦', '13993356265@163.com', '大家好！我叫朱峰，我是1990届公共卫生学院预防医学1班的班长.', 'aTjiFWI1BC/nHsO04X+CaA==', '2017-07-13 17:28:37', '2017-07-13 17:28:37', 'img/adminImg.png', '否');
INSERT INTO `classmatetabl` VALUES ('63', '朱钰飒', '1', '1370001908', '15193133963', '甘肃省兰州市永登县产业孵化大厦', '139933656265@163.com', '我是朱飒大家好！我是1990届公共卫生学院预防医学1班的班长.', null, '2017-07-26 14:16:36', '2017-10-17 9:11:21', 'img/adminImg.png', '是');

-- ----------------------------
-- Table structure for collegetabl
-- ----------------------------
DROP TABLE IF EXISTS `collegetabl`;
CREATE TABLE `collegetabl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(50) DEFAULT NULL,
  `create_date` varchar(50) DEFAULT NULL,
  `update_date` varchar(50) DEFAULT NULL,
  `editor_person` varchar(50) DEFAULT NULL,
  `editor_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of collegetabl
-- ----------------------------
INSERT INTO `collegetabl` VALUES ('1', '公共卫生学院', '2017-08-01 16:6:42', '2017-09-28 16:6:44', 'admintest', 'gongogn1');

-- ----------------------------
-- Table structure for gradetabl
-- ----------------------------
DROP TABLE IF EXISTS `gradetabl`;
CREATE TABLE `gradetabl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(100) DEFAULT NULL,
  `year_id` int(11) DEFAULT NULL,
  `grade_date` varchar(50) DEFAULT NULL,
  `grade_person` varchar(50) DEFAULT NULL,
  `grade_tel` varchar(50) DEFAULT NULL,
  `grade_qq` varchar(50) DEFAULT NULL,
  `grade_descri` varchar(4000) DEFAULT NULL,
  `update_date` varchar(50) DEFAULT NULL,
  `editor_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of gradetabl
-- ----------------------------
INSERT INTO `gradetabl` VALUES ('1', '1990届公共卫生学院预防医学1班', '1', '2017-06-24 11:6:39', 'adminTiensage', '15193133963', '2038104071', '大家好!我是1990届公共卫生学院预防医学1班的创建人朱峰.', '2017-10-11 10:59:53', null);
INSERT INTO `gradetabl` VALUES ('28', '1990届公共卫生学院预防医学2班', '1', '2017-07-17 11:6:39', 'adminTiensage', '15193133963', '2038104076', '大家好!我是1990届公共卫生学院预防医学2班的创建人朱峰.', '2017-07-18 13:35:43', '1990届公共卫生学院预防医学2班');
INSERT INTO `gradetabl` VALUES ('40', '1991届公共卫生学院预防医学1班', '1025', '2017-07-18 9:55:41', 'adminTiensage', '15193133963', '260060549', '大家好!我是1991届公共卫生学院预防医学1班的创建人朱峰.', '2017-07-18 13:36:34', '1991届公共卫生学院预防医学1班');
INSERT INTO `gradetabl` VALUES ('41', '1991届公共卫生学院预防医学2班', '1025', '2017-07-18 9:56:12', 'adminTiensage', '15193133963', '2038104076', '大家好!我是1991届公共卫生学院预防医学2班的创建人朱峰.', '2017-07-18 13:36:53', '1991届公共卫生学院预防医学2班');

-- ----------------------------
-- Table structure for newsmodeltabl
-- ----------------------------
DROP TABLE IF EXISTS `newsmodeltabl`;
CREATE TABLE `newsmodeltabl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `newsType_id` int(11) DEFAULT NULL,
  `newsModel_person` varchar(500) DEFAULT NULL,
  `newsModel_createdate` varchar(500) DEFAULT NULL,
  `newsModel_updatadate` varchar(500) DEFAULT NULL,
  `newsModel_name` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of newsmodeltabl
-- ----------------------------
INSERT INTO `newsmodeltabl` VALUES ('1', '1', 'admin', '2017-08-22 9:10:2', '2017-08-28 9:33:23', '新闻');
INSERT INTO `newsmodeltabl` VALUES ('2', '2', 'admin', '2017-08-22 9:12:49', '2017-08-22 9:12:49', '人物');
INSERT INTO `newsmodeltabl` VALUES ('3', '3', 'admin', '2017-08-22 9:14:25', '2017-08-22 9:14:25', '旧忆');
INSERT INTO `newsmodeltabl` VALUES ('4', '1', 'admin', '2017-08-22 9:15:2', '2017-08-22 9:15:2', '人物');
INSERT INTO `newsmodeltabl` VALUES ('5', '1', 'admin', '2017-08-22 9:15:47', '2017-08-22 9:15:47', '旧忆');
INSERT INTO `newsmodeltabl` VALUES ('6', '2', 'admin', '2017-08-22 9:16:46', '2017-08-22 9:16:46', '新闻');
INSERT INTO `newsmodeltabl` VALUES ('7', '2', 'admin', '2017-08-22 9:16:54', '2017-08-22 9:16:54', '旧忆');
INSERT INTO `newsmodeltabl` VALUES ('8', '3', 'admin', '2017-08-22 9:17:16', '2017-08-22 9:17:16', '新闻');
INSERT INTO `newsmodeltabl` VALUES ('9', '3', 'admin', '2017-08-22 9:17:24', '2017-08-22 9:17:24', '人物');

-- ----------------------------
-- Table structure for newstabl
-- ----------------------------
DROP TABLE IF EXISTS `newstabl`;
CREATE TABLE `newstabl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `news_type` varchar(1000) DEFAULT NULL,
  `news_module` varchar(1000) DEFAULT NULL,
  `news_title` varchar(1000) DEFAULT NULL,
  `news_createdate` varchar(1000) DEFAULT NULL,
  `news_updatadate` varchar(1000) DEFAULT NULL,
  `news_img` varchar(1000) DEFAULT NULL,
  `news_person` varchar(1000) DEFAULT NULL,
  `news_scannedCounts` varchar(1000) DEFAULT NULL,
  `news_contents` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of newstabl
-- ----------------------------

-- ----------------------------
-- Table structure for newstypetabl
-- ----------------------------
DROP TABLE IF EXISTS `newstypetabl`;
CREATE TABLE `newstypetabl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `newsType_name` varchar(500) DEFAULT NULL,
  `newsType_createdate` varchar(500) DEFAULT NULL,
  `newsType_person` varchar(500) DEFAULT NULL,
  `newsType_updatadate` varchar(500) DEFAULT NULL,
  `newsType_descri` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of newstypetabl
-- ----------------------------
INSERT INTO `newstypetabl` VALUES ('1', '校史概况', '2017-08-17 17:27:31', 'admin', '2017-08-21 9:3:27', '校史概况');
INSERT INTO `newstypetabl` VALUES ('2', '行业精英', '2017-08-17 17:28:24', 'admin', '2017-08-21 9:12:14', '行业精英');
INSERT INTO `newstypetabl` VALUES ('3', '学子风采', '2017-08-17 19:0:57', 'admin', '2017-08-21 9:12:38', '学子风采');
INSERT INTO `newstypetabl` VALUES ('4', '校友动态', '2017-08-17 19:3:15', 'admin', '2017-08-21 9:13:23', '校友动态');
INSERT INTO `newstypetabl` VALUES ('5', '人物访谈', '2017-08-17 19:3:25', 'admin', '2017-08-21 9:13:58', '人物访谈');
INSERT INTO `newstypetabl` VALUES ('6', '学校新闻', '2017-08-17 19:4:46', 'admin', '2017-08-21 9:14:18', '学校新闻');
INSERT INTO `newstypetabl` VALUES ('7', '校园旧事', '2017-08-17 19:4:46', 'admin', '2017-08-21 9:14:59', '校园旧事');
INSERT INTO `newstypetabl` VALUES ('8', '捐赠新闻', '2017-08-18 8:48:50', 'admin', '2017-08-21 9:15:11', '捐赠新闻');
INSERT INTO `newstypetabl` VALUES ('9', '最新资讯', '2017-08-18 8:53:44', 'admin', '2017-08-21 9:19:44', '最新资讯');
INSERT INTO `newstypetabl` VALUES ('13', '测试类型', '2017-08-21 9:52:9', 'admin', '2017-08-21 9:52:9', '测试类型');
INSERT INTO `newstypetabl` VALUES ('14', 'type测试', '2017-08-21 10:30:32', 'admin', '2017-08-21 10:30:32', '');

-- ----------------------------
-- Table structure for noticestabl
-- ----------------------------
DROP TABLE IF EXISTS `noticestabl`;
CREATE TABLE `noticestabl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notices_title` varchar(500) DEFAULT NULL,
  `notices_createdate` varchar(500) DEFAULT NULL,
  `notices_updatadate` varchar(500) DEFAULT NULL,
  `notices_img` varchar(500) DEFAULT NULL,
  `notices_person` varchar(500) DEFAULT NULL,
  `notices_scannedCounts` varchar(500) DEFAULT NULL,
  `notices_contents` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of noticestabl
-- ----------------------------

-- ----------------------------
-- Table structure for yeartabl
-- ----------------------------
DROP TABLE IF EXISTS `yeartabl`;
CREATE TABLE `yeartabl` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `year_name` varchar(50) DEFAULT NULL,
  `college_id` int(11) DEFAULT NULL,
  `create_date` varchar(50) DEFAULT NULL,
  `editor_person` varchar(50) DEFAULT NULL,
  `update_date` varchar(50) DEFAULT NULL,
  `editor_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1050 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of yeartabl
-- ----------------------------
INSERT INTO `yeartabl` VALUES ('1', '1990届', '1', null, 'admintiensage', '2017-07-20 17:7:15', '1990届');
INSERT INTO `yeartabl` VALUES ('1025', '', '1', '2017-07-17 11:38:31', 'admintest', '2017-11-02 17:17:0', '1991届');
INSERT INTO `yeartabl` VALUES ('1032', '1992届', '1', '2017-08-02 9:14:43', 'admin', '2017-08-02 9:14:43', '1992届');
INSERT INTO `yeartabl` VALUES ('1033', '1993届', '1', '2017-08-02 9:14:55', 'admin', '2017-08-02 9:14:55', '1993届');
INSERT INTO `yeartabl` VALUES ('1034', '1994届', '1', '2017-08-02 10:9:40', 'admintest', '2017-10-16 16:33:44', '1994届');
INSERT INTO `yeartabl` VALUES ('1035', '1995届', '1', '2017-08-02 10:9:48', 'admin', '2017-08-02 10:9:48', '1995届');
INSERT INTO `yeartabl` VALUES ('1036', '1996届', '1', '2017-08-02 10:9:55', 'admin', '2017-08-02 10:9:55', '1996届');
INSERT INTO `yeartabl` VALUES ('1037', '1997届', '1', '2017-08-02 10:10:3', 'admin', '2017-08-02 10:10:3', '1997届');
INSERT INTO `yeartabl` VALUES ('1038', '1998届', '1', '2017-08-02 10:10:19', 'admin', '2017-08-02 10:10:19', '1998届');
INSERT INTO `yeartabl` VALUES ('1039', '1999届', '1', '2017-08-02 10:10:34', 'admin', '2017-08-02 10:10:34', '1999届');
INSERT INTO `yeartabl` VALUES ('1040', '2000届', '1', '2017-08-02 10:11:6', 'admin', '2017-08-02 10:11:6', '2000届');
INSERT INTO `yeartabl` VALUES ('1041', '2001届', '1', '2017-08-02 10:11:20', 'admin', '2017-08-02 10:11:20', '2001届');
INSERT INTO `yeartabl` VALUES ('1042', '2002届', '1', '2017-08-02 10:11:26', 'admin', '2017-08-02 10:11:26', '2002届');
INSERT INTO `yeartabl` VALUES ('1043', '2003届', '1', '2017-08-02 10:11:36', 'admin', '2017-08-02 10:11:36', '2003届');
INSERT INTO `yeartabl` VALUES ('1044', '2004届', '1', '2017-08-02 10:11:43', 'admin', '2017-08-02 10:11:43', '2004届');
INSERT INTO `yeartabl` VALUES ('1045', '2005届', '1', '2017-08-02 10:11:51', 'admin', '2017-08-02 10:11:51', '2005届');
INSERT INTO `yeartabl` VALUES ('1046', '2006届', '1', '2017-08-02 10:12:10', 'admin', '2017-08-02 10:12:10', '2006届');
INSERT INTO `yeartabl` VALUES ('1047', '2007届', '1', '2017-08-02 10:12:23', 'admin', '2017-08-02 10:12:23', '2007届');
INSERT INTO `yeartabl` VALUES ('1048', '2008届', '1', '2017-08-02 10:12:39', 'admin', '2017-08-02 10:12:39', '2008届');
INSERT INTO `yeartabl` VALUES ('1049', '2009届', '1', '2017-08-02 10:12:49', 'admin', '2017-08-02 10:12:49', '2009届');
