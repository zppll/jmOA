package com.ruoyi.oa.service;

import com.ruoyi.oa.domain.bo.SysOaProjectMilestoneBo;
import com.ruoyi.oa.domain.vo.SysOaProjectMilestoneVo;

import java.util.List;
import java.util.Map;

public interface ISysOaProjectMilestoneService {

    /** 按项目查询所有里程碑任务，按 milestoneCode 分组 */
    Map<String, List<SysOaProjectMilestoneVo>> queryGroupByProject(Long projectId);

    /** 更新单个任务（状态/负责人/时间/附件） */
    Boolean updateTask(SysOaProjectMilestoneBo bo);

    /** 推进项目到下一里程碑（将当前里程碑所有任务置为完成，激活下一里程碑） */
    Boolean advanceMilestone(Long projectId);
}
