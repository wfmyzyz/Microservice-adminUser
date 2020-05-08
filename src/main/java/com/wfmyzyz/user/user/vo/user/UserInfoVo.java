package com.wfmyzyz.user.user.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 * @author xiong
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoVo {
    private String name;
    private List<String> roles;
    private String avatar;
    private String introduction;
}
