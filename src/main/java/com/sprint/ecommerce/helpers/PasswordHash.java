package com.sprint.ecommerce.helpers;

public class PasswordHash {

	public PasswordHash() {
	}

	public String encrypt(String password) {
		StringBuilder sb = new StringBuilder(password);
		return sb.reverse().toString();
	}

	public String decrypt(String encryptedString) {
		StringBuilder sb = new StringBuilder(encryptedString);
		return sb.reverse().toString();
	}
}
