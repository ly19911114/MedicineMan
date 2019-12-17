package com.entity;

import java.util.Date;

/**
 *	出售药品订单实体类
 */
public class SellOrder {
	
	private int id;  //订单编号id
	private String cName;  //药品名
	private double price;  //药品单价
	private int buyNums;   //购买数量
	private double totalMoney;  //总额
	private Date createDate;   //订单生产日期
	private int status;   //订单状态，1表示未支付，2表示已支付
	
	private User user;   //外键，售药人
	private int userId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getBuyNums() {
		return buyNums;
	}
	public void setBuyNums(int buyNums) {
		this.buyNums = buyNums;
	}
	public double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
