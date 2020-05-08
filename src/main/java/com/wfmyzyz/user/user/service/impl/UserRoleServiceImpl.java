package com.wfmyzyz.user.user.service.impl;

import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.UserRole;
import com.wfmyzyz.user.user.mapper.UserRoleMapper;
import com.wfmyzyz.user.user.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfmyzyz.user.user.vo.user.BindUserRoleVo;
import com.wfmyzyz.user.utils.Msg;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public boolean removeByUserIds(List<Integer> ids) {
        return this.lambdaUpdate().in(UserRole::getUserId,ids).remove();
    }

    @Override
    public boolean removeByRoleIds(List<Integer> delIdList) {
        return this.lambdaUpdate().in(UserRole::getRoleId,delIdList).remove();
    }

    @Override
    public Msg bindUserRole(BindUserRoleVo bindUserRoleVo) {
        //没有需要绑定的则为删除所有
        if (bindUserRoleVo.getRoleIds() == null || bindUserRoleVo.getRoleIds().size() <= 0){
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
            if (!bindUserRoleVo.getRoleIds().contains(roleId)){
                delIdList.add(roleId);
            }
        });
        bindUserRoleVo.getRoleIds().forEach(roleId-> {
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
}
