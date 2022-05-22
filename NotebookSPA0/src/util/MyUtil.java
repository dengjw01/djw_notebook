package util;

import java.io.UnsupportedEncodingException;

public class MyUtil {

	public MyUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String bytes2string(String parameter) {
		String s = "";
		if (parameter != null && parameter.length() > 0) {
			try {
				s = new String(parameter.getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				s = "";
			}
		}
		return s;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
