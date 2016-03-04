package com.myApp.POJO;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Fedir on 03.03.2016.
 */
public class Password {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALGORITHM = "md5";

    private Password() { }
    public static String generateSalt (){
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return new String(salt);

    }
    private static String Hash(String message) {
        String hash = "";
        if(null == message)
            return null;

        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM);
            digest.update(message.getBytes(), 0, message.length());
            hash = new BigInteger(1, digest.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    public static String getHashedPassword(String password, String salt){
        return Hash(Hash(password)+salt);
    }

    public static boolean checkPassword (String password, String salt, String expectedPassword){
        return getHashedPassword(password,salt).equals(expectedPassword);
    }

}