package com.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.MedicineType;

@Repository("medicineTypeDao")
public interface MedicineTypeDao {
	
	/**
	 * 通过id查找医药类别
	 * @return
	 */
	public MedicineType findById(Integer id);
	
	/**
	 * 通过id删除医药类别
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 添加医药类别
	 * @param medicineType
	 * @return
	 */
	public int add(MedicineType medicineType);
	
	/**
	 * 根据条件分页查询医药类别
	 * @param map
	 * @return
	 */
	public List<MedicineType> list(Map<String,Object> map);
	
	/**
	 * 获取类别总记录数
	 * @param map
	 * @return
	 */
	public int getCount(Map<String,Object> map);
	
	/**
	 * 更新医药类别信息
	 * @param medicineType
	 * @return
	 */
	public int update(MedicineType medicineType);
	
	/**
	 * 获取所有药品类别
	 * @return
	 */
	public List<MedicineType> allList();
}
