package com.wfmyzyz.user.config;

/**
 * @author admin
 */
public class ProjectConfig {
    /**
     * 用户盐值随机生成位数
     */
    public final static Integer USER_PASSWORD_START_LEN = 25;
    public final static Integer USER_PASSWORD_END_LEN = 30;

    /**
     * 前端的
     */
    public final static String TOKEN_KEY = "token";

    /**
     * redis里的
     */
    public final static String TOKEN = "token:";
    public final static String USER = "user:";
    public final static String PUBLIC_PREFIX = "PUBLIC_PRIVATE_KEY";
    public final static Integer PUBLIC_PRIVATE_KEY_LONG_TIME = 7;

    /**
     * response
     */
    public static final String RESPONSE_DATA = "data";

    /**
     * 权限返回
     */
    public static final Integer AUTHORITY_ROOT_ID = 0;

    /**
     * 角色
     */
    public static final Integer ROLE_ROOT_ID = 0;

    /**
     * 登录返回
     */
    public static final long LOGIN_MAX_TIME = 30;
}
