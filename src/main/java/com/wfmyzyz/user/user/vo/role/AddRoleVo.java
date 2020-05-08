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
public class AddRoleVo {

    @NotBlank(message = "角色名不能为空")
    private String name;
    @NotNull(message = "父角色ID不能为空")
    private Integer fRoleId;

}
