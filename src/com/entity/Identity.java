package com.entity;

import java.io.Serializable;

/**
 *	用户权限实体类
 */
public class Identity implements Serializable{
	
	private int id;
	private String identityName; //权限名
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIdentityName() {
		return identityName;
	}
	public void setIdentityName(String identityName) {
		this.identityName = identityName;
	}
	
}
