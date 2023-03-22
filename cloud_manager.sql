/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : localhost:3307
 Source Schema         : cloud_manager

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 17/03/2023 22:31:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course_homework
-- ----------------------------
DROP TABLE IF EXISTS `course_homework`;
CREATE TABLE `course_homework`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `is_outdated` tinyint(1) NOT NULL,
  `is_marked` tinyint(1) NOT NULL,
  `score` int(3) NULL DEFAULT NULL,
  `review` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `timetable_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_global` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_homework
-- ----------------------------

-- ----------------------------
-- Table structure for course_homework_context
-- ----------------------------
DROP TABLE IF EXISTS `course_homework_context`;
CREATE TABLE `course_homework_context`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `homework_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `submit_answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ref_answer` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `picture_url` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_homework_context
-- ----------------------------

-- ----------------------------
-- Table structure for course_resource
-- ----------------------------
DROP TABLE IF EXISTS `course_resource`;
CREATE TABLE `course_resource`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `file_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '云盘文件id',
  `timetable_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_global` tinyint(1) NOT NULL COMMENT '是否为全局资源（即小节资源和全局资源）',
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_resource
-- ----------------------------

-- ----------------------------
-- Table structure for manager_chapter
-- ----------------------------
DROP TABLE IF EXISTS `manager_chapter`;
CREATE TABLE `manager_chapter`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节id',
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节名',
  `number` int(10) NOT NULL COMMENT '章节号',
  `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

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
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `chapter_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节id',
  `sub_chapter_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节小节id',
  `timetable_id` int(10) NOT NULL COMMENT '时间表小节id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_chapter_timetable
-- ----------------------------

-- ----------------------------
-- Table structure for manager_class
-- ----------------------------
DROP TABLE IF EXISTS `manager_class`;
CREATE TABLE `manager_class`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_class
-- ----------------------------
INSERT INTO `manager_class` VALUES ('1', '一班', '1', '1', '2023-03-10 16:08:51', '2023-03-10 16:08:51');
INSERT INTO `manager_class` VALUES ('1111', '计科1班', '计信院', '计算机科学', '2023-03-14 09:53:31', '2023-03-14 09:53:31');
INSERT INTO `manager_class` VALUES ('2', '二班 ', '2', '2', '2023-03-10 16:09:00', '2023-03-10 16:09:00');

-- ----------------------------
-- Table structure for manager_class_course
-- ----------------------------
DROP TABLE IF EXISTS `manager_class_course`;
CREATE TABLE `manager_class_course`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `class_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_class_course
-- ----------------------------

-- ----------------------------
-- Table structure for manager_course
-- ----------------------------
DROP TABLE IF EXISTS `manager_course`;
CREATE TABLE `manager_course`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_course
-- ----------------------------
INSERT INTO `manager_course` VALUES ('1001', '数据库设计', 98, 3, b'0', 2022, '计信院', 1, 8, 18, '2023-03-12 11:40:56', '2023-03-12 11:40:59', NULL);

-- ----------------------------
-- Table structure for manager_course_description
-- ----------------------------
DROP TABLE IF EXISTS `manager_course_description`;
CREATE TABLE `manager_course_description`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_course_description
-- ----------------------------

-- ----------------------------
-- Table structure for manager_student
-- ----------------------------
DROP TABLE IF EXISTS `manager_student`;
CREATE TABLE `manager_student`  (
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `major` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `id` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `class_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_student
-- ----------------------------
INSERT INTO `manager_student` VALUES ('01', 1, '1', '1', '1', '2023-03-10 13:03:20', '2023-03-10 14:30:25', '1');
INSERT INTO `manager_student` VALUES ('02', 0, '1', '1', '2', '2023-03-10 13:03:18', '2023-03-10 14:36:14', '2');

-- ----------------------------
-- Table structure for manager_sub_chapter
-- ----------------------------
DROP TABLE IF EXISTS `manager_sub_chapter`;
CREATE TABLE `manager_sub_chapter`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节小节id',
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `chapter_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '章节id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小节标题',
  `number` int(10) NOT NULL COMMENT '小节序号',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_sub_chapter
-- ----------------------------
INSERT INTO `manager_sub_chapter` VALUES ('11', '1001', '101', 'mysql的起源', 1, '2023-03-12 11:46:43', '2023-03-12 11:46:46');

-- ----------------------------
-- Table structure for manager_teacher
-- ----------------------------
DROP TABLE IF EXISTS `manager_teacher`;
CREATE TABLE `manager_teacher`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for manager_teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `manager_teacher_course`;
CREATE TABLE `manager_teacher_course`  (
  `id` int(10) NOT NULL,
  `teacher_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_teacher_course
-- ----------------------------

-- ----------------------------
-- Table structure for manager_timetable
-- ----------------------------
DROP TABLE IF EXISTS `manager_timetable`;
CREATE TABLE `manager_timetable`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `course_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `date` date NOT NULL,
  `week` int(10) UNSIGNED NOT NULL,
  `day_of_week` int(10) UNSIGNED NOT NULL,
  `begin_index` int(10) UNSIGNED NOT NULL,
  `end_index` int(10) UNSIGNED NOT NULL,
  `location` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `teacher_id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gmt_create` datetime NOT NULL,
  `gmt_modified` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager_timetable
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `role` tinyint(1) NOT NULL COMMENT '0学生，1老师，2管理员',
  `avatar` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
