package com.chapter.common;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 *
 *  <a href="https://www.cnblogs.com/sweeeper/p/8462077.html">参考资料</a>
 *
 * @author zhengshijun
 * @version created on 2019/4/12.
 */
public class Base64Test {

	public static void main(String[] args) {

		String str = "Ow!";

		System.out.println(str.getBytes()[1]);
		System.out.println(Integer.toBinaryString(str.getBytes()[0]));
		System.out.println(Integer.toBinaryString(str.getBytes()[1]));
		System.out.println(Integer.toBinaryString(str.getBytes()[2]));

		byte[] bytes = {toByte("010011"),toByte("110111"),toByte("011100"),toByte("100001")};

		// bytes 到base64码表找到对应的值就是 T3ch

		System.out.println(Integer.parseInt("010011",2));
		// 01001111 01110111 00100001
		// 010011 110111 011100 100001


		System.out.println(Base64.getEncoder().encodeToString("Ow!".getBytes()));

	}

	public static byte toByte(String str){

		// 十进制转二进制
		Integer value = Integer.valueOf("010011",2);
		return value.byteValue();
	}


}
