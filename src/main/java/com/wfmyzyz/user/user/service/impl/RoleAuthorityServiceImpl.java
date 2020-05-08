package com.wfmyzyz.user.user.service.impl;

import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.RoleAuthority;
import com.wfmyzyz.user.user.domain.UserRole;
import com.wfmyzyz.user.user.mapper.RoleAuthorityMapper;
import com.wfmyzyz.user.user.service.IRoleAuthorityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfmyzyz.user.user.vo.authority.BindRoleAuthorityVo;
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
public class RoleAuthorityServiceImpl extends ServiceImpl<RoleAuthorityMapper, RoleAuthority> implements IRoleAuthorityService {

    @Override
    public boolean removeByAuthorityIds(List<Integer> ids) {
        return this.lambdaUpdate().in(RoleAuthority::getAuthorityId,ids).remove();
    }

    @Override
    public boolean removeByRoleIds(List<Integer> ids) {
        return this.lambdaUpdate().in(RoleAuthority::getRoleId,ids).remove();
    }

    @Override
    public Msg bindRoleAuthority(BindRoleAuthorityVo bindRoleAuthorityVo) {
        //没有需要绑定的则为删除所有
        if (bindRoleAuthorityVo.getAuthorityIdList() == null || bindRoleAuthorityVo.getAuthorityIdList().size() <= 0){
            List<Integer> delUserId = new ArrayList<>();
            delUserId.add(bindRoleAuthorityVo.getRoleId());
            this.removeByRoleIds(delUserId);
            return Msg.success(ProjectResEnum.USER_ROLE_SUCCESS);
        }
        List<RoleAuthority> roleAuthorityList = this.listByRoleId(bindRoleAuthorityVo.getRoleId());
        List<Integer> existIdList = roleAuthorityList.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        List<Integer> delIdList = new ArrayList<>();
        List<RoleAuthority> addUserRoleList = new ArrayList<>();
        existIdList.forEach(authorityId -> {
            if (!bindRoleAuthorityVo.getAuthorityIdList().contains(authorityId)){
                delIdList.add(authorityId);
            }
        });
        bindRoleAuthorityVo.getAuthorityIdList().forEach(authorityId-> {
            if (!existIdList.contains(authorityId)){
                RoleAuthority roleAuthority = new RoleAuthority();
                roleAuthority.setRoleId(bindRoleAuthorityVo.getRoleId());
                roleAuthority.setAuthorityId(authorityId);
                addUserRoleList.add(roleAuthority);
            }
        });
        if (delIdList.size() > 0) {
            this.removeByRoleIdAndAuthorityIds(bindRoleAuthorityVo.getRoleId(), delIdList);
        }
        if (addUserRoleList.size() > 0){
            this.saveBatch(addUserRoleList);
        }
        return Msg.success(ProjectResEnum.ROLE_AUTHORITY_SUCCESS);
    }

    @Override
    public List<RoleAuthority> listByRoleId(Integer roleId) {
        return this.lambdaQuery().eq(RoleAuthority::getRoleId,roleId).list();
    }

    @Override
    public boolean removeByRoleIdAndAuthorityIds(Integer roleId, List<Integer> delIdList) {
        return this.lambdaUpdate().eq(RoleAuthority::getRoleId,roleId).in(RoleAuthority::getAuthorityId,delIdList).remove();
    }

    @Override
    public List<RoleAuthority> getRoleAuthorityByRoleId(Integer roleId) {
        return this.lambdaQuery().eq(RoleAuthority::getRoleId,roleId).list();
    }

    @Override
    public List<RoleAuthority> listRoleAuthorityByRoleIds(List<Integer> roleIdList) {
        return this.lambdaQuery().in(RoleAuthority::getRoleId,roleIdList).list();
    }

    @Override
    public List<Integer> listAuthorityIdByRoleIds(List<Integer> roleIdList) {
        List<RoleAuthority> roleAuthorityList = this.listRoleAuthorityByRoleIds(roleIdList);
        List<Integer> authorityIdList = new ArrayList<>();
        if (roleAuthorityList.size() > 0){
            authorityIdList = roleAuthorityList.stream().map(RoleAuthority::getAuthorityId).collect(Collectors.toList());
        }
        return authorityIdList;
    }
}
