package com.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.SellOrder;

@Repository("/sellOrderDao")
public interface SellOrderDao {

	/**
	 * 通过id查找售药订单
	 * @param id
	 * @return
	 */
	public SellOrder findById(Integer id);
	
	/**
	 * 生成售药订单
	 * @param order
	 * @return
	 */
	public int add(SellOrder order);
	
	/**
	 * 删除售药订单
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 更新售药订单
	 */
	public int update(SellOrder order);
	
	/**
	 * 分页查找未结账的订单
	 * @param map
	 * @return
	 */
	public List<SellOrder> Nlist(Map<String,Object> map);
	
	/**
	 * 分页查找已结账的订单
	 * @param map
	 * @return
	 */
	public List<SellOrder> Ylist(Map<String,Object> map);
	
	/**
	 * 获取所有未结账订单的数量
	 * @return
	 */
	public int getNCount(Map<String,Object> map);
	
	/**
	 * 获取所有已结账订单的数量
	 * @return
	 */
	public int getYCount(Map<String,Object> map);
}
