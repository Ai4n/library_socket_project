package Main;

import java.security.MessageDigest;

public class PasswordUtils {
	public static String encodePassword(String userPassword) {
		byte[] encodedhash = null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(userPassword.getBytes());
			encodedhash = digest.digest();
		} catch (Exception e) {
			System.out.println("Oops!");
		}
		return bytesToHex(encodedhash);
	}

	private static String bytesToHex(byte[] hash) {
		StringBuffer hexString = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
