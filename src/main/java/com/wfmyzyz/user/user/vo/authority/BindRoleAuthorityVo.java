package com.wfmyzyz.user.user.vo.authority;

import lombok.Data;

import java.util.List;

/**
 * @author admin
 */
@Data
public class BindRoleAuthorityVo {
    private Integer roleId;
    private List<Integer> authorityIdList;
}
