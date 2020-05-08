package com.wfmyzyz.user.user.vo.authority;


import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author admin
 */
@Data
@ToString
public class SearchAuthorityVo {

    @NotNull
    private Integer page;
    @NotNull
    private Integer pageSize;
    private Integer authorityId;
    private String name;
    private String url;
    @Pattern(regexp = "页面|按钮")
    private String type;
    private Integer fAuthorityId;
    @Pattern(regexp = "显示|隐藏")
    private String display;
    private String icon;
}
