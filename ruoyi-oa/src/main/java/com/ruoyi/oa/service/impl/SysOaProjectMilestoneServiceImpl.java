package com.ruoyi.oa.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.ruoyi.oa.domain.SysOaProject;
import com.ruoyi.oa.domain.SysOaProjectMilestone;
import com.ruoyi.oa.domain.bo.SysOaProjectMilestoneBo;
import com.ruoyi.oa.domain.vo.SysOaProjectMilestoneVo;
import com.ruoyi.oa.mapper.SysOaProjectMapper;
import com.ruoyi.oa.mapper.SysOaProjectMilestoneMapper;
import com.ruoyi.oa.service.ISysOaProjectMilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SysOaProjectMilestoneServiceImpl implements ISysOaProjectMilestoneService {

    private static final List<String> MILESTONE_ORDER =
            Arrays.asList("M0", "M1", "M2", "M3", "M4", "M5", "M6");

    private final SysOaProjectMilestoneMapper milestoneMapper;
    private final SysOaProjectMapper projectMapper;

    @Override
    public Map<String, List<SysOaProjectMilestoneVo>> queryGroupByProject(Long projectId) {
        List<SysOaProjectMilestoneVo> list = milestoneMapper.selectByProjectId(projectId);
        // 保持 M0-M6 顺序
        Map<String, List<SysOaProjectMilestoneVo>> result = new LinkedHashMap<>();
        for (String code : MILESTONE_ORDER) {
            result.put(code, new ArrayList<>());
        }
        for (SysOaProjectMilestoneVo vo : list) {
            result.computeIfAbsent(vo.getMilestoneCode(), k -> new ArrayList<>()).add(vo);
        }
        return result;
    }

    @Override
    public Boolean updateTask(SysOaProjectMilestoneBo bo) {
        SysOaProjectMilestone entity = BeanUtil.toBean(bo, SysOaProjectMilestone.class);
        return milestoneMapper.updateById(entity) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean advanceMilestone(Long projectId) {
        SysOaProject project = projectMapper.selectById(projectId);
        if (project == null) return false;

        String current = project.getCurrentMilestone();
        int idx = MILESTONE_ORDER.indexOf(current);
        if (idx < 0 || idx >= MILESTONE_ORDER.size() - 1) return false;

        String next = MILESTONE_ORDER.get(idx + 1);

        // 当前里程碑所有待开始/进行中任务 → 已完成
        milestoneMapper.update(null, new LambdaUpdateWrapper<SysOaProjectMilestone>()
                .eq(SysOaProjectMilestone::getProjectId, projectId)
                .eq(SysOaProjectMilestone::getMilestoneCode, current)
                .in(SysOaProjectMilestone::getStatus, "0", "1")
                .set(SysOaProjectMilestone::getStatus, "2")
                .set(SysOaProjectMilestone::getActualEnd, new Date()));

        // 下一里程碑所有任务 → 进行中
        milestoneMapper.update(null, new LambdaUpdateWrapper<SysOaProjectMilestone>()
                .eq(SysOaProjectMilestone::getProjectId, projectId)
                .eq(SysOaProjectMilestone::getMilestoneCode, next)
                .set(SysOaProjectMilestone::getStatus, "1")
                .set(SysOaProjectMilestone::getActualStart, new Date()));

        // 更新项目当前里程碑
        SysOaProject update = new SysOaProject();
        update.setProjectId(projectId);
        update.setCurrentMilestone(next);
        projectMapper.updateById(update);

        return true;
    }
}
