package com.wfmyzyz.user.utils;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author xiong
 */
public class RandomUtils {

    public static String getRandomStr(int startLen,int endLen){
        return RandomStringUtils.randomAscii(startLen,endLen);
    }

}
