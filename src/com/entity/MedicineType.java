package com.entity;

import java.io.Serializable;

/**
 * 药品类别实体类
 */
public class MedicineType implements Serializable{
	
	private int id;            //类别编码
	private String cTypeName;  //中文类别名
	private String eTypeName;  //英文类别名
	private String typeDesc;   //类别描述
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getcTypeName() {
		return cTypeName;
	}
	public void setcTypeName(String cTypeName) {
		this.cTypeName = cTypeName;
	}
	public String geteTypeName() {
		return eTypeName;
	}
	public void seteTypeName(String eTypeName) {
		this.eTypeName = eTypeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	
}
