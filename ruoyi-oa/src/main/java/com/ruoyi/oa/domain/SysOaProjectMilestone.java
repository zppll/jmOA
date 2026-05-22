package com.ruoyi.oa.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sys_oa_project_milestone")
public class SysOaProjectMilestone extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @TableId(value = "milestone_id")
    private Long milestoneId;

    private Long projectId;

    /** M0-M6 */
    private String milestoneCode;

    private String milestoneName;

    /** marketing / solution / tech */
    private String deptCode;

    private String deptName;

    private String taskName;

    private String taskDesc;

    private String deliverableDef;

    /** 0待开始 1进行中 2已完成 3已跳过 */
    private String status;

    private String assignee;

    private Date planStart;

    private Date planEnd;

    private Date actualStart;

    private Date actualEnd;

    private String accessory;

    private Integer sortNo;
}
