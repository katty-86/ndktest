package com.example.kaky.ndktest;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import android.util.Log;

/**
 * Created by kaky on 29.01.16.
 */


public class DES {

    public String genString(int size){
        char[]charTab= new char[size];
        String alphabet= "abcdefghijklmnopqrstuvwxyz123456789!@#$%^&*";
        int stringSize= alphabet.length();
        String result= new String();
        for(int i=0; i<size; i++){
            Random randomGenerator = new Random();
            charTab[i]=alphabet.charAt(randomGenerator.nextInt(stringSize));
        }
        return new String(charTab);
    }


    public String DESencryptDecrypt(String  s){
        KeyGenerator keygenerator = null;
        try {
            keygenerator = KeyGenerator.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        SecretKey myDesKey = keygenerator.generateKey();

        Cipher desCipher = null;

        // Create the cipher
        try {
            desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        // Initialize the cipher for encryption
        try {
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        //sensitive information
        byte[] text = s.getBytes();

     //   Log.v("des", "Text [Byte Format] : " + text);
     //   Log.v("des", "Text : " + new String(text));


        // Encrypt the text
        byte[] textEncrypted = new byte[0];
        try {
            textEncrypted = desCipher.doFinal(text);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

    //    Log.v("des", "Text Encryted : " + textEncrypted);
        String result =new String(textEncrypted);

        // Initialize the same cipher for decryption
        try {
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        // Decrypt the text
        byte[] textDecrypted = new byte[0];
        try {
            textDecrypted = desCipher.doFinal(textEncrypted);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
     //   Log.v("des", "Text Decryted : " + new String(textDecrypted));

        return result;
    }


}
