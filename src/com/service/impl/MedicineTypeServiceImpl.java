package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.MedicineTypeDao;
import com.entity.MedicineType;
import com.service.MedicineTypeService;

@Service("/medicineTypeService")
public class MedicineTypeServiceImpl implements MedicineTypeService{

	@Autowired
	private MedicineTypeDao medicineTypeDao;
	
	@Override
	public MedicineType findById(Integer id) {
		// TODO Auto-generated method stub
		return medicineTypeDao.findById(id);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return medicineTypeDao.delete(id);
	}

	@Override
	public int add(MedicineType medicineType) {
		// TODO Auto-generated method stub
		return medicineTypeDao.add(medicineType);
	}

	@Override
	public List<MedicineType> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return medicineTypeDao.list(map);
	}

	@Override
	public int getCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return medicineTypeDao.getCount(map);
	}

	@Override
	public int update(MedicineType medicineType) {
		// TODO Auto-generated method stub
		return medicineTypeDao.update(medicineType);
	}

	@Override
	public List<MedicineType> allList() {
		// TODO Auto-generated method stub
		return medicineTypeDao.allList();
	}

}
