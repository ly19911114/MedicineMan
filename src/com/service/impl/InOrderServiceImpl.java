package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.InOrderDao;
import com.entity.InOrder;
import com.service.InOrderService;

@Service("/inOrderService")
public class InOrderServiceImpl implements InOrderService{

	@Autowired
	private InOrderDao inOrderDao;
	
	@Override
	public InOrder findById(Integer id) {
		// TODO Auto-generated method stub
		return inOrderDao.findById(id);
	}

	@Override
	public List<InOrder> Nlist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return inOrderDao.Nlist(map);
	}

	@Override
	public List<InOrder> Ylist(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return inOrderDao.Ylist(map);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return inOrderDao.delete(id);
	}

	@Override
	public int add(InOrder order) {
		// TODO Auto-generated method stub
		return inOrderDao.add(order);
	}

	@Override
	public int getNCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return inOrderDao.getNCount(map);
	}

	@Override
	public int getYCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return inOrderDao.getYCount(map);
	}

	@Override
	public int update(InOrder order) {
		// TODO Auto-generated method stub
		return inOrderDao.update(order);
	}

}
