package com.islamozcelik.imkbapp.cipher;

import android.util.Base64;

import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CipherClass {

    String plainText;

    public CipherClass(byte[] key, byte[] iv, String plainText) {

        this.plainText = plainText;
    }
    public CipherClass(){

    }

    public byte[] encrypt(String plainText, byte[] key, byte[] iv) throws Exception{

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, paramSpec);

        byte[] cipherText = cipher.doFinal(plainText.getBytes());
        return cipherText;
    }



    public String decyrpt(String plainText, byte[] key, byte[] iv) throws Exception{
        byte[] pt = Base64.decode(plainText,Base64.DEFAULT);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");

        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, paramSpec);

        byte[] decryptedText = cipher.doFinal(pt);
        return new String(decryptedText);
    }

}
