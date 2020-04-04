package com;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ToolKit {
    public static String getSHA256(String message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            return toHexString(messageDigest.digest(message.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String toHexString(byte[] bytes) {
        return new BigInteger(1, bytes).toString(16).toUpperCase();
    }
}
