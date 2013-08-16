package com.l.h.y.fucklogin.Http;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.l.h.y.fucklogin.Utils.TextUtils;


/**
 * 网络请求处理类，java实现
 * */

public final class HttpJavaHandler{
	
	/**
	 * GET 请求
	 * */
	public static final String doGet(String baseUrl, Bundle params) throws IOException {

		URL url;
		String response = null ;
		HttpURLConnection httpConnection = null;
		InputStream is = null ;
		
		try {
			
			baseUrl = createGetUrl(baseUrl , params);
//			LetvHttpLog.Log("GET  " + baseUrl);
			url = new URL(baseUrl);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setReadTimeout(5*1000);
			httpConnection.setConnectTimeout(5*1000);

			httpConnection.connect();
			int responseCode = httpConnection.getResponseCode();

			if (responseCode == HttpStatus.SC_OK) {
				is = httpConnection.getInputStream();
			} else {
					is = httpConnection.getErrorStream();
					response = read(is);
				throw new IOException("responseCode is not 200");
			}
			response = read(is);
			System.out.println("Response-Get = " + response);
			return response ;
		} finally {
			httpConnection.disconnect();
			if(is != null){try {is.close();} catch (Exception e) {e.printStackTrace();}}
			url = null ;
			response = null ;
			httpConnection = null ;
			is = null ;
		}
	}

	
	/**
	 * POST 请求
	 * */
	public static final String doPost(String baseUrl, Bundle params) throws IOException {

		URL url;
		String response = null ;
		HttpURLConnection httpConnection = null;
		InputStream is = null ;
		
		try {
			url = new URL(baseUrl);
			httpConnection = (HttpURLConnection) url.openConnection();

			httpConnection.setRequestMethod("POST");
			httpConnection.setReadTimeout(5*1000);
			httpConnection.setConnectTimeout(5*1000);
			httpConnection.setDoOutput(true);
			httpConnection.connect();
			String p = createPostParamString(params) ;
			httpConnection.getOutputStream().write(p.toString().getBytes());

			int responseCode = httpConnection.getResponseCode();

			if (responseCode == HttpStatus.SC_OK) {
				is = httpConnection.getInputStream();
			} else {
					is = httpConnection.getErrorStream();
					response = read(is);
				throw new IOException("responseCode is not 200");
			}

			response = read(is);
//			System.out.println("Response-Post = " + response);
			return response ;
		} finally {
			httpConnection.disconnect();
			if(is != null){try {is.close();} catch (Exception e) {e.printStackTrace();}}
			url = null ;
			response = null ;
			httpConnection = null ;
			is = null ;
		}
	}
	
	/**
	 * 组建get请求的地址串
	 * */
	private static final String createGetUrl(String baseUrl , Bundle params){
		if(!TextUtils.isEmpty(baseUrl)){
			if(params != null && params.size() > 0){
				StringBuilder sb = new StringBuilder(baseUrl);
				sb.append("?");
				sb.append(encodeUrl(params));
				
				return sb.toString() ;
			}
			return baseUrl ;
		}
		
		return null ;
	}
	
	
	/**
	 * 组建post请求参数串
	 * */
	private static final String createPostParamString(Bundle params){
		StringBuilder sb = encodeUrl(params);
		if(sb == null)
			return null ;
		return sb.toString() ;
	}

	/**
	 * 将Bundle参数组装成字符串
	 * */
	private static final StringBuilder encodeUrl(Bundle parameters) {
		if (parameters == null) {
			return null ;
		}
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String key : parameters.keySet()) {
			if (first) {
				first = false;
			} else {
				sb.append("&");
			}
			String pa = parameters.getString(key);
			if(pa != null){
				sb.append(key + "=" + URLEncoder.encode(pa));
			}else{
				sb.append(key + "=");
			}
		}
		return sb;
	}
	
	/**
	 * 将回来的IO流转化为字符串
	 * */
	private static final String read(InputStream in) throws IOException {
		if(in == null){
			return null ;
		}
		InputStreamReader r = null ;
		String result = null ;
		char[] buf = new char[100] ;
		try{
			StringBuilder sb = new StringBuilder();
			r = new InputStreamReader(in);
			int len = 0 ;
			while ((len = r.read(buf)) != -1) {
				sb.append(buf , 0 , len);
			}
			
			result = sb.toString();
			
			return result ;
		} finally{
			if(r != null){r.close();}
			r = null ;
			result = null ;
			buf = null ;
			in = null ;
		}
	}
}
