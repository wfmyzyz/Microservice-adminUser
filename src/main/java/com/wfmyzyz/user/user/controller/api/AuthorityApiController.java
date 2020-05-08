package com.wfmyzyz.user.user.controller.api;

import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.service.IAuthorityService;
import com.wfmyzyz.user.user.service.IRoleAuthorityService;
import com.wfmyzyz.user.user.vo.api.AuthVo;
import com.wfmyzyz.user.utils.Msg;
import com.wfmyzyz.user.utils.TokenUtils;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author admin
 */
@Api(tags="API模块-登录管理")
@RestController
@RequestMapping("api/authority")
public class AuthorityApiController {

    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private IAuthorityService authorityService;

    @PostMapping("auth")
    public Msg authAuthority(@RequestBody AuthVo authVo){
        if (StringUtils.isBlank(authVo.getToken())){
            return Msg.error(ProjectResEnum.LOGIN);
        }
        Integer userId = tokenUtils.getUserIdByToken(authVo.getToken());
        if (userId == null){
            return Msg.error(ProjectResEnum.LOGIN);
        }
        boolean auth = authorityService.judgeAuthByUserIdAndUrl(authVo.getUrl(), userId);
        if (!auth){
            return Msg.error(ProjectResEnum.NONE_AUTHORITY);
        }
        return Msg.success();
    }
}
