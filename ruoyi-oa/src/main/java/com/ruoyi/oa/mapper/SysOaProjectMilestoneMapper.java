package com.ruoyi.oa.mapper;

import com.ruoyi.common.core.mapper.BaseMapperPlus;
import com.ruoyi.oa.domain.SysOaProjectMilestone;
import com.ruoyi.oa.domain.vo.SysOaProjectMilestoneVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOaProjectMilestoneMapper
        extends BaseMapperPlus<SysOaProjectMilestoneMapper, SysOaProjectMilestone, SysOaProjectMilestoneVo> {

    List<SysOaProjectMilestoneVo> selectByProjectId(@Param("projectId") Long projectId);

    int deleteByProjectId(@Param("projectId") Long projectId);
}
