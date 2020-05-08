package com.wfmyzyz.user.user.service;

import com.wfmyzyz.user.user.domain.RoleAuthority;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfmyzyz.user.user.vo.authority.BindRoleAuthorityVo;
import com.wfmyzyz.user.utils.Msg;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto
 * @since 2020-03-16
 */
public interface IRoleAuthorityService extends IService<RoleAuthority> {

    /**
     * 根据权限ID删除绑定数据
     * @param ids
     * @return
     */
    boolean removeByAuthorityIds(List<Integer> ids);

    /**
     * 根据角色ID删除绑定数据
     * @param ids
     * @return
     */
    boolean removeByRoleIds(List<Integer> ids);

    /**
     * 绑定角色权限
     * @param bindRoleAuthorityVo
     * @return
     */
    Msg bindRoleAuthority(BindRoleAuthorityVo bindRoleAuthorityVo);

    /**
     * 根据角色ID获取权限关系
     * @param roleId
     * @return
     */
    List<RoleAuthority> listByRoleId(Integer roleId);

    /**
     * 根据角色ID与权限ID删除
     * @param roleId
     * @param delIdList
     * @return
     */
    boolean removeByRoleIdAndAuthorityIds(Integer roleId, List<Integer> delIdList);

    /**
     * 根据角色ID获取角色权限
     * @param roleId
     * @return
     */
    List<RoleAuthority> getRoleAuthorityByRoleId(Integer roleId);

    /**
     * 获取关系表根据角色ID列表
     * @param roleIdList
     * @return
     */
    List<RoleAuthority> listRoleAuthorityByRoleIds(List<Integer> roleIdList);

    /**
     * 获取权限ID根据角色ID列表
     * @param roleIdList
     * @return
     */
    List<Integer> listAuthorityIdByRoleIds(List<Integer> roleIdList);
}
