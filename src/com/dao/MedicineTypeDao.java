package com.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.entity.MedicineType;

@Repository("medicineTypeDao")
public interface MedicineTypeDao {
	
	/**
	 * ͨ��id����ҽҩ���
	 * @return
	 */
	public MedicineType findById(Integer id);
	
	/**
	 * ͨ��idɾ��ҽҩ���
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * ���ҽҩ���
	 * @param medicineType
	 * @return
	 */
	public int add(MedicineType medicineType);
	
	/**
	 * ����������ҳ��ѯҽҩ���
	 * @param map
	 * @return
	 */
	public List<MedicineType> list(Map<String,Object> map);
	
	/**
	 * ��ȡ����ܼ�¼��
	 * @param map
	 * @return
	 */
	public int getCount(Map<String,Object> map);
	
	/**
	 * ����ҽҩ�����Ϣ
	 * @param medicineType
	 * @return
	 */
	public int update(MedicineType medicineType);
	
	/**
	 * ��ȡ����ҩƷ���
	 * @return
	 */
	public List<MedicineType> allList();
}
