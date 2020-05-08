package com.wfmyzyz.user.user.vo.user;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 */
@Data
public class MenuListVo {
    private String path;
    private String component;
    private String redirect;
    private Boolean alwaysShow = false;
    private String name;
    private MenuMetaVo meta;
    private Boolean hidden;
    private List<MenuListVo> children = new ArrayList<>();
}
