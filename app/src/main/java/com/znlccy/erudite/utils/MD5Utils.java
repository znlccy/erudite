package com.znlccy.erudite.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
  * Author: znlccy
  * Date: 2019-10-08 16:22
  * Version: 1.0.0
  * Comment: MD5加密算法
  */
public class MD5Utils {

    /**
     * MD5加密算法
     * @param text
     * @return
     */
    public static String md5(String text) {
        MessageDigest digest = null;
        try {
            digest= MessageDigest.getInstance("md5");
            byte[] result = digest.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b: result) {
                int number = b & 0xff;
                String hex = Integer.toHexString(number);
                if (hex.length() == 1) {
                    sb.append("0" + hex);
                } else {
                    sb.append(hex);
                }
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
