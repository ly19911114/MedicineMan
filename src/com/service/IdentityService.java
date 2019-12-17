package com.service;

import java.util.List;

import com.entity.Identity;

public interface IdentityService {
	
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
