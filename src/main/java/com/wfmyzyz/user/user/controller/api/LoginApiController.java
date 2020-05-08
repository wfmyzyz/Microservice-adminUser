package com.wfmyzyz.user.user.controller.api;

import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.service.IUserService;
import com.wfmyzyz.user.user.vo.api.UserLogin;
import com.wfmyzyz.user.utils.Msg;
import com.wfmyzyz.user.utils.RsaUtils;
import com.wfmyzyz.user.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * @author admin
 */
@Api(tags="API模块-登录管理")
@RestController
@RequestMapping("api")
public class LoginApiController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserUtils userUtils;

    /**
     * 获取公钥
     * @return
     */
    @ApiOperation(value="获取公钥", notes="获取公钥" ,httpMethod="GET")
    @RequestMapping(value = "/login/getRsaPublicKey", method = RequestMethod.GET)
    public Msg getPublicKey(){
        String publicKey = userUtils.getPublicKey();
        if (StringUtils.isBlank(publicKey)){
            return Msg.error(ProjectResEnum.PUBLIC_ERROR);
        }
        return Msg.success().add("publicKey",publicKey);
    }

    @ApiOperation(value="用户登录",httpMethod="POST")
    @PostMapping("login")
    public Msg login(@Valid @RequestBody UserLogin user){
        String decryptPassword = userUtils.decryptPassword(user.getPassword());
        if (StringUtils.isBlank(decryptPassword)){
            return Msg.error(ProjectResEnum.LOGIN_ERROR);
        }
        return userService.login(user.getUsername(),decryptPassword);
    }
}
