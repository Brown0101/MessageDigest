package com.dontasweb;

//Necessary imports
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.MessageDigest;

public class SaltExample {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {

        // A static String for the example
        String passwordToHash = "myPassword";

        // Create a salt
        byte[] salt = createSalt();

        // Create a hash
        String securePassword = getSecurePassword(passwordToHash, salt);
        System.out.println(securePassword);
    }

    // Method to generate a Salt
    public static byte[] createSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];;
        secureRandom.nextBytes(salt);

        return salt;
    }

    // Method to generate the hash.
    // It takes a password and the Salt as input arguments
    private static String getSecurePassword(String passwordToHash, byte[] salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
//            System.out.println(md.hashCode()); // comparing hash values
//            System.out.println(passwordToHash.hashCode()); // comparing hash values
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
