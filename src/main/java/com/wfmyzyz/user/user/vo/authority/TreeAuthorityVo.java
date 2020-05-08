package com.wfmyzyz.user.user.vo.authority;

import com.wfmyzyz.user.user.domain.Authority;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author admin
 */
@Data
public class TreeAuthorityVo {
    private Integer authorityId;
    private String name;
    private String url;
    private String type;
    private Integer fAuthorityId;
    private String display;
    private Integer sort;
    private String icon;
    private LocalDateTime createTime;
    private List<TreeAuthorityVo>  children;
}
