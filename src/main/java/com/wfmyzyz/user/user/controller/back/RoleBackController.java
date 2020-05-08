package com.wfmyzyz.user.user.controller.back;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.Role;
import com.wfmyzyz.user.user.domain.RoleAuthority;
import com.wfmyzyz.user.user.domain.UserRole;
import com.wfmyzyz.user.user.service.IRoleAuthorityService;
import com.wfmyzyz.user.user.service.IRoleService;
import com.wfmyzyz.user.user.vo.authority.BindRoleAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.TreeAuthorityVo;
import com.wfmyzyz.user.user.vo.role.AddRoleVo;
import com.wfmyzyz.user.user.vo.role.SearchRoleVo;
import com.wfmyzyz.user.user.vo.role.TreeRoleVo;
import com.wfmyzyz.user.user.vo.role.UpdateRoleVo;
import com.wfmyzyz.user.user.vo.user.BindUserRoleVo;
import com.wfmyzyz.user.utils.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author admin
 */
@RestController
@RequestMapping("back/role")
public class RoleBackController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IRoleAuthorityService roleAuthorityService;

    @ApiOperation(value="树形角色列表" ,httpMethod="GET")
    @GetMapping("getTreeRoleList")
    public Msg getTreeRoleList(){
        List<TreeRoleVo> roleList =  roleService.getRoleList();
        return Msg.success().add(roleList);
    }

    @ApiOperation(value="查询角色" ,httpMethod="GET")
    @GetMapping("getRoleList")
    public Msg getRoleList(@Valid @RequestBody SearchRoleVo searchRoleVo){
        IPage<Role> roleList = roleService.getRoleList(searchRoleVo);
        return Msg.success().add(roleList);
    }

    @ApiOperation(value="添加角色" ,httpMethod="POST")
    @PostMapping("addRole")
    public Msg addRole(@Valid @RequestBody AddRoleVo addRoleVo){
        boolean flag = roleService.addRole(addRoleVo);
        if (!flag){
            return Msg.error(ProjectResEnum.ROLE_ADD_FAIL);
        }
        return Msg.success(ProjectResEnum.ROLE_ADD_SUCCESS);
    }

    @ApiOperation(value="修改角色" ,httpMethod="POST")
    @PostMapping("updateRole")
    public Msg updateRole(@Valid @RequestBody UpdateRoleVo updateRoleVo){
        boolean flag = roleService.updateRole(updateRoleVo);
        if (!flag){
            return Msg.error(ProjectResEnum.ROLE_UPDATE_FAIL);
        }
        return Msg.success(ProjectResEnum.ROLE_UPDATE_SUCCESS);
    }

    @ApiOperation(value="删除角色" ,httpMethod="POST")
    @PostMapping("deleteRole")
    public Msg deleteRole(@RequestBody List<Integer> ids){
        if (ids == null || ids.size() <= 0){
            return Msg.error(ProjectResEnum.ROLE_DELETE_SIZE);
        }
        boolean flag = roleService.removeByIdsAndSon(ids);
        if (!flag){
            return Msg.error(ProjectResEnum.ROLE_DELETE_FAIL);
        }
        return Msg.success(ProjectResEnum.ROLE_DELETE_SUCCESS);
    }

    @ApiOperation(value="绑定角色权限" ,httpMethod="POST")
    @PostMapping(value = "/bindRoleAuthority")
    public Msg bindRoleAuthority(@Valid @RequestBody BindRoleAuthorityVo bindRoleAuthorityVo){
        return roleAuthorityService.bindRoleAuthority(bindRoleAuthorityVo);
    }

    @ApiOperation(value="获取绑定角色权限" ,httpMethod="POST")
    @PostMapping(value = "/getRoleAuthority")
    public Msg getUserRole(@RequestBody JSONObject params){
        Integer roleId = params.getInteger("roleId");
        List<RoleAuthority> roleAuthorityList = roleAuthorityService.getRoleAuthorityByRoleId(roleId);
        return Msg.success().add(roleAuthorityList);
    }

}
