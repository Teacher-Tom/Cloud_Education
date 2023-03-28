/*
 Navicat Premium Data Transfer

 Source Server         : huawei
 Source Server Type    : MySQL
 Source Server Version : 50741
 Source Host           : 123.60.88.31:1001
 Source Schema         : cloud_manager

 Target Server Type    : MySQL
 Target Server Version : 50741
 File Encoding         : 65001

 Date: 27/03/2023 18:26:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `auth_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `auth_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO `authority` VALUES ('1', 'auth_test', 'auth_test');
INSERT INTO `authority` VALUES ('2', 'auth_course', 'auth_course');
INSERT INTO `authority` VALUES ('3', 'auth_teacher', 'auth_teacher');
INSERT INTO `authority` VALUES ('4', 'auth_admin', 'auth_admin');

-- ----------------------------
-- Table structure for course_discussion
-- ----------------------------
DROP TABLE IF EXISTS `course_discussion`;
CREATE TABLE `course_discussion`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `page` int(11) NULL DEFAULT NULL,
  `send_time` datetime NOT NULL,
  `likes` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_discussion
-- ----------------------------

-- ----------------------------
-- Table structure for course_discussion_reply
-- ----------------------------
DROP TABLE IF EXISTS `course_discussion_reply`;
CREATE TABLE `course_discussion_reply`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `discussion_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `send_time` datetime NOT NULL,
  `likes` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_discussion_reply
-- ----------------------------

-- ----------------------------
-- Table structure for course_discussion_resource
-- ----------------------------
DROP TABLE IF EXISTS `course_discussion_resource`;
CREATE TABLE `course_discussion_resource`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `discussion_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '每条讨论下附带的图片、文件、链接资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_discussion_resource
-- ----------------------------

-- ----------------------------
-- Table structure for course_homework
-- ----------------------------
DROP TABLE IF EXISTS `course_homework`;
CREATE TABLE `course_homework`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `is_outdated` tinyint(1) NOT NULL DEFAULT 0,
  `review` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_global` tinyint(1) NOT NULL DEFAULT 0,
  `timetable_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_homework
-- ----------------------------
INSERT INTO `course_homework` VALUES ('1638550521385598978', '第一章作业一', '2023-03-22 00:00:00', '2023-03-30 00:00:00', 0, NULL, '1001', '1', 1, NULL);
INSERT INTO `course_homework` VALUES ('1638803499988389890', '作业一', '2023-04-01 00:00:00', '2023-04-08 00:00:00', 0, NULL, '1002', '1', 1, NULL);

-- ----------------------------
-- Table structure for course_homework_context
-- ----------------------------
DROP TABLE IF EXISTS `course_homework_context`;
CREATE TABLE `course_homework_context`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `homework_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ref_answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `picture_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_homework_context
-- ----------------------------
INSERT INTO `course_homework_context` VALUES ('1638550521926664194', '1638550521385598978', '题目一', '12', 'string');
INSERT INTO `course_homework_context` VALUES ('1638550522190905346', '1638550521385598978', '题目二', 'test', 'string');
INSERT INTO `course_homework_context` VALUES ('1638803500768530434', '1638803499988389890', '选择1', 'A', 'string');
INSERT INTO `course_homework_context` VALUES ('1638803501028577281', '1638803499988389890', '选择2', 'C', 'string');
INSERT INTO `course_homework_context` VALUES ('1638803501422841857', '1638803499988389890', '选择3', 'B', 'string');

-- ----------------------------
-- Table structure for course_homework_student
-- ----------------------------
DROP TABLE IF EXISTS `course_homework_student`;
CREATE TABLE `course_homework_student`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `student_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `homework_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `score` int(11) NULL DEFAULT 0,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_corrected` tinyint(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_homework_student
-- ----------------------------
INSERT INTO `course_homework_student` VALUES ('1639180955194802177', '3', '1638550521385598978', 90, '做的不错，继续加油', 1);

-- ----------------------------
-- Table structure for course_homework_submit
-- ----------------------------
DROP TABLE IF EXISTS `course_homework_submit`;
CREATE TABLE `course_homework_submit`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `homework_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `context_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `submit_answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `student_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `score` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_homework_submit
-- ----------------------------
INSERT INTO `course_homework_submit` VALUES ('1639180954402078721', '1638550521385598978', '1638550521926664194', '12', '3', 0);
INSERT INTO `course_homework_submit` VALUES ('1639180954930561025', '1638550521385598978', '1638550522190905346', 'test', '3', 0);

-- ----------------------------
-- Table structure for course_message
-- ----------------------------
DROP TABLE IF EXISTS `course_message`;
CREATE TABLE `course_message`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_message
-- ----------------------------
INSERT INTO `course_message` VALUES ('1', '开课通知', 'xxxxxxxxxxxxx', '1001');

-- ----------------------------
-- Table structure for course_resource
-- ----------------------------
DROP TABLE IF EXISTS `course_resource`;
CREATE TABLE `course_resource`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `file_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '云盘文件id',
  `timetable_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_global` tinyint(1) NOT NULL COMMENT '是否为全局资源（即小节资源和全局资源）',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course_resource
-- ----------------------------

-- ----------------------------
-- Table structure for manager_chapter
-- ----------------------------
DROP TABLE IF EXISTS `manager_chapter`;
CREATE TABLE `manager_chapter`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节id',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节名',
  `number` int(10) NOT NULL COMMENT '章节号',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_chapter
-- ----------------------------
INSERT INTO `manager_chapter` VALUES ('101', '1001', 'mysq介绍', 1, '2023-03-12 11:41:36', '2023-03-12 11:41:39');
INSERT INTO `manager_chapter` VALUES ('102', '1001', 'mysql查询', 2, '2023-03-12 11:49:50', '2023-03-12 11:49:52');

-- ----------------------------
-- Table structure for manager_chapter_timetable
-- ----------------------------
DROP TABLE IF EXISTS `manager_chapter_timetable`;
CREATE TABLE `manager_chapter_timetable`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '关系id',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节id',
  `sub_chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节小节id',
  `timetable_id` int(19) NOT NULL COMMENT '时间表小节id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_chapter_timetable
-- ----------------------------

-- ----------------------------
-- Table structure for manager_class
-- ----------------------------
DROP TABLE IF EXISTS `manager_class`;
CREATE TABLE `manager_class`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_class
-- ----------------------------
INSERT INTO `manager_class` VALUES ('1', '一班', '1', '1', '2023-03-10 16:08:51', '2023-03-10 16:08:51');
INSERT INTO `manager_class` VALUES ('1111', '计科1班', '计信院', '计算机科学', '2023-03-14 09:53:31', '2023-03-14 09:53:31');
INSERT INTO `manager_class` VALUES ('2', '二班 ', '2', '2', '2023-03-10 16:09:00', '2023-03-10 16:09:00');
INSERT INTO `manager_class` VALUES ('3', '软工20_1', '计算机与信息学院', '软件工程', '2023-03-22 15:06:18', '2023-03-22 15:06:18');

-- ----------------------------
-- Table structure for manager_class_course
-- ----------------------------
DROP TABLE IF EXISTS `manager_class_course`;
CREATE TABLE `manager_class_course`  (
  `id` int(19) NOT NULL AUTO_INCREMENT,
  `class_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_class_course
-- ----------------------------
INSERT INTO `manager_class_course` VALUES (1, '2', '1001');
INSERT INTO `manager_class_course` VALUES (18, '3', '101');
INSERT INTO `manager_class_course` VALUES (19, '1', '123');
INSERT INTO `manager_class_course` VALUES (20, '3', '1639479136591618050');
INSERT INTO `manager_class_course` VALUES (21, '3', '1022');

-- ----------------------------
-- Table structure for manager_course
-- ----------------------------
DROP TABLE IF EXISTS `manager_course`;
CREATE TABLE `manager_course`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `credit_hour` int(11) NOT NULL,
  `credit_num` int(11) NOT NULL,
  `type` bit(1) NOT NULL,
  `year` int(4) NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `term` int(11) NOT NULL,
  `begin_week` int(11) NOT NULL,
  `end_week` int(11) NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL,
  `cover_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_course
-- ----------------------------
INSERT INTO `manager_course` VALUES ('1001', '数据库设计', 98, 3, b'0', 2022, '计信院', 2, 8, 18, '2023-03-12 11:40:56', '2023-03-12 11:40:59', NULL);
INSERT INTO `manager_course` VALUES ('101', '数据结构', 48, 3, b'1', 2022, '计算机与信息学院', 1, 1, 12, '2023-03-24 15:58:11', '2023-03-24 15:58:11', NULL);
INSERT INTO `manager_course` VALUES ('1022', '数据结构', 48, 3, b'1', 2022, '计算机与信息学院', 1, 1, 12, '2023-03-25 13:31:03', '2023-03-25 13:31:03', NULL);
INSERT INTO `manager_course` VALUES ('123', '数据结构', 48, 3, b'1', 2022, '计算机与信息学院', 1, 1, 12, '2023-03-24 19:34:00', '2023-03-24 19:34:00', NULL);
INSERT INTO `manager_course` VALUES ('1639479136591618050', '软件开发环境', 90, 3, b'1', 2022, '计信院', 2, 1, 12, '2023-03-25 12:07:41', '2023-03-25 12:07:41', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017123856.jpg');

-- ----------------------------
-- Table structure for manager_course_description
-- ----------------------------
DROP TABLE IF EXISTS `manager_course_description`;
CREATE TABLE `manager_course_description`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_course_description
-- ----------------------------

-- ----------------------------
-- Table structure for manager_student
-- ----------------------------
DROP TABLE IF EXISTS `manager_student`;
CREATE TABLE `manager_student`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `class_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_student
-- ----------------------------

-- ----------------------------
-- Table structure for manager_sub_chapter
-- ----------------------------
DROP TABLE IF EXISTS `manager_sub_chapter`;
CREATE TABLE `manager_sub_chapter`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节小节id',
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `chapter_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小节标题',
  `number` int(10) NOT NULL COMMENT '小节序号',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_sub_chapter
-- ----------------------------
INSERT INTO `manager_sub_chapter` VALUES ('11', '1001', '101', 'mysql的起源', 1, '2023-03-12 11:46:43', '2023-03-12 11:46:46');

-- ----------------------------
-- Table structure for manager_teacher
-- ----------------------------
DROP TABLE IF EXISTS `manager_teacher`;
CREATE TABLE `manager_teacher`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_teacher
-- ----------------------------
INSERT INTO `manager_teacher` VALUES ('1', '林凡', 1, '计算机与信息学院', '2023-03-22 15:00:10', '2023-03-25 07:39:53', '1637078895506567170');

-- ----------------------------
-- Table structure for manager_teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `manager_teacher_course`;
CREATE TABLE `manager_teacher_course`  (
  `id` int(19) NOT NULL AUTO_INCREMENT,
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_teacher_course
-- ----------------------------
INSERT INTO `manager_teacher_course` VALUES (1, '1', '1001');
INSERT INTO `manager_teacher_course` VALUES (16, '1', '101');
INSERT INTO `manager_teacher_course` VALUES (18, '1', '1639479136591618050');
INSERT INTO `manager_teacher_course` VALUES (19, '1', '1022');

-- ----------------------------
-- Table structure for manager_timetable
-- ----------------------------
DROP TABLE IF EXISTS `manager_timetable`;
CREATE TABLE `manager_timetable`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date` date NULL DEFAULT NULL,
  `week` int(10) UNSIGNED NOT NULL,
  `day_of_week` int(10) UNSIGNED NOT NULL,
  `begin_index` int(10) UNSIGNED NOT NULL,
  `end_index` int(10) UNSIGNED NOT NULL,
  `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `status` tinyint(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of manager_timetable
-- ----------------------------
INSERT INTO `manager_timetable` VALUES ('1639497438248652802', '1001', '2023-03-21', 6, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:24', '2023-03-26 21:11:00', 2);
INSERT INTO `manager_timetable` VALUES ('1639497438844243969', '1001', '2023-03-28', 7, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:24', '2023-03-25 13:20:24', 0);
INSERT INTO `manager_timetable` VALUES ('1639497439112679426', '1001', '2023-04-04', 8, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497439309811714', '1001', '2023-04-11', 9, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497439574052865', '1001', '2023-04-18', 10, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497439968317441', '1001', '2023-04-25', 11, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497440299667458', '1001', '2023-05-02', 12, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497440563908610', '1001', '2023-05-09', 13, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497440823955457', '1001', '2023-05-16', 14, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497441088196609', '1001', '2023-05-23', 15, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497441352437762', '1001', '2023-05-30', 16, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497441616678914', '1001', '2023-06-06', 17, 2, 3, 4, '博学楼B208', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497442082246658', '1001', '2023-03-23', 6, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:25', '2023-03-26 21:11:00', 2);
INSERT INTO `manager_timetable` VALUES ('1639497442409402369', '1001', '2023-03-30', 7, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497442740752385', '1001', '2023-04-06', 8, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497443000799233', '1001', '2023-04-13', 9, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:25', '2023-03-25 13:20:25', 0);
INSERT INTO `manager_timetable` VALUES ('1639497443395063810', '1001', '2023-04-20', 10, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:26', '2023-03-25 13:20:26', 0);
INSERT INTO `manager_timetable` VALUES ('1639497443793522689', '1001', '2023-04-27', 11, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:26', '2023-03-25 13:20:26', 0);
INSERT INTO `manager_timetable` VALUES ('1639497444187787266', '1001', '2023-05-04', 12, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:26', '2023-03-25 13:20:26', 0);
INSERT INTO `manager_timetable` VALUES ('1639497444514942977', '1001', '2023-05-11', 13, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:26', '2023-03-25 13:20:26', 0);
INSERT INTO `manager_timetable` VALUES ('1639497444913401857', '1001', '2023-05-18', 14, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:26', '2023-03-25 13:20:26', 0);
INSERT INTO `manager_timetable` VALUES ('1639497445173448705', '1001', '2023-05-25', 15, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:26', '2023-03-25 13:20:26', 0);
INSERT INTO `manager_timetable` VALUES ('1639497445504798722', '1001', '2023-06-01', 16, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:26', '2023-03-25 13:20:26', 0);
INSERT INTO `manager_timetable` VALUES ('1639497445769039873', '1001', '2023-06-08', 17, 4, 3, 4, '致用楼217', '1', '2023-03-25 13:20:26', '2023-03-25 13:20:26', 0);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `title` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1639534014600871938', '1001', '1', '测试标题', '测试内容', '林凡', '2023-03-25 15:45:45');
INSERT INTO `message` VALUES ('1639534457641054210', '1001', '1', '测试标题', '测试内容', '林凡', '2023-03-25 15:47:30');
INSERT INTO `message` VALUES ('1639540037818560513', '101', '2', '通知2', '内容内容', '张三', '2023-03-25 16:09:41');
INSERT INTO `message` VALUES ('1639540848430723074', '101', '2', '通知2', '内容内容', '张三', '2023-03-25 16:12:54');
INSERT INTO `message` VALUES ('1639541196469874689', '1001', '2', '通知3', '内容内容', '张三', '2023-03-25 16:14:17');

-- ----------------------------
-- Table structure for message_user
-- ----------------------------
DROP TABLE IF EXISTS `message_user`;
CREATE TABLE `message_user`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `msg_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `has_read` bit(1) NOT NULL COMMENT '已读',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message_user
-- ----------------------------
INSERT INTO `message_user` VALUES ('1639534457884323842', '1637036809054887937', '1639534457641054210', b'0');
INSERT INTO `message_user` VALUES ('1639541196734115842', '1637036809054887937', '1639541196469874689', b'0');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'test', 'test');
INSERT INTO `role` VALUES ('2', 'student', 'student');
INSERT INTO `role` VALUES ('3', 'teacher', 'teacher');
INSERT INTO `role` VALUES ('4', 'admin', 'admin');

-- ----------------------------
-- Table structure for role_auth
-- ----------------------------
DROP TABLE IF EXISTS `role_auth`;
CREATE TABLE `role_auth`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `auth_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_auth
-- ----------------------------
INSERT INTO `role_auth` VALUES ('1', '1', '1');
INSERT INTO `role_auth` VALUES ('2', '2', '2');
INSERT INTO `role_auth` VALUES ('3', '3', '3');
INSERT INTO `role_auth` VALUES ('4', '4', '4');

-- ----------------------------
-- Table structure for term_first_week
-- ----------------------------
DROP TABLE IF EXISTS `term_first_week`;
CREATE TABLE `term_first_week`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  `term` int(11) NOT NULL,
  `first_day` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of term_first_week
-- ----------------------------
INSERT INTO `term_first_week` VALUES (1, 2022, 2, '2023-02-13');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'student,teacher,admin',
  `avatar` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'test', 'test', 'test', 'tt', 'tt');
INSERT INTO `user` VALUES ('1621728796152811522', 'yunpan', '$2a$10$xcLj5hK8YNE.EVRJSGSCsegbQJ6yIDTnMvDpsPyVpZxN6RIBSbw6W', 'admin', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'yunpan');
INSERT INTO `user` VALUES ('1636754127444037633', '2001010111', '827ccb0eea8a706c4c34a16891f84e7b', 'student', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', '小李');
INSERT INTO `user` VALUES ('1637036809054887937', 'yanshuangying', '$2a$10$Qs/FMk3Y446OmESVoa2g2OYNmG1XB4Exvro6CbqH16sZTncFq3r/6', 'teacher', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', '燕双鹰');
INSERT INTO `user` VALUES ('1637078812736172034', 'sy233', '$2a$10$A6DBYbnJ/S0xbsoeVxW0yO7zJBHlbDN2r9iyTDzLKRy6iT.szy59C', 'student', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', '双鹰');
INSERT INTO `user` VALUES ('1637078895506567170', 'by233', '$2a$10$HOqgVJYf4WSKtywIn2vMsuJbczCrchpUZ5jKVy1LvZvX3/pGMzVAS', 'teacher', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', '步鹰');
INSERT INTO `user` VALUES ('1637363136999997442', 'syy233', '$2a$10$yMu5IZ9w.sbtsGw6Bcjnuub9ZKn8jyCgpb8RZ6nbY6x7GxaDglvrq', 'admin', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'syy');
INSERT INTO `user` VALUES ('1639606939212058625', '2006050139', '$2a$10$81x1lEspv2hFGF6kT5zRzeQhnvpr0yu/b/oohwW0UTIdu2dzQKFY.', 'admin', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'fishmoun');
INSERT INTO `user` VALUES ('1639607437877055489', '2006050130', '$2a$10$zYrcIQFVJxjuGTbwlNMSQeLVXkY.p4.rqW.xn7QkiNIueVNN62SYO', 'teacher', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'fishmoun');
INSERT INTO `user` VALUES ('1639608791583719425', 'laoli', '$2a$10$feReDxgWp1gKZoNrlY1kqu3BMlC5xoel8oeSN5Dgd0JnkFGe78fHC', 'admin', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'laoli');
INSERT INTO `user` VALUES ('1639613159821357057', '老王', '$2a$10$zWC5JFVdiTAOBJv9Y6Q66OFXAasBYe3HUXsFst4efB8dA.UPYte7e', 'admin', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'laowang');
INSERT INTO `user` VALUES ('1639621375419224066', 'tea', '$2a$10$/nRZIxK7zPTWbuV8j7HZQudJIWIH/637V9QTUR7eqyfhuvBylsRg6', '学生', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'fish');
INSERT INTO `user` VALUES ('1639621655611314178', 'teacher', '$2a$10$uU7Y7Q6uxQtclzN.oXSnje1rfMtF72BfVxPMZsxgWViKu4IKqeLRK', 'teacher', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'dish');
INSERT INTO `user` VALUES ('1639648119090057218', 'testhyj666', '$2a$10$ioZhvfdJySaLGf4pRvzayu0BnKlwV2gc6WaO3LCkjMUBfzApjQzi.', '学生', 'https://cloud-file-230201-1.oss-cn-hangzhou.aliyuncs.com/avatar/2023/02/01/QQ%E5%9B%BE%E7%89%8720221017125912.jpg', 'hyj666');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1636754127444037633', '1');
INSERT INTO `user_role` VALUES ('1637363137146798081', '1637363136999997442', '4');
INSERT INTO `user_role` VALUES ('1639601542816337922', '1639601542770200577', '1');
INSERT INTO `user_role` VALUES ('1639605719621701633', '1639605719596535810', '4');
INSERT INTO `user_role` VALUES ('1639606307646345218', '1639606307612790785', '2');
INSERT INTO `user_role` VALUES ('1639606939245613058', '1639606939212058625', '4');
INSERT INTO `user_role` VALUES ('1639607437914804226', '1639607437877055489', '3');
INSERT INTO `user_role` VALUES ('1639608791764074497', '1639608791583719425', '4');
INSERT INTO `user_role` VALUES ('1639609671570427905', '1639609671536873474', '1');
INSERT INTO `user_role` VALUES ('1639613159959769089', '1639613159821357057', '4');
INSERT INTO `user_role` VALUES ('1639621375452778498', '1639621375419224066', '1');
INSERT INTO `user_role` VALUES ('1639621655649062913', '1639621655611314178', '3');
INSERT INTO `user_role` VALUES ('1639648119123611650', '1639648119090057218', '1');
INSERT INTO `user_role` VALUES ('2', '1637036809054887937', '1');

SET FOREIGN_KEY_CHECKS = 1;
