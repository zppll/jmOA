package com.ruoyi.oa.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.oa.domain.bo.SysOaProjectMilestoneBo;
import com.ruoyi.oa.domain.vo.SysOaProjectMilestoneVo;
import com.ruoyi.oa.service.ISysOaProjectMilestoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/oa/project/milestone")
public class SysOaProjectMilestoneController extends BaseController {

    private final ISysOaProjectMilestoneService milestoneService;

    /** 查询项目里程碑（按 M0-M6 分组） */
    @SaCheckPermission("oa:project:query")
    @GetMapping("/{projectId}")
    public R<Map<String, List<SysOaProjectMilestoneVo>>> list(
            @NotNull(message = "项目ID不能为空") @PathVariable Long projectId) {
        return R.ok(milestoneService.queryGroupByProject(projectId));
    }

    /** 更新单个任务状态/附件/负责人 */
    @SaCheckPermission("oa:project:edit")
    @Log(title = "里程碑任务", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PutMapping("/task")
    public R<Void> updateTask(@Validated(EditGroup.class) @RequestBody SysOaProjectMilestoneBo bo) {
        return toAjax(milestoneService.updateTask(bo));
    }

    /** 推进项目到下一里程碑 */
    @SaCheckPermission("oa:project:edit")
    @Log(title = "里程碑推进", businessType = BusinessType.UPDATE)
    @RepeatSubmit
    @PostMapping("/advance/{projectId}")
    public R<Void> advance(@NotNull(message = "项目ID不能为空") @PathVariable Long projectId) {
        return toAjax(milestoneService.advanceMilestone(projectId));
    }
}
