package com.example.encryptionanddecrytionaes.Function;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Encrypt {
    public static byte[] encrypt(byte[] plaintext, SecretKey key, byte[] IV) throws Exception{
        Cipher cipher=Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec=new SecretKeySpec(key.getEncoded(),"AES");
        IvParameterSpec ivParameterSpec=new IvParameterSpec(IV);

        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);
        byte[] ciphertext=cipher.doFinal(plaintext);
        return ciphertext;
    }
}
