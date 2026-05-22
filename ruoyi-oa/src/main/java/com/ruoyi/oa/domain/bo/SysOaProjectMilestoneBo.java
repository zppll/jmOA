package com.ruoyi.oa.domain.bo;

import com.ruoyi.common.core.validate.EditGroup;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class SysOaProjectMilestoneBo {

    @NotNull(message = "里程碑ID不能为空", groups = {EditGroup.class})
    private Long milestoneId;

    /** 0待开始 1进行中 2已完成 3已跳过 */
    private String status;

    private String assignee;

    private Date planStart;

    private Date planEnd;

    private Date actualStart;

    private Date actualEnd;

    private String accessory;

    private String remark;
}
