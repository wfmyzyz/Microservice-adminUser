package com.wfmyzyz.user.user.vo.user;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author admin
 */
@Data
public class BindUserRoleVo {

    @NotNull(message = "绑定用户ID不能为空")
    private Integer userId;
    private List<Integer> roleIds;
}
