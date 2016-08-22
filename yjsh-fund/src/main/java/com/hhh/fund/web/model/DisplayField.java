package com.hhh.fund.web.model;

import java.io.Serializable;

/**
 * 用于显示简单信息的bean
 * @author 3hhjj
*/
public class DisplayField implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1802837191962759879L;

	private String id;
	
	private String name;

	public DisplayField(){}
	
	public DisplayField(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
