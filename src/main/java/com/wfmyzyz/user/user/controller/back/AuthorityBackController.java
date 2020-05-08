package com.wfmyzyz.user.user.controller.back;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.Authority;
import com.wfmyzyz.user.user.service.IAuthorityService;
import com.wfmyzyz.user.user.vo.authority.AddAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.SearchAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.TreeAuthorityVo;
import com.wfmyzyz.user.user.vo.authority.UpdateAuthorityVo;
import com.wfmyzyz.user.user.vo.user.MenuListVo;
import com.wfmyzyz.user.utils.Msg;
import com.wfmyzyz.user.utils.TokenUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @author admin
 */
@RestController
@RequestMapping("back/authority")
public class AuthorityBackController {

    @Autowired
    private IAuthorityService authorityService;
    @Autowired
    private TokenUtils tokenUtils;

    @ApiOperation(value="根据用户token获取用户菜单",httpMethod="GET")
    @GetMapping("getMenuList")
    public Msg getMenuList(HttpServletRequest request){
        Integer userId = tokenUtils.getUserIdByRequest(request);
        List<MenuListVo> menuListVoList =  authorityService.getMenuList(userId);
        return Msg.success().add("data",menuListVoList);
    }

    @ApiOperation(value="树形权限列表" ,httpMethod="GET")
    @GetMapping("getTreeAuthorityList")
    public Msg getTreeAuthorityList(){
        List<TreeAuthorityVo> authorityList =  authorityService.getTreeAuthorityList();
        return Msg.success().add(authorityList);
    }

    @ApiOperation(value="查询权限列表" ,httpMethod="GET")
    @GetMapping("getAuthorityList")
    public Msg getAuthorityList(@Valid @RequestBody SearchAuthorityVo searchAuthorityVo){
        IPage<Authority> authorityList = authorityService.getAuthorityList(searchAuthorityVo);
        return Msg.success().add(authorityList);
    }

    @ApiOperation(value="添加权限" ,httpMethod="POST")
    @PostMapping("addAuthority")
    public Msg addAuthority(@Valid @RequestBody AddAuthorityVo addAuthorityVo){
        return authorityService.addAuthority(addAuthorityVo);
    }

    @ApiOperation(value="修改权限" ,httpMethod="POST")
    @PostMapping("updateAuthority")
    public Msg updateAuthority(@Valid @RequestBody UpdateAuthorityVo updateAuthorityVo){
        return authorityService.updateAuthority(updateAuthorityVo);
    }

    @ApiOperation(value="删除权限" ,httpMethod="POST")
    @PostMapping("deleteAuthority")
    public Msg deleteAuthority(@RequestBody List<Integer> ids){
        if (ids == null || ids.size() <= 0){
            return Msg.error(ProjectResEnum.AUTHORITY_DELETE_SIZE);
        }
        boolean flag = authorityService.removeByIdsAndSon(ids);
        if (!flag){
            return Msg.error(ProjectResEnum.AUTHORITY_DELETE_FAIL);
        }
        return Msg.error(ProjectResEnum.AUTHORITY_DELETE_SUCCESS);
    }
}
