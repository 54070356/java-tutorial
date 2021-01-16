/*
 * Copyright (c) 2020 by Emotibot Corporation. All rights reserved.
 * EMOTIBOT CORPORATION CONFIDENTIAL AND TRADE SECRET
 */

package laofuzi.java.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 中文字符工具类
 *
 *
 */
public class ChineseCharacterUtils {
	private static Pattern pattern = Pattern.compile("\\s*|\t*|\r*|\n*");
	
	/**
	 * 判断一个字符是否是乱码。不是很可靠
	 * @param character
	 * @return
	 *
	 */
	public static boolean isMessyCode(char character) {
		String s = String.valueOf(character);
		Matcher m = pattern.matcher(s);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();

		int length = (ch != null) ? ch.length : 0;
		for (int i = 0; i < length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {
				String str = "" + ch[i];
				if (!str.matches("[\u4e00-\u9fa5]+")) {
					return true;
				}
			}
		}
		return false;
	}
}
