package com.wfmyzyz.user.utils;

import com.wfmyzyz.user.config.ProjectConfig;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 */
@Component
public class UserUtils {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取解密后的密码
     * @param encryptionPassword
     * @return
     */
    public String decryptPassword(String encryptionPassword){
        HashMap keyMap = redisUtils.getValue(ProjectConfig.PUBLIC_PREFIX, HashMap.class);
        if (keyMap == null){
            return null;
        }
        String privateKey = keyMap.get(RsaUtils.PRIVATE_KEY).toString();
        String decrypt = null;
        try {
            decrypt = RsaUtils.decrypt(encryptionPassword, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decrypt;
    }

    /**
     * 获取md5加密后的密码
     * @param password
     * @param salt
     * @return
     */
    public String getMd5StrByPasswordAndSalt(String password,String salt){
        return Md5Utils.getMd5Str(password+salt);
    }

    /**
     * 获取用户盐值
     * @return
     */
    public String getUserSalt(){
        return RandomUtils.getRandomStr(ProjectConfig.USER_PASSWORD_START_LEN,ProjectConfig.USER_PASSWORD_END_LEN);
    }

    /**
     * 获取rsa公钥
     * @return
     */
    public String getPublicKeyRsa(){
        Map<String, String> rsaMap = RsaUtils.genKeyPair();
        String publicKey = rsaMap.get(RsaUtils.PUBLIC_KEY);
        String privateKey = rsaMap.get(RsaUtils.PRIVATE_KEY);
        if (org.apache.commons.lang3.StringUtils.isBlank(publicKey) || StringUtils.isBlank(privateKey)){
            return null;
        }
        redisUtils.addToRedis(ProjectConfig.PUBLIC_PREFIX,rsaMap,ProjectConfig.PUBLIC_PRIVATE_KEY_LONG_TIME, TimeUnit.DAYS);
        return publicKey;
    }

    /**
     * 获取公钥
     * @return
     */
    public String getPublicKey(){
        String publicKey;
        HashMap keyMap = redisUtils.getValue(ProjectConfig.PUBLIC_PREFIX, HashMap.class);
        if (keyMap == null){
            publicKey = getPublicKeyRsa();
        }else {
            publicKey = keyMap.get(RsaUtils.PUBLIC_KEY).toString();
        }
        return publicKey;
    }
}
