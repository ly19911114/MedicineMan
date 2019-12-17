package com.entity;

/**
 * 订单状态实体类
 */
public class OrderStatus {
	
	private int id;
	private String statusName;  //订单状态，是否通过系统管理员的批准
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
