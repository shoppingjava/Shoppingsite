/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encrypt {
    
    /*
     *  Krypterar lösenord
     */
    public static String encryptPassw(String s) {
        byte[] defaultBytes = s.getBytes();
        try {
            //Skapar alorithm (MD5)
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            //Array som lagrar tecken
            byte messageDigest[] = algorithm.digest();

            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < messageDigest.length; i++) {
                //Lagrar tecken i sträng
                String hex = Integer.toHexString(0xFF & messageDigest[i]);
                //Körs om sträng enbart består av ett tecken
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                //Lägger till tecken
                hexString.append(hex);
            }
            //Returnerar krypterat lösenord
            return hexString.toString();

        } catch (NoSuchAlgorithmException nsae) {
            return null;
        }
    }
}
