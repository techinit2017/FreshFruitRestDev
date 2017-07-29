package com.ffp.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 Password encryption algorithm MD5 is a one-way hashing. This means that
 * once hashed, you cannot recover the original value. This is good security,
 * but maybe your approach can be improved.
 *
 */
public class Encryption {
	private static final String ALGO = "MD5";
	/**
	 * salt machenism added to add additional security to password
	 */
	private static final String SALT = "FRESHFRUIT@2017";

	/**
	 * Instead of "decrypting" the existing password, you can hash the password
	 * that has been entered, and compare this hash with the existing password
	 * hash. If they are the same, then the password is the same, and you can
	 * authorize the login attempt.
	 * 
	 * @param input
	 *            {@link String}
	 * @return {@link String} hashed password
	 */
	public static String encrypt(String input) {
		String md5 = null;
		if (null == input)
			return null;
		try {
			input = input + SALT;
			// Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance(ALGO);

			// Update input string in message digest
			digest.update(input.getBytes(), 0, input.length());

			// Converts message digest value in base 16 (hex)
			md5 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return md5;
	}

	public static void main(String[] args) {
		String password = "harsh";
		String encryptedPassword = Encryption.encrypt(password);
		String againEncruptedPassword = Encryption.encrypt(password);
		if (encryptedPassword.equals(againEncruptedPassword)) {
			System.out.println(encryptedPassword);
			System.out.println(againEncruptedPassword);
		}
	}
}
