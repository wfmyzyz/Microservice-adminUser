package com.wfmyzyz.user.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.user.domain.Authority;
import com.wfmyzyz.user.user.domain.Role;
import com.wfmyzyz.user.user.mapper.RoleMapper;
import com.wfmyzyz.user.user.service.IRoleAuthorityService;
import com.wfmyzyz.user.user.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfmyzyz.user.user.service.IUserRoleService;
import com.wfmyzyz.user.user.service.IUserService;
import com.wfmyzyz.user.user.vo.authority.TreeAuthorityVo;
import com.wfmyzyz.user.user.vo.role.AddRoleVo;
import com.wfmyzyz.user.user.vo.role.SearchRoleVo;
import com.wfmyzyz.user.user.vo.role.TreeRoleVo;
import com.wfmyzyz.user.user.vo.role.UpdateRoleVo;
import com.wfmyzyz.user.utils.RoleUtils;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto
 * @since 2020-03-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRoleAuthorityService roleAuthorityService;
    @Autowired
    private IUserRoleService userRoleService;
    @Autowired
    private IUserService userService;

    @Override
    public boolean addRole(AddRoleVo addRoleVo) {
        Role role = new Role();
        role.setName(addRoleVo.getName());
        role.setfRoleId(addRoleVo.getFRoleId());
        return this.save(role);
    }

    @Override
    public boolean updateRole(UpdateRoleVo updateRoleVo) {
        return this.lambdaUpdate().set(Role::getName,updateRoleVo.getName()).eq(Role::getRoleId,updateRoleVo.getRoleId()).update();
    }

    @Override
    public IPage<Role> getRoleList(SearchRoleVo searchRoleVo) {
        LambdaQueryChainWrapper<Role> lambdaQuery = this.lambdaQuery();
        if (searchRoleVo.getRoleId() != null){
            lambdaQuery.eq(Role::getRoleId,searchRoleVo.getRoleId());
        }
        if (StringUtils.isNotBlank(searchRoleVo.getName())){
            lambdaQuery.eq(Role::getName,searchRoleVo.getName());
        }
        if (searchRoleVo.getFRoleId() != null){
            lambdaQuery.eq(Role::getfRoleId,searchRoleVo.getFRoleId());
        }
        lambdaQuery.orderByDesc(Role::getCreateTime);
        return lambdaQuery.page(new Page<>(searchRoleVo.getPage(),searchRoleVo.getPageSize()));
    }

    @Override
    public List<TreeRoleVo> getRoleList(Integer userId) {
        //获取当前用户角色ID
        Set<Integer> topList = new HashSet<>();
        List<Role> roleList = this.listByOrderCreateTimeAsc();
        List<Integer> roleIdList = userRoleService.listRoleIdByUserId(userId);
        RoleUtils roleUtils = new RoleUtils(roleList,roleIdList);
        topList.addAll(roleUtils.getTopList());
        List<TreeRoleVo> treeRoleVoList = new ArrayList<>();
        topList.forEach(topId -> {
            List<TreeRoleVo> sonRole = findSonRole(roleList, topId);
            treeRoleVoList.addAll(sonRole);
        });
        return treeRoleVoList;
    }

    /**
     * 获取子角色列表
     * @param roleList
     * @param fId
     * @return
     */
    private List<TreeRoleVo> findSonRole(List<Role> roleList, Integer fId) {
        List<TreeRoleVo> treeRoleVoList = new ArrayList<>();
        roleList.forEach(role -> {
            if (Objects.equals(role.getfRoleId(),fId)){
                List<TreeRoleVo> sonRole = findSonRole(roleList, role.getRoleId());
                TreeRoleVo treeRoleVo = new TreeRoleVo();
                treeRoleVo.setChildren(sonRole);
                BeanUtils.copyProperties(role,treeRoleVo);
                treeRoleVo.setFRoleId(role.getfRoleId());
                treeRoleVoList.add(treeRoleVo);
            }
        });
        return treeRoleVoList;
    }

    @Override
    public List<Role> listByOrderCreateTimeAsc() {
        return this.lambdaQuery().orderByAsc().list();
    }

    @Override
    public boolean removeByIdsAndSon(List<Integer> ids, Integer opUserId) {
        //删除角色只能删除自身角色之下的角色
        Set<Integer> opRoleIdSet = userRoleService.listCanOpRoleIdByUserId(opUserId);
        List<Integer> canDeleteRoleId = new ArrayList<>();
        ids.forEach(id -> {
            if (opRoleIdSet.contains(id)){
                canDeleteRoleId.add(id);
            }
        });
        if (canDeleteRoleId.size() <= 0){
            return true;
        }
        List<Role> roleList = this.list();
        List<Integer> delIdList = new ArrayList<>();
        canDeleteRoleId.forEach(id -> {
            List<Integer> sonIdList = getSonRoleIdById(roleList, id);
            delIdList.addAll(sonIdList);
            delIdList.add(id);
        });
        //根据角色ID删除角色权限绑定表
        roleAuthorityService.removeByRoleIds(delIdList);
        //根据角色ID删除用户角色绑定表
        userRoleService.removeByRoleIds(delIdList);
        return this.removeByIds(delIdList);
    }

    @Override
    public List<TreeRoleVo> getTreeRoleBindList(Integer userId) {
        List<TreeRoleVo> intoRoleList = new ArrayList<>();
        List<TreeRoleVo> roleList = this.getRoleList(userId);
        if (userService.isAdmin(userId)){
            return roleList;
        }
        roleList.forEach(treeRoleVo -> {
            intoRoleList.addAll(treeRoleVo.getChildren());
        });
        return intoRoleList;
    }

    /**
     * 根据角色ID获取子角色
     * @param roleList
     * @param fId
     * @return
     */
    private List<Integer> getSonRoleIdById(List<Role> roleList,Integer fId){
        List<Integer> idList = new ArrayList<>();
        roleList.forEach(authority -> {
            if (Objects.equals(authority.getfRoleId(),fId)){
                List<Integer> sonIdList = getSonRoleIdById(roleList, authority.getRoleId());
                idList.addAll(sonIdList);
                idList.add(authority.getRoleId());
            }
        });
        return idList;
    }
}
