package com.wfmyzyz.user.user.vo.role;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author admin
 */
@Data
public class TreeRoleVo {

    private String name;
    private Integer fRoleId;
    private Integer roleId;
    private LocalDateTime createTime;
    private List<TreeRoleVo> children;
}
