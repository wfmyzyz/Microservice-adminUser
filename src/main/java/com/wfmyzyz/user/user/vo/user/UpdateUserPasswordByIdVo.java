package com.wfmyzyz.user.user.vo.user;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author admin
 */
@Data
@ToString
public class UpdateUserPasswordByIdVo {

    @NotNull(message = "用户id不能为空")
    private Integer userId;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @NotBlank(message = "新验证密码不能为空")
    private String newPasswords;
}
