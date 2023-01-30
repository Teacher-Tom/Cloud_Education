#
# Structure for table "node"
#

CREATE TABLE `node` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `task_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '课时任务ID',
  `node_name` varchar(255) NOT NULL COMMENT '节点名称',
  `node_desc` varchar(255) DEFAULT NULL COMMENT '节点描述',
  `is_over` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否已完成(1是，0否)',
  `deadlinde` datetime NOT NULL COMMENT '截止时间',
  `user_type` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '用户类型(1:学生，2:教师，3:管理员)',
  `parent_no` bigint(20) NOT NULL DEFAULT '0' COMMENT '前一个节点的编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='节点信息';

#
# Structure for table "node_branch"
#

CREATE TABLE `node_branch` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `node_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '节点ID',
  `branch_name` varchar(255) NOT NULL COMMENT '分支名称',
  `branch_desc` varchar(255) DEFAULT NULL COMMENT '分支描述',
  `is_checked` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否被选(1是，0否)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='节点分支信息';

#
# Structure for table "node_demand"
#

CREATE TABLE `node_demand` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `node_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '节点ID',
  `demand_desc` varchar(255) DEFAULT NULL COMMENT '要求描述',
  `is_doc` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否存在文档(1存在，0否)',
  `doc_name` varchar(255) DEFAULT NULL COMMENT '文档名称',
  `doc_url` varchar(255) DEFAULT NULL COMMENT '文档地址',
  `is_pic` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否存在图片(1存在，0否)',
  `pic_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `is_video` varchar(255) NOT NULL DEFAULT '0' COMMENT '是否存在视频(1存在，0否)',
  `video_no` bigint(20) DEFAULT NULL COMMENT '视频编号',
  `video_name` varchar(255) DEFAULT NULL COMMENT '视频名称',
  `video_vid` varchar(50) DEFAULT NULL COMMENT '视频VID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='节点任务要求信息';

#
# Structure for table "node_feedback"
#

CREATE TABLE `node_feedback` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `node_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '节点ID',
  `difficulty` int(11) NOT NULL DEFAULT '0' COMMENT '难度',
  `participation` int(11) NOT NULL DEFAULT '0' COMMENT '参与人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='节点反馈信息';

#
# Structure for table "node_message"
#

CREATE TABLE `node_message` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `status_id` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '状态(1:正常，0:禁用)',
  `node_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '节点ID',
  `content` longtext NOT NULL DEFAULT '' COMMENT '留言内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='节点留言提问信息';
