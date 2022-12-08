package com.jayus.test.crypt;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : h zk
 * @date : 2022/11/29 13:41
 * @description :
 **/
public class CryptTest {

    public static void main(String[] args) throws Exception {
        /*SecretKeySpec skeSpect = new SecretKeySpec("t9rF=9b9kshn#D+$".getBytes(), "AES");
        AlgorithmParameterSpec iv = new IvParameterSpec("d=mZ19*aPGJT77o1".getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, skeSpect, iv);
        byte[] decrypted = cipher.doFinal("1".getBytes());
        System.out.println(new String(decrypted));*/

        SecretKeySpec skeSpect = new SecretKeySpec("t9rF=9b9kshn#D+$".getBytes(), "AES");
        AlgorithmParameterSpec iv = new IvParameterSpec("d=mZ19*aPGJT77o1".getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeSpect, iv);
        byte[] decrypted = cipher.doFinal("1".getBytes());
        for (byte b : decrypted) {
            System.out.println(b);
        }
        System.out.println(new String(decrypted,StandardCharsets.US_ASCII));

        SecretKeySpec skeSpect1 = new SecretKeySpec("t9rF=9b9kshn#D+$".getBytes(), "AES");
        AlgorithmParameterSpec iv1 = new IvParameterSpec("d=mZ19*aPGJT77o1".getBytes());
        Cipher cipher1 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher1.init(Cipher.DECRYPT_MODE, skeSpect1, iv1);
        byte[] decrypted1 = cipher1.doFinal(decrypted);
        System.out.println(new String(decrypted1));
    }

}
