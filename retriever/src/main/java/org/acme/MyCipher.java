package org.acme;

import java.util.Base64;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

/**
 * Faz uma criptografia muito fraca, simplesmente um base64 
 */
@ApplicationScoped
@Named("myCipher")
public class MyCipher {


    public String encrypt(String message) throws Exception {
        return Base64.getEncoder().encodeToString(message.getBytes("UTF8"));
    }

    public String decrypt(String message) throws Exception {
        byte[] messageBytes = Base64.getDecoder().decode(message.getBytes("UTF8"));
        return new String(messageBytes, "UTF8");
    }
}