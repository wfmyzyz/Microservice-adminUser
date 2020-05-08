package com.wfmyzyz.user.user.vo.user;

import com.wfmyzyz.user.user.enums.UserStatusEnum;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author admin
 */
@Data
@ToString
public class UpdateStatusVo {
    @NotNull(message = "用户id不能为空")
    private Integer userId;
}
