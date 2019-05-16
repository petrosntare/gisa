/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisainvestment.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class PasswordEncrypter {

    private static Logger LOGGER = Logger.getLogger(PasswordEncrypter.class.getName());

    public static String securePassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            //Create MessageDigest instance for MD5
            //MessageDigest md = MessageDigest.getInstance("MD5");
            // Create MessageDigest instance for SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            //Add password bytes to digest
            String saltKey = "itdc@SYS=2";
            byte[] salt = saltKey.getBytes();
            md.update(salt);
            //get a random salt key
            // md.update(getSalt());
            //Get the hash's bytes 
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.toString());
        }
        return generatedPassword;
    }

//      //Add salt
//    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
////        String saltKey = "itdc@SYS=2";
//        //Always use a SecureRandom generator
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
//        //Create array for salt
//        byte[] salt = new byte[16];
////        byte[] salt = saltKey.getBytes();
//        //Get a random salt
//        sr.nextBytes(salt);
//        //return salt
//        return salt;
//    }

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public static void setLOGGER(Logger LOGGER) {
        PasswordEncrypter.LOGGER = LOGGER;
    }
}
