package com.wfmyzyz.user.user.vo.authority;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * @author admin
 */
@Data
@ToString
public class UpdateAuthorityVo {
    @NotNull(message = "权限ID不能为空")
    private Integer authorityId;

    private String name;

    private String url;

    private String type;

    private Integer sort;

    private String display;

    private String icon;
}
