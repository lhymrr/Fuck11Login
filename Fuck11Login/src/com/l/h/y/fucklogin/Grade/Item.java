package com.l.h.y.fucklogin.Grade;


public class Item {
	/**
	 * 当前计数器
	 */
	private int i;
	
	/**
	 * 当前ID，下标值
	 */
	private int id;
	
	private IsMaxListener maxListener;
	
	/**
	 * 当前值加1
	 */
	public void add(int pwdLength){
		i++;
		if(i >= pwdLength){
			i = 0;
			if(null != maxListener){
				maxListener.maxListener(id);
			}
			
		}
	}
	
	/**
	 * 获得当前值
	 * @return
	 */
	public int get(){
		return i;
	}
	
	public void setMaxListener(IsMaxListener isMaxListener) {
		this.maxListener = isMaxListener;
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * 是否最大值监听器
	 * @author liuheyuan
	 *
	 */
	public interface IsMaxListener{
		public abstract void maxListener(int id);
	}
}
