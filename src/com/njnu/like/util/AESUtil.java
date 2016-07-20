package com.njnu.like.util;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;




/**
* This program generates a AES key, retrieves its raw bytes, and then reinstantiates a AES key from the key bytes. The
* reinstantiated key is used to initialize a AES cipher for encryption and decryption.
*/
public class AESUtil {

private static final String AES = "AES";

private static final String CRYPT_KEY = "YUUAtestYUUAtesw";
/**
* 加密
* 
* @param encryptStr
* @return
*/
public static byte[] encrypt(byte[] src, String key) throws Exception {
	KeyGenerator keygen = KeyGenerator.getInstance("AES");
	keygen.init(256,new SecureRandom(key.getBytes()));


	  SecretKey key1 = keygen.generateKey();
	 
Cipher cipher = Cipher.getInstance(AES);
SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);
cipher.init(Cipher.ENCRYPT_MODE, securekey);// 设置密钥和加密形式
return cipher.doFinal(src);
}

/**
* 解密
* 
* @param decryptStr
* @return
* @throws Exception
*/
public static byte[] decrypt(byte[] src, String key) throws Exception {
Cipher cipher = Cipher.getInstance(AES);
SecretKeySpec securekey = new SecretKeySpec(key.getBytes(), AES);// 设置加密Key
cipher.init(Cipher.DECRYPT_MODE, securekey);// 设置密钥和解密形式
return cipher.doFinal(src);
}

/**
* 二行制转十六进制字符串
* 
* @param b
* @return
*/
public static String byte2hex(byte[] b) {
String hs = "";
String stmp = "";
for (int n = 0; n < b.length; n++) {
stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
if (stmp.length() == 1) hs = hs + "0" + stmp;
else hs = hs + stmp;
}
return hs.toUpperCase();
}

public static byte[] hex2byte(byte[] b) {
if ((b.length % 2) != 0) throw new IllegalArgumentException("长度不是偶数");
byte[] b2 = new byte[b.length / 2];
for (int n = 0; n < b.length; n += 2) {
String item = new String(b, n, 2);
b2[n / 2] = (byte) Integer.parseInt(item, 16);
}
return b2;
}

/**
* 解密
* 
* @param data
* @return
* @throws Exception
*/
public final static String decrypt(String data) {
try {
return new String(decrypt(hex2byte(data.getBytes()), CRYPT_KEY));
} catch (Exception e) {
}
return null;
}

/**
* 加密
* 
* @param data
* @return
* @throws Exception
*/
public final static String encrypt(String data) {
try {
return byte2hex(encrypt(data.getBytes(), CRYPT_KEY));
} catch (Exception e) {
}
return null;
}

//public static void main(String[] args) {
////	  Logger logger = Logger.getLogger(AESUtil.class);
////	File file = new File("./license.txt");
////	if (!file.exists())
////    {
////        logger.error("系统没有授权，无法运行！");
////        System.exit(0);
////    }
////	InputStreamReader isr=null;
////
////	 try {
////		isr = new InputStreamReader(new FileInputStream(file), "UTF-8");
////	} catch (UnsupportedEncodingException e1) {
////		// TODO Auto-generated catch block
////		e1.printStackTrace();
////	} catch (FileNotFoundException e1) {
////		// TODO Auto-generated catch block
////		e1.printStackTrace();
////	}
////	
////	BufferedReader  bufferedReader = null;
////	bufferedReader = new BufferedReader(isr);
////	
////	try {
////	System.out.println(bufferedReader.readLine());	
////	System.out.println(bufferedReader.readLine());	
////	System.out.println(bufferedReader.readLine());	
////	System.out.println(bufferedReader.readLine());	
////	System.out.println(bufferedReader.readLine());	
////	} catch (IOException e) {
////		// TODO Auto-generated catch block
////		e.printStackTrace();
////	}
//String ID = "四川师范大学2012-01-012012-05-0100-E0-81-D6-CF-C3";
//
//ID=MD5Util.createMD5(ID);
//System.out.println(ID);
//String idEncrypt = encrypt(ID);
//System.out.println(idEncrypt);
//String idDecrypt = decrypt(idEncrypt);
//System.out.println(idDecrypt);
//}

}
