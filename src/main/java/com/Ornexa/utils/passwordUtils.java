package com.Ornexa.utils;

import org.mindrot.jbcrypt.BCrypt;
public class passwordUtils {
	private static final int COST = 10;
	
	public static String getHashPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(COST));
	}
	
	public static boolean checkPassword(String input, String storedHash) {
		return BCrypt.checkpw(input,storedHash);
	}

}
