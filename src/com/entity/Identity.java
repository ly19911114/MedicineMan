package com.entity;

import java.io.Serializable;

/**
 *	�û�Ȩ��ʵ����
 */
public class Identity implements Serializable{
	
	private int id;
	private String identityName; //Ȩ����
	
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
