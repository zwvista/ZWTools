package com.zwstudio.tools.models;

public class CsvUtilities {
	public static final String error = "★★★★★";
	
	public static String csvField(String s, int sz) {
		int len = 0;
		for(int i = 0; i < s.length(); i++) {
			int cp = s.codePointAt(i);
			if (0x0<=cp && cp <= 0x7F)
				len++; /* ASCII文字 */
			else if (0xFF61<=cp && cp<=0xFF9F)
				len++; /* 半角カナ */
			else len += 2;
		}

		int sz2 = sz - len + s.length();
		String fmt = String.format("'%%-%ds'", sz2);
		return (len > sz ? error : "") + String.format(fmt, s);
	}

	public static String trimSpace(String orgStr) {
	    char[] value = orgStr.toCharArray();
	    int len = value.length;
	    int st = 0;
	    char[] val = value;
	    
	    while ((st < len) && (val[st] <= ' ' || val[st] == '　')) {
	        st++;
	    }
	    while ((st < len) && (val[len - 1] <= ' ' || val[len - 1] == '　')) {
	        len--;
	    }
	    
	    return ((st>0) || (len<value.length)) ? orgStr.substring(st,len):orgStr;
	}
}
