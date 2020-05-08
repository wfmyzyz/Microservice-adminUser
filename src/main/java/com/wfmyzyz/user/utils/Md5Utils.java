package com.wfmyzyz.user.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author admin
 */
public class Md5Utils {

    public static String getMd5Str(String str){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = str.getBytes();
        md5.reset();
        byte[] digest = md5.digest(bytes);
        StringBuilder hexValue = new StringBuilder();
        for (int i=0;i<digest.length;i++){
            String hexString = Integer.toHexString(digest[i] & 0xff);
            if (hexString.length() == 1){
                hexValue.append("0");
            }
            hexValue.append(hexString);
        }
        return hexValue.toString();
    }

}
