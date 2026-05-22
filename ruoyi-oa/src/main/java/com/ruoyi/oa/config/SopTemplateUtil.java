package com.ruoyi.oa.config;

import com.ruoyi.oa.domain.SysOaProjectMilestone;

import java.util.ArrayList;
import java.util.List;

/**
 * SOP 里程碑任务模板
 * 按 Visio 流程图定义 M0-M6 各部门默认任务
 */
public class SopTemplateUtil {

    public static List<SysOaProjectMilestone> buildTemplate(Long projectId) {
        List<SysOaProjectMilestone> list = new ArrayList<>();
        int sort = 1;

        // M0 售前
        list.add(task(projectId, "M0", "售前", "marketing", "营销中心",
                "售前立项", "需求对接、报价、投标、合同、售前立项",
                "需求/项目策划/招投标", sort++));
        list.add(task(projectId, "M0", "售前", "solution", "解决方案中心",
                "技术支持（技术方案）", "提供汇报系统/视频动画/总体方案支持",
                "汇报系统/视频动画/总体方案", sort++));
        list.add(task(projectId, "M0", "售前", "tech", "技术中心",
                "技术方案评审", "对技术方案进行评审",
                "技术评审意见", sort++));
        list.add(task(projectId, "M0", "售前", "marketing", "营销中心",
                "项目立项（售前转制作立项）", "立项书、项目策划、报价、投标",
                "立项书/项目策划书", sort++));

        // M1 项目启动
        list.add(task(projectId, "M1", "项目启动", "marketing", "营销中心",
                "协助交底", "客户背景移交",
                "需求/合同文本/报单价", sort++));
        list.add(task(projectId, "M1", "项目启动", "solution", "解决方案中心",
                "项目启动", "项目启动会/组建项目团队",
                "项目方案/项目计划表/预算/技术方案", sort++));
        list.add(task(projectId, "M1", "项目启动", "solution", "解决方案中心",
                "需求调研", "确定调研人员/调研周期",
                "调研大纲/调研报告", sort++));
        list.add(task(projectId, "M1", "项目启动", "tech", "技术中心",
                "产品设计", "输出产品原型设计",
                "产品原型", sort++));

        // M2 设计评审通过
        list.add(task(projectId, "M2", "设计评审通过", "marketing", "营销中心",
                "客户沟通", "需求变更协调",
                "变更确认记录", sort++));
        list.add(task(projectId, "M2", "设计评审通过", "solution", "解决方案中心",
                "过程监控", "风险预警、变更管理",
                "进度汇报方案/评审记录/变更单/用例", sort++));
        list.add(task(projectId, "M2", "设计评审通过", "tech", "技术中心",
                "设计评审", "原型/UI/PRD评审",
                "原型/UI/PRD文档/评审记录", sort++));

        // M3 过程监控
        list.add(task(projectId, "M3", "过程监控", "solution", "解决方案中心",
                "过程监控", "风险预警、变更管理持续跟踪",
                "进度汇报/变更单", sort++));
        list.add(task(projectId, "M3", "过程监控", "tech", "技术中心",
                "开发完成/自测", "完成开发并进行自测",
                "源代码/模型/程序", sort++));

        // M4 开发完成
        list.add(task(projectId, "M4", "开发完成", "solution", "解决方案中心",
                "内部审查", "质量监督、复验闭环",
                "用例/测试报告/bug清单", sort++));
        list.add(task(projectId, "M4", "开发完成", "tech", "技术中心",
                "BUG修复", "问题跟踪关闭",
                "修复记录", sort++));
        list.add(task(projectId, "M4", "开发完成", "solution", "解决方案中心",
                "部署/试运行", "文档归档/验收资料收集",
                "部署文档/验收资料", sort++));

        // M5 交付验收
        list.add(task(projectId, "M5", "交付验收", "solution", "解决方案中心",
                "交付验收", "文档归档",
                "验收报告/文档归档", sort++));

        // M6 回款与复盘
        list.add(task(projectId, "M6", "回款与复盘", "marketing", "营销中心",
                "回款跟进", "凭证确认",
                "回款凭证", sort++));
        list.add(task(projectId, "M6", "回款与复盘", "solution", "解决方案中心",
                "项目复盘", "知识沉淀",
                "复盘报告", sort++));
        list.add(task(projectId, "M6", "回款与复盘", "solution", "解决方案中心",
                "维保", "项目维保服务",
                "维保记录", sort++));
        list.add(task(projectId, "M6", "回款与复盘", "tech", "技术中心",
                "产品化需求", "技术沉淀",
                "技术文档/产品化需求", sort++));

        return list;
    }

    private static SysOaProjectMilestone task(Long projectId,
                                               String code, String name,
                                               String deptCode, String deptName,
                                               String taskName, String taskDesc,
                                               String deliverableDef, int sort) {
        SysOaProjectMilestone m = new SysOaProjectMilestone();
        m.setProjectId(projectId);
        m.setMilestoneCode(code);
        m.setMilestoneName(name);
        m.setDeptCode(deptCode);
        m.setDeptName(deptName);
        m.setTaskName(taskName);
        m.setTaskDesc(taskDesc);
        m.setDeliverableDef(deliverableDef);
        m.setStatus("0");
        m.setSortNo(sort);
        return m;
    }
}
