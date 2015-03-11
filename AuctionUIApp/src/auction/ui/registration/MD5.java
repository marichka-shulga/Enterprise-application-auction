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
            MessageDigest md = MessageDigest.getInstance("MD5");// Create MessageDigest instance for MD5
            md.update(password.getBytes());//Add password bytes to digest
            byte[] bytes = md.digest();//Get the hash's bytes
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for( int i=0; i< bytes.length; i++ )
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString(); //Get complete hashed password in hex format
        }
        catch (NoSuchAlgorithmException e)
        {
			 LOGGRER.error("Is not satisfied encryptPassword={}, reason={}", e, e.getMessage());
        }
        return generatedPassword;
	}
	
	

}
