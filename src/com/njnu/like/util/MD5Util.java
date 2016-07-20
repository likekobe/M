package com.njnu.like.util;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {

/**
* 通过MD5加密算法返回加密后的字符串
* 
* @param text 明文（要加密的字符串）
* @return
*/
public static String createMD5(String text) {
MessageDigest md = null;
try {
md = MessageDigest.getInstance("MD5");
} catch (NoSuchAlgorithmException e) {// 理论上不会有这个异常
throw new IllegalStateException("System doesn't support MD5 algorithm.");
}
try {
	md.update(text.getBytes("UTF-8"));
} catch (UnsupportedEncodingException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
byte b[] = md.digest();
StringBuffer buf = new StringBuffer("");
for (int offset = 0; offset < b.length; offset++) {
int i = b[offset];
if (i < 0) {
i += 256;
}
if (i < 16) {
buf.append("0");// 不足两位，补0
}
buf.append(Integer.toHexString(i));
}
return buf.toString();
}

}
