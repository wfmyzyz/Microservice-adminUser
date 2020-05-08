package com.wfmyzyz.user.user.vo.user;

import lombok.Data;

/**
 * @author admin
 */
@Data
public class MenuMetaVo {
    private String title;
    private String icon;
    private Boolean noCache = false;
    private Boolean breadcrumb = true;
    private String activeMenu;
    private Boolean affix;
}
