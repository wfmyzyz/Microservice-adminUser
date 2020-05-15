package com.wfmyzyz.user.user.service.impl;

import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.Role;
import com.wfmyzyz.user.user.domain.UserRole;
import com.wfmyzyz.user.user.mapper.UserRoleMapper;
import com.wfmyzyz.user.user.service.IRoleService;
import com.wfmyzyz.user.user.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfmyzyz.user.user.vo.user.BindUserRoleVo;
import com.wfmyzyz.user.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto
 * @since 2020-03-16
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public boolean removeByUserIds(List<Integer> ids) {
        return this.lambdaUpdate().in(UserRole::getUserId,ids).remove();
    }

    @Override
    public boolean removeByRoleIds(List<Integer> delIdList) {
        return this.lambdaUpdate().in(UserRole::getRoleId,delIdList).remove();
    }

    @Override
    public Msg bindUserRole(Integer opUserId, BindUserRoleVo bindUserRoleVo,Boolean admin) {
        //获取用户可操作的角色
        Set<Integer> opRoleIdSet = userRoleService.listCanOpRoleIdByUserId(opUserId);
        //根据可操作的角色过滤出可以绑定的角色
        List<Integer> bindRoleIdList = new ArrayList<>();
        if (!admin){
            bindUserRoleVo.getRoleIds().forEach(roleId -> {
                if (opRoleIdSet.contains(roleId)){
                    bindRoleIdList.add(roleId);
                }
            });
        }else {
            bindRoleIdList.addAll(bindUserRoleVo.getRoleIds());
        }
        //没有需要绑定的则为删除所有
        if (bindRoleIdList == null || bindRoleIdList.size() <= 0){
            List<Integer> delUserId = new ArrayList<>();
            delUserId.add(bindUserRoleVo.getUserId());
            this.removeByUserIds(delUserId);
            return Msg.success(ProjectResEnum.USER_ROLE_SUCCESS);
        }
        List<UserRole> userRoleList = this.listByUserId(bindUserRoleVo.getUserId());
        List<Integer> existIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        List<Integer> delIdList = new ArrayList<>();
        List<UserRole> addUserRoleList = new ArrayList<>();
        existIdList.forEach(roleId -> {
            if (!bindRoleIdList.contains(roleId)){
                delIdList.add(roleId);
            }
        });
        bindRoleIdList.forEach(roleId-> {
            if (!existIdList.contains(roleId)){
                UserRole userRole = new UserRole();
                userRole.setUserId(bindUserRoleVo.getUserId());
                userRole.setRoleId(roleId);
                addUserRoleList.add(userRole);
            }
        });
        if (delIdList.size() > 0) {
            this.removeByUserIdAndRoleIds(bindUserRoleVo.getUserId(), delIdList);
        }
        if (addUserRoleList.size() > 0){
            this.saveBatch(addUserRoleList);
        }
        return Msg.success(ProjectResEnum.USER_ROLE_SUCCESS);
    }

    @Override
    public List<UserRole> listByUserId(Integer userId) {
        return this.lambdaQuery().eq(UserRole::getUserId,userId).list();
    }

    @Override
    public boolean removeByUserIdAndRoleIds(Integer userId, List<Integer> delIdList) {
        return this.lambdaUpdate().eq(UserRole::getUserId,userId).in(UserRole::getRoleId,delIdList).remove();
    }

    @Override
    public List<UserRole> getUserRoleByUserId(Integer userId) {
        return this.lambdaQuery().eq(UserRole::getUserId,userId).list();
    }

    @Override
    public List<Integer> listRoleIdByUserId(Integer userId) {
        List<UserRole> userRoleList = this.getUserRoleByUserId(userId);
        List<Integer> roleIdList = new ArrayList<>();
        if (userRoleList.size() > 0){
            roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        }
        return roleIdList;
    }

    @Override
    public Set<Integer> listCanOpRoleIdByUserId(Integer opUserId) {
        List<Integer> ownRoleIdList = this.listRoleIdByUserId(opUserId);
        List<Integer> canOpRoleIdList = this.listRoleIdByOwnRoleId(ownRoleIdList);
        Set<Integer> canOpRoleIdSet = new HashSet<>(canOpRoleIdList);
        return canOpRoleIdSet;
    }

    @Override
    public Set<Integer> listOwnOpRoleIdByUserId(Integer opUserId) {
        List<Integer> ownRoleIdList = this.listRoleIdByUserId(opUserId);
        List<Integer> canOpRoleIdList = this.listRoleIdByOwnRoleId(ownRoleIdList);
        canOpRoleIdList.addAll(ownRoleIdList);
        Set<Integer> canOpRoleIdSet = new HashSet<>(canOpRoleIdList);
        return canOpRoleIdSet;
    }

    /**
     * 获取用户可操作的所有子角色ID
     * @param ownRoleIdList
     * @return
     */
    private List<Integer> listRoleIdByOwnRoleId(List<Integer> ownRoleIdList){
        List<Role> roleList = roleService.list();
        List<Integer> canOpRoleIdList = new ArrayList<>();
        ownRoleIdList.forEach(roleId-> {
            List<Integer> sonRoleId = findSonRoleId(roleList, roleId);
            canOpRoleIdList.addAll(sonRoleId);
        });
        return canOpRoleIdList;
    }

    /**
     * 找到子角色ID
     * @param roleList
     * @param fId
     * @return
     */
    private List<Integer> findSonRoleId(List<Role> roleList,Integer fId){
        List<Integer> roleIdList = new ArrayList<>();
        roleList.forEach(role -> {
            if (Objects.equals(role.getfRoleId(),fId)){
                Integer roleId = role.getRoleId();
                List<Integer> sonRoleId = findSonRoleId(roleList, roleId);
                roleIdList.add(role.getRoleId());
                roleIdList.addAll(sonRoleId);
            }
        });
        return roleIdList;
    }
}
