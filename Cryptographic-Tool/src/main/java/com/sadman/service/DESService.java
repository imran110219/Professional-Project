package com.sadman.service;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @author Sadman
 */
public class DESService {
    public static String encrypt(String strToEncrypt, String secret)
    {
        try
        {
            byte[] decodedKey = Base64.getDecoder().decode(secret);
            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret)
    {
        try
        {
            byte[] decodedKey = Base64.getDecoder().decode(secret);
            SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
