package com.wfmyzyz.user.user.vo.role;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 */
@Data
@ToString
public class UpdateRoleVo {

    @NotNull(message = "角色ID不能为空")
    private Integer roleId;

    @NotBlank(message = "角色名不能为空")
    private String name;

}
