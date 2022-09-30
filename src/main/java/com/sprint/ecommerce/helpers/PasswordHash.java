package com.sprint.ecommerce.helpers;

public class PasswordHash {

	public PasswordHash() {
	}

	public String encrypt(String unencryptedString) {
		return unencryptedString + "madhur";
	}

	public String decrypt(String encryptedString) {
		return encryptedString.substring(0, encryptedString.length() - 6);
	}
}
