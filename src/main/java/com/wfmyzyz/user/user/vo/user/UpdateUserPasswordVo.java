package com.wfmyzyz.user.user.vo.user;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author admin
 */
@Data
@ToString
public class UpdateUserPasswordVo {

    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @NotBlank(message = "新验证密码不能为空")
    private String newPasswords;

}
