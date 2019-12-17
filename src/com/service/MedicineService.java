package com.service;

import java.util.List;
import java.util.Map;

import com.entity.Medicine;

public interface MedicineService {

	/**
	 * 根据id查找药品信息
	 * @param id
	 * @return
	 */
	public Medicine findById(Integer id);
	
	/**
	 * 根据具体药品名查找药品信息
	 * @param cName
	 * @return
	 */
	public Medicine findByCName(String cName);
	
	/**
	 * 根据药品类别查找药品信息
	 * @param typeId
	 * @return
	 */
	public List<Medicine> findByTypeId(Integer typeId);
	
	/**
	 * 删除药品信息
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 更新药品信息
	 * @param medicine
	 * @return
	 */
	public int update(Medicine medicine);
	
	/**
	 * 添加药品信息
	 * @param medicine
	 * @return
	 */
	public int add(Medicine medicine);
	
	/**
	 * 查找药品总记录数
	 * @param map
	 * @return
	 */
	public int getCount(Map<String,Object> map);
	
	/**
	 * 查找所有药品信息
	 * @param map
	 * @return
	 */
	public List<Medicine> list(Map<String,Object> map);
}
