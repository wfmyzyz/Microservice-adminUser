package com.wfmyzyz.user.utils;

import com.wfmyzyz.user.config.ProjectConfig;
import com.wfmyzyz.user.user.domain.User;
import com.wfmyzyz.user.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author admin
 */
@Slf4j
@Component
public class TokenUtils {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IUserService userService;

    /**
     * 根据request获取用户ID
     * @param request
     * @return
     */
    public Integer getUserIdByRequest(HttpServletRequest request){
        String token = request.getHeader(ProjectConfig.TOKEN_KEY);
        return getUserIdByToken(token);
    }

    /**
     * 根据request获取用户
     * @param request
     * @return
     */
    public User getUserByRequest(HttpServletRequest request){
        Integer userId = getUserIdByRequest(request);
        return userService.getById(userId);
    }

    /**
     * 根据token获取用户ID
     * @param token
     * @return
     */
    public Integer getUserIdByToken(String token){
        HashMap<String,String> keyMap = redisUtils.getValue(ProjectConfig.PUBLIC_PREFIX, HashMap.class);
        if (keyMap == null){
            return null;
        }
        String decryptToken;
        try {
            decryptToken = RsaUtils.decrypt(token, keyMap.get(RsaUtils.PRIVATE_KEY));
        } catch (Exception e) {
            log.error("token={"+token+"},解密失败");
            return null;
        }
        String base64decodeToken = new String(Base64.decodeBase64(decryptToken));
        String userId = base64decodeToken.substring(base64decodeToken.indexOf("[") + 1, base64decodeToken.lastIndexOf("]"));
        return Integer.parseInt(userId);
    }


    /**
     * 创建token
     * @param userId
     * @return
     */
    public String createToken(Integer userId) {
        String nowDatetime = DatetimeUtils.getNowDatetimeStr();
        String userIdStr = "userId["+userId+"]";
        String tokenStr = nowDatetime + "-" + userIdStr;
        String base64EncodeTokenStr = Base64.encodeBase64String(tokenStr.getBytes());
        HashMap<String,String> keyMap = redisUtils.getValue(ProjectConfig.PUBLIC_PREFIX, HashMap.class);
        if (keyMap == null){
            return null;
        }
        String publicKey = keyMap.get(RsaUtils.PUBLIC_KEY);
        String token = null;
        try {
            token = RsaUtils.encrypt(base64EncodeTokenStr, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }
}
