package com.ellychou.todo.rest.util;

import java.security.MessageDigest;

/**
 * Encrypt password
 * @author szhou
 * @version 1.0.1
 * @since 2015/3/3
 */
public class EncryptionKit {

    public static String md5Encrypt(String srcStr) {
        return encrypt("MD5", srcStr);
    }

    public static String sha1Encrypt(String srcStr) {
        return encrypt("SHA-1", srcStr);
    }

    public static String sha256Encrypt(String srcStr) {
        return encrypt("SHA-256", srcStr);
    }

    public static String sha384Encrypt(String srcStr) {
        return encrypt("SHA-384", srcStr);
    }

    public static String sha512Encrypt(String srcStr) {
        return encrypt("SHA-512", srcStr);
    }

    public static String encrypt(String algorithm, String srcStr) {
        try {
            StringBuilder result = new StringBuilder();
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xFF);
                if (hex.length() == 1)
                    result.append("0");
                result.append(hex);
            }
            return result.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
