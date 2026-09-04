package com.spotify.utility;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {

	static String Key = "1234567890123456";

	public static String encryptData(String data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			IllegalBlockSizeException, BadPaddingException

	{
		Cipher c = Cipher.getInstance("AES");

		c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Key.getBytes(), "AES"));

		return Base64.getEncoder().encodeToString(c.doFinal(data.getBytes()));

	}

	public static String decryptData(String data) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException

	{
		Cipher c = Cipher.getInstance("AES");

		c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Key.getBytes(), "AES"));

		return new String(c.doFinal(Base64.getDecoder().decode(data)));

	}
	


}
