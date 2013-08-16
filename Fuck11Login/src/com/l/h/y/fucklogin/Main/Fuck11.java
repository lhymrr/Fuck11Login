package com.l.h.y.fucklogin.Main;

import com.l.h.y.fucklogin.Grade.Gun;
import com.l.h.y.fucklogin.Utils.Contances;


public class Fuck11 {

//	static int x = 0;
//	public static int min = 4;
//	static int max = 16;
//	static int[] a = { 1, 2, 3, 4, 5 };

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for(int i=Contances.pwdLengthMin; i<=Contances.pwdLengthMax; i++){
			Gun gun = new Gun();
			gun.setColumSize(i);
			gun.start();
		}
		
//		System.out.println("<script>alert('账号或密码错误');</script></form>");
//		Properties properties = System.getProperties();
//
//		System.setProperty("file.encoding", "UTF-8");
//		System.out.println("current = " + properties.getProperty("file.encoding"));
	}

}
