package com.wfmyzyz.user.user.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wfmyzyz.user.user.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wfmyzyz.user.user.vo.user.*;
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
public interface IUserService extends IService<User> {

    /**
     * 添加用户
     * @param userVo
     * @return
     */
    Msg addUser(AddUserVo userVo);

    /**
     * 根据条件修改用户密码
     * @param user
     * @param updateUserPasswordVo
     * @return
     */
    Msg updatePasswordByUserAndCondition(User user, UpdateUserPasswordVo updateUserPasswordVo);

    /**
     * 根据用户ID修改用户密码
     * @param updateUserPasswordByIdVo
     * @return
     */
    Msg updatePasswordByUserId(UpdateUserPasswordByIdVo updateUserPasswordByIdVo);

    /**
     * 根据用户id修改状态
     * @param updateStatusVo
     * @return
     */
    Msg updateStatus(UpdateStatusVo updateStatusVo);

    /**
     * 分页查询用户数据
     * @param searchUserVo
     * @return
     */
    IPage<User> getUserList(SearchUserVo searchUserVo);

    /**
     * 用户登录
     * @param username
     * @param decryptPassword
     * @return
     */
    Msg login(String username, String decryptPassword);

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    User getUserByUsername(String username);

    /**
     * 根据用户Ids删除用户与用户角色绑定表
     * @param ids
     * @return
     */
    boolean removeUserAndRoleByIds(List<Integer> ids);
}
