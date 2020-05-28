package com.example.encryptionanddecrytionaes.Function;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {
    public static String decrypt(byte[] cipherText, SecretKey key,byte[] IV) {
        try {
            Cipher cipher=Cipher.getInstance("AES");
            SecretKeySpec keySpec=new SecretKeySpec(key.getEncoded(),"AES");
            IvParameterSpec ivParameterSpec=new IvParameterSpec(IV) ;
            cipher.init(Cipher.DECRYPT_MODE,keySpec,ivParameterSpec);
            byte[] decryptedText=cipher.doFinal(cipherText);
            return new String(decryptedText);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
