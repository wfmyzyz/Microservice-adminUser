package com.wfmyzyz.user.user.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wfmyzyz.user.config.MybatisPlusConfig;
import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.enums.ProjectResEnum;
import com.wfmyzyz.user.user.domain.User;
import com.wfmyzyz.user.user.enums.UserStatusEnum;
import com.wfmyzyz.user.user.mapper.UserMapper;
import com.wfmyzyz.user.user.service.IUserRoleService;
import com.wfmyzyz.user.user.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wfmyzyz.user.user.vo.user.*;
import com.wfmyzyz.user.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto
 * @since 2020-03-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IUserRoleService userRoleService;

    @Override
    public Msg addUser(AddUserVo userVo) {
        User user = this.getUserByUsername(userVo.getUsername());
        if (user != null){
            return Msg.error(ProjectResEnum.USER_ALREADY_EXISTED);
        }
        user = new User();
        user.setUsername(userVo.getUsername());
        String userSalt = userUtils.getUserSalt();
        user.setSalt(userSalt);
        String password = userUtils.getMd5StrByPasswordAndSalt(user.getPassword(), userSalt);
        user.setPassword(password);
        boolean flag = this.save(user);
        if (!flag){
            return Msg.error(ProjectResEnum.USER_ADD_FAIL);
        }
        return Msg.success(ProjectResEnum.USER_ADD_SUCCESS);
    }

    @Override
    public Msg updatePasswordByUserAndCondition(User user, UpdateUserPasswordVo updateUserPasswordVo) {
        if (!Objects.equals(updateUserPasswordVo.getNewPassword(),updateUserPasswordVo.getNewPasswords())){
            return Msg.error(ProjectResEnum.USER_NO_SAME_PASSWORD);
        }
        String oldPassword = userUtils.getMd5StrByPasswordAndSalt(updateUserPasswordVo.getOldPassword(), user.getSalt());
        if (!Objects.equals(oldPassword,user.getPassword())){
            return Msg.error(ProjectResEnum.USER_OLD_PASSWORD_ERROR);
        }
        boolean flag = this.updatePassword(user,updateUserPasswordVo.getNewPassword());
        if (!flag){
            return Msg.error(ProjectResEnum.USER_UPDATE_FAIL);
        }
        return Msg.success(ProjectResEnum.USER_UPDATE_SUCCESS);
    }

    @Override
    public Msg updatePasswordByUserId(UpdateUserPasswordByIdVo updateUserPasswordByIdVo) {
        if (!Objects.equals(updateUserPasswordByIdVo.getNewPassword(),updateUserPasswordByIdVo.getNewPasswords())){
            return Msg.error(ProjectResEnum.USER_NO_SAME_PASSWORD);
        }
        User user = this.getById(updateUserPasswordByIdVo.getUserId());
        if (user == null){
            return Msg.error(ProjectResEnum.USER_NONENTITY);
        }
        boolean flag = updatePassword(user, updateUserPasswordByIdVo.getNewPassword());
        if (!flag){
            return Msg.error(ProjectResEnum.USER_UPDATE_FAIL);
        }
        return Msg.success(ProjectResEnum.USER_UPDATE_SUCCESS);
    }

    /**
     * 修改用户密码
     * @param user
     * @param password
     * @return
     */
    private boolean updatePassword(User user,String password){
        String userSalt = userUtils.getUserSalt();
        user.setSalt(userSalt);
        String newPassword = userUtils.getMd5StrByPasswordAndSalt(password, userSalt);
        user.setPassword(newPassword);
        user.setUpdateTime(null);
        return this.updateById(user);
    }

    @Override
    public Msg updateStatus(UpdateStatusVo updateStatusVo) {
        User user = this.getById(updateStatusVo.getUserId());
        if (user == null){
            return Msg.error(ProjectResEnum.USER_NONENTITY);
        }
        LambdaUpdateChainWrapper<User> lambdaUpdate = this.lambdaUpdate().eq(User::getUserId, user.getUserId());
        if (Objects.equals(user.getStatus(), UserStatusEnum.正常.toString())){
            lambdaUpdate.set(User::getStatus,UserStatusEnum.冻结.toString());
        }else {
            lambdaUpdate.set(User::getStatus,UserStatusEnum.正常.toString());
        }
        boolean flag = lambdaUpdate.update();
        if (!flag){
            return Msg.error(ProjectResEnum.USER_UPDATE_FAIL);
        }
        return Msg.success(ProjectResEnum.USER_UPDATE_SUCCESS);
    }

    @Override
    public IPage<User> getUserList(SearchUserVo searchUserVo) {
        LambdaQueryChainWrapper<User> lambdaQuery = this.lambdaQuery();
        if (searchUserVo.getUserId() != null){
            lambdaQuery.eq(User::getUserId,searchUserVo.getUserId());
        }
        if (StringUtils.isNotBlank(searchUserVo.getStatus())){
            lambdaQuery.eq(User::getStatus,searchUserVo.getStatus());
        }
        if (StringUtils.isNotBlank(searchUserVo.getUsername())){
            lambdaQuery.like(User::getUsername,searchUserVo.getUsername());
        }
        if (StringUtils.isNotBlank(searchUserVo.getStartTime())){
            lambdaQuery.ge(User::getCreateTime,searchUserVo.getStartTime());
        }
        if (StringUtils.isNotBlank(searchUserVo.getEndTime())){
            lambdaQuery.le(User::getCreateTime,searchUserVo.getEndTime());
        }
        lambdaQuery.orderByDesc(User::getCreateTime);
        return lambdaQuery.page(new Page<>(searchUserVo.getPage(),searchUserVo.getPageSize()));
    }

    @Override
    public Msg login(String username, String decryptPassword) {
        User user = this.getUserByUsername(username);
        if (user == null){
            return Msg.error(ProjectResEnum.LOGIN_ERROR_NO_USER);
        }
        String strByPasswordAndSalt = userUtils.getMd5StrByPasswordAndSalt(decryptPassword, user.getSalt());
        if (!Objects.equals(strByPasswordAndSalt,user.getPassword())){
            return Msg.error(ProjectResEnum.LOGIN_ERROR_NO_USER);
        }
        if (Objects.equals(user.getStatus(),UserStatusEnum.冻结.toString())){
            return Msg.error(ProjectResEnum.LOGIN_ERROR_NO_USER);
        }
        String token = tokenUtils.createToken(user.getUserId());
        if (StringUtils.isBlank(token)){
            return Msg.error(ProjectResEnum.LOGIN_ERROR);
        }
        String idKey = ProjectConfig.USER + user.getUserId();
        String tokenKey = ProjectConfig.TOKEN + token;
        if (redisUtils.getValue(idKey) != null){
            String oldToken = redisUtils.getValue(idKey);
            redisUtils.deletStringeKey(oldToken);
        }
        redisUtils.addToRedis(idKey,tokenKey,ProjectConfig.LOGIN_MAX_TIME, TimeUnit.MINUTES);
        redisUtils.addToRedis(tokenKey,user,ProjectConfig.LOGIN_MAX_TIME,TimeUnit.MINUTES);
        return Msg.success().add("token",token);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.lambdaQuery().eq(User::getUsername, username).last(MybatisPlusConfig.LIMIT_ONE).one();
    }

    @Override
    public boolean removeUserAndRoleByIds(List<Integer> ids) {
        //删除用户角色绑定表
        userRoleService.removeByUserIds(ids);
        return this.removeByIds(ids);
    }
}
