package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MedicineDao;
import com.entity.Medicine;
import com.service.MedicineService;

@Service("medicineService")
public class MedicineServiceImpl implements MedicineService{

	@Autowired
	private MedicineDao medicineDao;
	
	@Override
	public Medicine findById(Integer id) {
		// TODO Auto-generated method stub
		return medicineDao.findById(id);
	}

	@Override
	public List<Medicine> findByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return medicineDao.findByTypeId(typeId);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return medicineDao.delete(id);
	}

	@Override
	public int update(Medicine medicine) {
		// TODO Auto-generated method stub
		return medicineDao.update(medicine);
	}

	@Override
	public int add(Medicine medicine) {
		// TODO Auto-generated method stub
		return medicineDao.add(medicine);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return medicineDao.getCount(map);
	}

	@Override
	public List<Medicine> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return medicineDao.list(map);
	}

	@Override
	public Medicine findByCName(String cName) {
		// TODO Auto-generated method stub
		return medicineDao.findByCName(cName);
	}

}
