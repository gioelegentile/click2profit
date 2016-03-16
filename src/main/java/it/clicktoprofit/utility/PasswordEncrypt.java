package it.clicktoprofit.utility;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Gioele on 30/12/2015.
 */
public class PasswordEncrypt {

    /**
     * Encrypt.
     *
     * @param message the message
     * @return messaggio criptato
     */
    public static String encrypt(String message) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(message.getBytes());
            return String.format("%032x", new BigInteger(1, m.digest()));
        } catch (Exception e) {
            return null;
        }
    }

}
