package com.wfmyzyz.user.user.vo.api;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author admin
 */
@Data
@ToString
public class UserLogin {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
