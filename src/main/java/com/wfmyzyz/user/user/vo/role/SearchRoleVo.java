package com.wfmyzyz.user.user.vo.role;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author admin
 */
@Data
@ToString
public class SearchRoleVo {
    @NotNull
    private Integer page;
    @NotNull
    private Integer pageSize;
    private Integer roleId;
    private String name;
    private Integer fRoleId;
    private String startTime;
    private String endTime;
}
