package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entity.Identity;

@Repository("identityDao")
public interface IdentityDao {
	
	/**
	 * ����id���Ҷ�Ӧ��Ȩ��
	 * @param id
	 * @return
	 */
	public Identity findById(int id);
	
	/**
	 * ��������Ȩ����Ϣ
	 * @return
	 */
	public List<Identity> list();
	
	/**
	 * ��������Ȩ�޵��ܼ�¼��
	 * @return
	 */
	public int getCount();
}
