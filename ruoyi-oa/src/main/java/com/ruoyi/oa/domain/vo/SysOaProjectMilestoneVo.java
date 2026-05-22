package com.ruoyi.oa.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class SysOaProjectMilestoneVo {

    private Long milestoneId;
    private Long projectId;
    private String milestoneCode;
    private String milestoneName;
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
    private Date updateTime;
}
