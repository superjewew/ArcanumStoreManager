package com.arcanum.arcanumstoremanager.utils;

import org.spongycastle.crypto.PBEParametersGenerator;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.encoders.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by norman on 22/02/18.
 */

public class EncryptUtils {

    private static SecureRandom random = new SecureRandom();

    private static int keyLength = 256;
    private static int saltLength = keyLength / 8;

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    public static SecretKey generateKeyFromPassPhrase(String phrase, byte[] salt) throws UnsupportedEncodingException {
        PKCS5S2ParametersGenerator gen = new PKCS5S2ParametersGenerator(new SHA256Digest());
        gen.init(PBEParametersGenerator.PKCS5PasswordToUTF8Bytes(phrase.toCharArray()), salt, 4096);
        byte[] dk = ((KeyParameter) gen.generateDerivedParameters(keyLength)).getKey();
        return new SecretKeySpec(dk, "AES");
    }

    public static String encrypt(String string) {
        byte[] salt = createSalt(saltLength);
        byte[] input = getStringBytes(string);
        Cipher cipher = initCipher();
        if (cipher != null) {
            byte[] iv = initIv(cipher);
            IvParameterSpec ivParams = new IvParameterSpec(iv);
            String saltAsBase64String = Base64.toBase64String(salt);
            String ivAsBase64String = Base64.toBase64String(iv);
            try {
                cipher.init(Cipher.ENCRYPT_MODE, generateKeyFromPassPhrase(string, salt), ivParams);
                byte[] cipherText = cipher.doFinal(input);
                String cipherAsBase64String = Base64.toBase64String(cipherText);
                return saltAsBase64String + "]" + ivAsBase64String + "]" + cipherAsBase64String;
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (InvalidAlgorithmParameterException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
        }

        return string;
    }

    private static byte[] createSalt(int size) {
        byte[] salt = new byte[size];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] getStringBytes(String string) {
        byte[] res = new byte[0];
        try {
            res = string.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static Cipher initCipher() {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return cipher;
    }

    private static byte[] initIv(Cipher cipher) {
        byte[] iv = new byte[cipher.getBlockSize()];
        random.nextBytes(iv);
        return iv;
    }

    public static boolean validatePassword(String password, String cipherText) {
        String[] fields = cipherText.split("]");
        byte[] salt = Base64.decode(fields[0]);
        byte[] iv = Base64.decode(fields[1]);
        byte[] cipherBytes = Base64.decode(fields[2]);

        Cipher cipher = initCipher();
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        try {
            if (cipher != null) {
                cipher.init(Cipher.DECRYPT_MODE, generateKeyFromPassPhrase(password, salt), ivParams);
                byte[] plaintext = cipher.doFinal(cipherBytes);
                String plainStr = new String(plaintext , "UTF-8");
                return password.equals(plainStr);
            }
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }

}
