package auction.ui.registration;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.logging.log4j.Logger;

import auction.ui.log.LogFactory;


public class MD5 {
	
	private static final Logger LOGGRER = LogFactory.getLogger(MD5.class);
	
	public static String encryptPassword(String password){
        String generatedPassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] bytes = messageDigest.digest();
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
            	builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = builder.toString();
        } catch (NoSuchAlgorithmException e) {
			 LOGGRER.error("Is not satisfied encryptPassword={}, reason={}", e, e.getMessage());
        }
        return generatedPassword;
	}
	
	

}
