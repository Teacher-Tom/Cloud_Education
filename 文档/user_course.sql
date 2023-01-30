#
# Structure for table "user"
#

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `user_no` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '用户编号',
  `mobile` varchar(11) NOT NULL COMMENT '手机号码',
  `mobile_salt` varchar(36) NOT NULL COMMENT '密码盐',
  `mobile_psw` varchar(255) NOT NULL COMMENT '登录密码',
  `user_type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '用户类型(1:学生，2:教师，3:管理员)',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mobile`),
  UNIQUE KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户基本信息';

#
# Structure for table "user_student"
#

CREATE TABLE `user_student` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `user_no` bigint(20) NOT NULL COMMENT '用户编号为学生学号',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '用户手机',
  `student_name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` tinyint(3) unsigned DEFAULT '3' COMMENT '性别(1男，2女，3保密)',
  `department_no` bigint(20) NOT NULL DEFAULT '1' COMMENT '院系编号',
  `major_no` bigint(20) NOT NULL DEFAULT '1' COMMENT '专业编号',
  `class_no` bigint(20) NOT NULL DEFAULT '1' COMMENT '班级编号',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mobile`),
  UNIQUE KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='学生用户信息';

#
# Structure for table "user_teacher"
#

CREATE TABLE `user_teacher` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `user_no` bigint(20) NOT NULL COMMENT '用户编号为教师工号',
  `mobile` varchar(11) NOT NULL DEFAULT '' COMMENT '用户手机',
  `teacher_name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` tinyint(3) unsigned DEFAULT '3' COMMENT '性别(1男，2女，3保密)',
  `department_no` bigint(20) NOT NULL DEFAULT '1' COMMENT '院系编号',
  `head_img_url` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mobile`),
  UNIQUE KEY (`user_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='教师用户信息';

#
# Structure for table "class"
#

CREATE TABLE `class` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `class_no` bigint(20) NOT NULL DEFAULT '1' COMMENT '班级编号',
  `class_name` varchar(255) NOT NULL DEFAULT '' COMMENT '班级名称',
  `student_num` int(11) NOT NULL DEFAULT '0' COMMENT '班级人数',
  PRIMARY KEY (`id`),
  UNIQUE KEY (`class_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='班级信息';

#
# Structure for table "course"
#

CREATE TABLE `course` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `course_no` bigint(20) NOT NULL DEFAULT '1' COMMENT '课程编号',
  `course_name` varchar(255) NOT NULL DEFAULT '' COMMENT '课程名称',
  `course_logo` varchar(255) NOT NULL DEFAULT '' COMMENT '课程封面',
  `teacher_no` bigint(20) NOT NULL DEFAULT '1' COMMENT '教师用户编号',
  `introduce` longtext DEFAULT NULL COMMENT '课程简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课程信息';

#
# Structure for table "course_chapter"
#

CREATE TABLE `course_chapter` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `course_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '课程ID',
  `chapter_name` varchar(255) NOT NULL COMMENT '章节名称',
  `chapter_desc` varchar(255) DEFAULT NULL COMMENT '章节描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='章节信息';

#
# Structure for table "course_chapter_task"
#

CREATE TABLE `course_chapter_task` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `course_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '课程ID',
  `chapter_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '章节ID',
  `task_name` varchar(255) NOT NULL COMMENT '课时名称',
  `task_desc` varchar(255) DEFAULT NULL COMMENT '课时描述',
  `is_over` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否已完成(1是，0否)',
  `task_url` varchar(255) DEFAULT NULL COMMENT '具体任务页面地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='课时任务信息';

#
# Structure for table "student_course"
#

CREATE TABLE `student_course` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `student_id` bigint(20) unsigned NOT NULL COMMENT '学生ID',
  `course_id` bigint(20) unsigned NOT NULL COMMENT '课程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1145889062897147907 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='学生课程关联表';

#
# Structure for table "class_course"
#

CREATE TABLE `class_course` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `class_id` bigint(20) unsigned NOT NULL COMMENT '班级ID',
  `course_id` bigint(20) unsigned NOT NULL COMMENT '课程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1145889062897147907 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='班级课程关联表';
