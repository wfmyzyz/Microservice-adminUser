package com.wfmyzyz.user.user.vo.authority;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author admin
 */
@Data
@ToString
public class AddAuthorityVo {

    @NotBlank(message = "权限名不能为空")
    private String name;

    private String url;

    @NotBlank(message = "权限类型不能为空")
    @Pattern(regexp = "页面|按钮")
    private String type;

    @NotNull(message = "父权限ID不能为空")
    private Integer fAuthorityId;

    private Integer sort;

    private String icon;

    @Pattern(regexp = "显示|隐藏")
    private String display;
}
