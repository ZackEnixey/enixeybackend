package com.project.webapp.util;

import java.util.UUID;

public class TokenUtil {

	public static String generateToken() {
		return UUID.randomUUID().toString().replace("-", "").substring(7);
	}
	
	public static String generateTemporaryPassword() {
		return UUID.randomUUID().toString().replace("-", "").substring(20);
	}
}
