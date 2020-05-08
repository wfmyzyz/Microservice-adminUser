package com.wfmyzyz.user.user.vo.user;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author admin
 */
@Data
@ToString
public class AddUserVo {

    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "验证密码不能为空")
    private String passwords;

}
