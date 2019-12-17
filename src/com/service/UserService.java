package com.service;

import java.util.List;
import java.util.Map;

import com.entity.User;

public interface UserService {
	
	/**
	 * 用户登录
	 * @param user
	 * @return User
	 */
	public User login(User user);
	
	/**
	 * 根据id查找用户
	 */
	public User findById(Integer id);

	/**
	 * 修改密码
	 */
	public int updatePwd(User user);
	
	
	/**
	 * 删除用户
	 * @return int
	 */
	public int delete(Integer id);
	
	/**
	 * 添加用户
	 * @return
	 */
	public int save(User user);
	
	/**
	 * 更新用户信息
	 * @param user
	 * @return
	 */
	public int update(User user);
	
	/**
	 * 更改用户权限
	 */
	public int updateIden(Map<String,Object> map);
	
	/**
	 * 根据邮箱查找用户密码
	 */
	public User findpwd(User user);
	
	/**
	 * 查找所有用户信息
	 * @return
	 */
	public List<User> list(Map<String,Object> map);
	
	/**
	 * 获取符合条件的用户记录数
	 * @param map
	 * @return
	 */
	public int getCount(Map<String,Object> map);
}
