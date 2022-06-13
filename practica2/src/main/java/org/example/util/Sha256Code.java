package org.example.util;

public class Sha256Code {
    public static String sha256(String text){
        return org.apache.commons.codec.digest.DigestUtils.sha256Hex(text);
    }
}
