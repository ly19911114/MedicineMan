package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.IdentityDao;
import com.entity.Identity;
import com.service.IdentityService;

@Service("identityService")
public class IdentityServiceImpl implements IdentityService{

	@Autowired
	private IdentityDao identityDao;
	
	@Override
	public List<Identity> list() {
		// TODO Auto-generated method stub
		return identityDao.list();
	}

	@Override
	public Identity findById(int id) {
		// TODO Auto-generated method stub
		return identityDao.findById(id);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return identityDao.getCount();
	}

}
