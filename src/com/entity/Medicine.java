package com.entity;

import java.util.Date;

/**
 * 药品实体类
 */
public class Medicine {
	
	private int id; 			  //药品编码
	private String cName;         //中文名称
	private String eName;		  //英文名称
	private double price;		  //价格
	private int nums;			  //库存数量
	private String manufacturer;  //生产商
	private String describle;     //药效描述
	private Date productDate;     //生产日期
	private String safeDate;      //保质期
	private String standard;      //规格
	private double oldPrice;      //进价
	
	private MedicineType type;    //药品类别，外键
	private int typeId;
	
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
	public String geteName() {
		return eName;
	}
	public void seteName(String eName) {
		this.eName = eName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getNums() {
		return nums;
	}
	public void setNums(int nums) {
		this.nums = nums;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getDescrible() {
		return describle;
	}
	public void setDescrible(String describle) {
		this.describle = describle;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public String getSafeDate() {
		return safeDate;
	}
	public void setSafeDate(String safeDate) {
		this.safeDate = safeDate;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	public MedicineType getType() {
		return type;
	}
	public void setType(MedicineType type) {
		this.type = type;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public double getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}
}
