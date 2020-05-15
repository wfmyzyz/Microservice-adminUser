package com.wfmyzyz.user.user.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.User;
import com.wfmyzyz.user.user.domain.UserRole;
import com.wfmyzyz.user.user.service.IUserRoleService;
import com.wfmyzyz.user.user.service.IUserService;
import com.wfmyzyz.user.user.vo.user.*;
import com.wfmyzyz.user.utils.Msg;
import com.wfmyzyz.user.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author admin
 */
@RestController
@RequestMapping("back/user")
public class UserBackController {

    @Autowired
    private IUserService userService;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private IUserRoleService userRoleService;

    @ApiOperation(value="根据用户token获取用户信息",httpMethod="GET")
    @GetMapping("info")
    public Msg info(HttpServletRequest request){
        User user = tokenUtils.getUserByRequest(request);
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        return Msg.success().add(new UserInfoVo(user.getUsername(),roles,"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif",""));
    }

    @ApiOperation(value="查询用户列表",httpMethod="POST")
    @PostMapping("getUserList")
    public Msg getUserList(@Valid @RequestBody SearchUserVo searchUserVo){
        IPage<User> userList = userService.getUserList(searchUserVo);
        return Msg.success().add(userList);
    }

    @ApiOperation(value="添加新用户" ,httpMethod="POST")
    @PostMapping("addUser")
    public Msg addUser(@Valid @RequestBody AddUserVo userVo){
        if (!Objects.equals(userVo.getPassword(),userVo.getPasswords())){
            return Msg.error(ProjectResEnum.USER_NO_SAME_PASSWORD);
        }
        return userService.addUser(userVo);
    }

    @ApiOperation(value="修改用户密码" ,httpMethod="POST")
    @PostMapping(value = "/updatePassword")
    public Msg updatePassword(@Valid @RequestBody UpdateUserPasswordVo updateUserPasswordVo, HttpServletRequest request){
        User user = tokenUtils.getUserByRequest(request);
        if (user == null){
            return Msg.error(ProjectResEnum.USER_NONENTITY);
        }
        return userService.updatePasswordByUserAndCondition(user,updateUserPasswordVo);
    }

    @ApiOperation(value="根据用户ID修改用户密码" ,httpMethod="POST")
    @PostMapping(value = "/updatePasswordByUserId")
    public Msg updatePasswordByUserId(@Valid @RequestBody UpdateUserPasswordByIdVo updateUserPasswordByIdVo,HttpServletRequest request){
        Integer opUserId = tokenUtils.getUserIdByRequest(request);
        //判断当前操作人员的角色是否都大于被操作人员
        if(!userService.isAdmin(opUserId) && !userService.judgeUserAContainB(opUserId,updateUserPasswordByIdVo.getUserId())){
            return Msg.error(ProjectResEnum.NONE_AUTHORITY);
        }
        return userService.updatePasswordByUserId(updateUserPasswordByIdVo);
    }

    @ApiOperation(value="修改用户状态" ,httpMethod="POST")
    @PostMapping(value = "/updateStatus")
    public Msg updateStatus(@Valid @RequestBody UpdateStatusVo updateStatusVo,HttpServletRequest request){
        Integer opUserId = tokenUtils.getUserIdByRequest(request);
        //判断当前操作人员的角色是否都大于被操作人员
        if(!userService.isAdmin(opUserId) && !userService.judgeUserAContainB(opUserId,updateStatusVo.getUserId())){
            return Msg.error(ProjectResEnum.NONE_AUTHORITY);
        }
        return userService.updateStatus(updateStatusVo);
    }

    @ApiOperation(value="删除用户" ,httpMethod="POST")
    @PostMapping(value = "/deleteUser")
    public Msg deleteUser(@RequestBody List<Integer> ids,HttpServletRequest request){
        if (ids == null || ids.size() <= 0){
            return Msg.error(ProjectResEnum.USER_DELETE_SIZE);
        }
        Integer opUserId = tokenUtils.getUserIdByRequest(request);
        List<Integer> canRemoveUserId = new ArrayList<>();
        //过滤出可以删除的用户
        ids.forEach(id -> {
            if(userService.judgeUserAContainB(opUserId,id)){
                canRemoveUserId.add(id);
            }
        });
        if (canRemoveUserId.size() <= 0){
            return Msg.success(ProjectResEnum.NONE_AUTHORITY);
        }
        boolean flag = userService.removeUserAndRoleByIds(canRemoveUserId);
        if (!flag){
            return Msg.error(ProjectResEnum.USER_DELETE_FAIL);
        }
        if (canRemoveUserId.size() < ids.size()){
            return Msg.success(ProjectResEnum.USER_DELETE_PART_SUCCESS);
        }
        return Msg.success(ProjectResEnum.USER_DELETE_SUCCESS);
    }

    @ApiOperation(value="绑定用户角色" ,httpMethod="POST")
    @PostMapping(value = "/bindUserRole")
    public Msg bindUserRole(@Valid @RequestBody BindUserRoleVo bindUserRoleVo,HttpServletRequest request){
        Integer opUserId = tokenUtils.getUserIdByRequest(request);
        //判断当前操作人员的角色是否都大于被操作人员
        boolean admin = userService.isAdmin(opUserId);
        if(!admin && !userService.judgeUserAContainB(opUserId,bindUserRoleVo.getUserId())){
            return Msg.error(ProjectResEnum.NONE_AUTHORITY);
        }
        return userRoleService.bindUserRole(opUserId, bindUserRoleVo,admin);
    }

    @ApiOperation(value="获取绑定用户角色" ,httpMethod="POST")
    @PostMapping(value = "/getUserRole")
    public Msg getUserRole(@RequestBody JSONObject params,HttpServletRequest request){
        Integer userId = params.getInteger("userId");
        Integer opUserId = tokenUtils.getUserIdByRequest(request);
        //判断当前操作人员的角色是否都大于被操作人员
        if(!userService.isAdmin(opUserId) && !userService.judgeUserAContainB(opUserId,userId)){
            return Msg.error(ProjectResEnum.NONE_AUTHORITY);
        }
        List<UserRole> userRoleList = userRoleService.getUserRoleByUserId(userId);
        return Msg.success().add(userRoleList);
    }
}
