package com.app.beta.glam.fiesta.cmn;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class AuthUtil {
	
	private static final Logger logger = Logger.getLogger(AuthUtil.class.getCanonicalName());
	
	private static int iterations = 1000;
	private static int keysize = (48 * 8);

	public static String generateAuthPsswd(String psswdstr, String saltstr) throws NoSuchAlgorithmException, InvalidKeySpecException {
		char[] chars = psswdstr.toCharArray();
		byte[] salt = saltstr.getBytes();

		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, keysize);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return toHex(hash);
	}

	public static String getGenrtdRndmAuthSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		logger.info("**** salt generated - " + salt.toString());
		return salt.toString();
	}
	
	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }
	
	public static String generateResetPwdKey() {
		String rndmstr = "";
		SecureRandom random = new SecureRandom();
		rndmstr = new BigInteger(130, random).toString(32);
		logger.info("**** reset password key generated - " + rndmstr);
		return rndmstr;
	}

}
