package com.wfmyzyz.user.user.vo.user;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @author admin
 */
@Data
@ToString
public class SearchUserVo {

    @NotNull
    private Integer page;
    @NotNull
    private Integer pageSize;
    private Integer userId;
    private String status;
    private String username;
    private String startTime;
    private String endTime;
}
