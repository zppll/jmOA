-- SOP 里程碑相关 DDL
-- 1. 给项目表加里程碑字段
ALTER TABLE `sys_oa_project`
    ADD COLUMN `current_milestone` varchar(4) NOT NULL DEFAULT 'M0' COMMENT '当前里程碑(M0-M6)' AFTER `project_status`,
    ADD COLUMN `dept_marketing`    varchar(64) DEFAULT NULL COMMENT '营销中心负责人' AFTER `current_milestone`,
    ADD COLUMN `dept_solution`     varchar(64) DEFAULT NULL COMMENT '解决方案中心负责人' AFTER `dept_marketing`,
    ADD COLUMN `dept_tech`         varchar(64) DEFAULT NULL COMMENT '技术中心负责人' AFTER `dept_solution`;

-- 2. 项目里程碑任务跟踪表
DROP TABLE IF EXISTS `sys_oa_project_milestone`;
CREATE TABLE `sys_oa_project_milestone` (
    `milestone_id`   bigint       NOT NULL COMMENT '主键',
    `project_id`     bigint       NOT NULL COMMENT '项目ID',
    `milestone_code` varchar(4)   NOT NULL COMMENT '里程碑编码(M0-M6)',
    `milestone_name` varchar(64)  NOT NULL COMMENT '里程碑名称',
    `dept_code`      varchar(16)  NOT NULL COMMENT '部门编码(marketing/solution/tech)',
    `dept_name`      varchar(32)  NOT NULL COMMENT '部门名称',
    `task_name`      varchar(64)  NOT NULL COMMENT '任务名称',
    `task_desc`      varchar(256) DEFAULT NULL COMMENT '任务说明',
    `deliverable_def` varchar(256) DEFAULT NULL COMMENT '产出物定义',
    `status`         char(1)      NOT NULL DEFAULT '0' COMMENT '状态(0待开始 1进行中 2已完成 3已跳过)',
    `assignee`       varchar(64)  DEFAULT NULL COMMENT '负责人',
    `plan_start`     datetime     DEFAULT NULL COMMENT '计划开始时间',
    `plan_end`       datetime     DEFAULT NULL COMMENT '计划结束时间',
    `actual_start`   datetime     DEFAULT NULL COMMENT '实际开始时间',
    `actual_end`     datetime     DEFAULT NULL COMMENT '实际结束时间',
    `accessory`      text         DEFAULT NULL COMMENT '产出物附件(JSON)',
    `remark`         varchar(256) DEFAULT NULL COMMENT '备注',
    `sort_no`        int          NOT NULL DEFAULT 0 COMMENT '排序号',
    `create_by`      varchar(32)  NOT NULL DEFAULT '' COMMENT '创建者',
    `create_time`    datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(32)  NOT NULL DEFAULT '' COMMENT '更新者',
    `update_time`    datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`milestone_id`),
    KEY `idx_project_id` (`project_id`),
    KEY `idx_milestone_code` (`milestone_code`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='项目SOP里程碑任务表';
