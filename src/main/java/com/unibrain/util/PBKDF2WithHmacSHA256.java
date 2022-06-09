package com.unibrain.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Component;

@Component
public class PBKDF2WithHmacSHA256 {
	public static final int iterations = 1000;

	public String SHA256SecurePassword(String message) {
		char[] chars = message.toCharArray();
		byte[] salt = "SALT".getBytes();
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
		SecretKeyFactory skf = null;
		try {
			skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = null;
		String generatedHash = null;
		try {
			hash = skf.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		try {
			generatedHash = toHex(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedHash;
	}

	private static String toHex(byte[] array) throws NoSuchAlgorithmException {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0) {
			return String.format("%0" + paddingLength + "d", 0) + hex;
		} else {
			return hex;
		}
	}

	public boolean validatePassword(String currentPassword, String storedPassword) {
		final String saltValue = "53414c54";
		byte[] salt;
		int diff = 0;
		try {
			salt = fromHex(saltValue.trim());

			byte[] storedPasswordHash = fromHex(storedPassword.trim());
			PBEKeySpec spec = new PBEKeySpec(currentPassword.toCharArray(), salt, iterations,
					storedPasswordHash.length * 8);
			SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			byte[] currentPasswordHash = skf.generateSecret(spec).getEncoded();
			diff = storedPasswordHash.length ^ currentPasswordHash.length;
			for (int i = 0; i < storedPasswordHash.length && i < currentPasswordHash.length; i++) {
				diff |= storedPasswordHash[i] ^ currentPasswordHash[i];
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return diff == 0;
	}

	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException {
		byte[] bytes = new byte[hex.length() / 2];
		for (int i = 0; i < bytes.length; i++) {
			bytes[i] = (byte) Integer.parseInt(hex.trim().substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}

	public String SHA256(String message) {
		String password = null;
		try {

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(message.getBytes());
			byte[] bytes = md.digest();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			password = sb.toString();
		}

		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}
}
