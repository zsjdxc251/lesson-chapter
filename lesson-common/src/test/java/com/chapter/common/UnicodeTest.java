package com.chapter.common;

import com.example.common.lang.StringTools;

/**
 * @author zhengshijun
 * @version created on 2019/5/5.
 */
public class UnicodeTest {


	/**
	 * 字符串编码成Unicode编码
	 */
	public static String encode(String src) throws Exception {
		char c;
		StringBuilder str = new StringBuilder();
		int intAsc;
		String strHex;
		for (int i = 0; i < src.length(); i++) {
			c = src.charAt(i);
			intAsc = (int) c;
			strHex = Integer.toHexString(intAsc);
			if (intAsc > 128)
				str.append("\\u" + strHex);
			else
				str.append("\\u00" + strHex); // 低位在前面补00
		}
		return str.toString();
	}

	/**
	 * Unicode解码成字符串
	 * @param src
	 * @return
	 */
	public static String decode(String src) {
		int t =  src.length() / 6;
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < t; i++) {
			String s = src.substring(i * 6, (i + 1) * 6); // 每6位描述一个字节
			// 高位需要补上00再转
			String s1 = s.substring(2, 4) + "00";
			// 低位直接转
			String s2 = s.substring(4);
			// 将16进制的string转为int
			int n = Integer.valueOf(s1, 16) + Integer.valueOf(s2, 16);
			// 将int转换为字符
			char[] chars = Character.toChars(n);
			str.append(new String(chars));
		}
		return str.toString();
	}

	public static void main(String[] args) throws Exception {

		System.out.println(encode("中"));
		System.out.println(StringTools.charToUnicode("中"));


		System.out.println(decode("\\u0061"));
		System.out.println(StringTools.unicodeToChar("\\u61"));
	}
}
