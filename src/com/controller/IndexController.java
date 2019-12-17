package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ����ת���õ�Controller��
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	/**
	 * ǰ��ϵͳ��½����
	 * @return
	 */
	@RequestMapping("/goLogin")
	public String goLogin(){
		return "login/login";
	}
	
	/**
	 * ǰ���һ��������
	 * @return
	 */
	@RequestMapping("goFindpwd")
	public String goFindpwd(){
		return "login/forget-password";
	}
}
