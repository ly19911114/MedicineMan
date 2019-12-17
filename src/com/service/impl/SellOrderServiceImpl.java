package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.SellOrderDao;
import com.entity.SellOrder;
import com.service.SellOrderService;

@Service("sellOrderService")
public class SellOrderServiceImpl implements SellOrderService{

	@Autowired
	private SellOrderDao sellOrderDao;
	
	@Override
	public SellOrder findById(Integer id) {
		// TODO Auto-generated method stub
		return sellOrderDao.findById(id);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return sellOrderDao.delete(id);
	}

	@Override
	public int update(SellOrder order) {
		// TODO Auto-generated method stub
		return sellOrderDao.update(order);
	}

	@Override
	public List<SellOrder> Nlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sellOrderDao.Nlist(map);
	}

	@Override
	public List<SellOrder> Ylist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sellOrderDao.Ylist(map);
	}

	@Override
	public int getNCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sellOrderDao.getNCount(map);
	}

	@Override
	public int getYCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sellOrderDao.getYCount(map);
	}

	@Override
	public int add(SellOrder order) {
		// TODO Auto-generated method stub
		return sellOrderDao.add(order);
	}
}
