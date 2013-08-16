package com.l.h.y.fucklogin.Grade;

import com.l.h.y.fucklogin.Utils.Utils;

/**
 * 等级1 数字
 * 
 * @author liuheyuan
 * 
 */
public class Grade {

	private static final char[] G_Number = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static final char[] G_abc = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	private static final char[] G_ABC = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static final char[] G_other = new char[] { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '~', ',', '.', '/', '\\' };
	
	public static final char [] Grade1 = G_Number;
	
	public static final char [] Grade2 = G_abc;
	
	/**
	 * a,b,c,1,2,3
	 */
	public static final char [] Grade3 = Utils.addAllArrays(G_Number, G_abc);
	
	/**
	 * a,b,c,A,B,C,1,2,3
	 */
	public static final char [] Grade4 = Utils.addAllArrays(Grade3, G_ABC);
	
	/**
	 * a,b,c,A,B,C,1,2,3,!,@,#
	 */
	public static final char [] Grade5 = Utils.addAllArrays(Grade4, G_other);
	
}
