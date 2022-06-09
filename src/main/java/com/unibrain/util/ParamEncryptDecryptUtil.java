package com.unibrain.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class ParamEncryptDecryptUtil {

	public String aesEncrypt(String content, String encryptKey) {
		try {

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));
			byte[] encryptedBytes = cipher.doFinal(content.getBytes("utf-8"));

			String encryptedValue = Base64.encodeBase64String(encryptedBytes);

			return encryptedValue;
		} catch (Exception e) {

		}
		return null;
	}

	public String aesDecrypt(String encryptedValue, String decryptKey) {
		try {
			byte[] encryptBytes = encryptedValue.isEmpty() ? null : Base64.decodeBase64(encryptedValue);

			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
			byte[] decryptBytes = cipher.doFinal(encryptBytes);
			return new String(decryptBytes);

		} catch (Exception e) {

		}
		return null;
	}
}