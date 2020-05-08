package com.wfmyzyz.user.user.service;

import com.wfmyzyz.user.user.domain.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfmyzyz.user.user.vo.user.BindUserRoleVo;
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
public interface IUserRoleService extends IService<UserRole> {

    /**
     * 根据用户ids删除绑定表
     * @param ids
     * @return
     */
    boolean removeByUserIds(List<Integer> ids);

    /**
     * 根据用户角色ID删除绑定表
     * @param delIdList
     * @return
     */
    boolean removeByRoleIds(List<Integer> delIdList);

    /**
     * 绑定用户角色
     * @param bindUserRoleVo
     * @return
     */
    Msg bindUserRole(BindUserRoleVo bindUserRoleVo);

    /**
     * 根据用户ID获取绑定角色关系
     * @param userId
     * @return
     */
    List<UserRole> listByUserId(Integer userId);

    /**
     * 根据用户ID，角色IDs删除关系表
     * @param userId
     * @param delIdList
     * @return
     */
    boolean removeByUserIdAndRoleIds(Integer userId, List<Integer> delIdList);

    /**
     * 根据用户ID获取关系表
     * @param userId
     * @return
     */
    List<UserRole> getUserRoleByUserId(Integer userId);

    /**
     * 根据用户ID获取角色ID
     * @param userId
     * @return
     */
    List<Integer> listRoleIdByUserId(Integer userId);
}
