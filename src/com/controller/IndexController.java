package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 起跳转作用的Controller类
 */
@Controller
@RequestMapping("/index")
public class IndexController {
	
	/**
	 * 前往系统登陆界面
	 * @return
	 */
	@RequestMapping("/goLogin")
	public String goLogin(){
		return "login/login";
	}
	
	/**
	 * 前往找回密码界面
	 * @return
	 */
	@RequestMapping("goFindpwd")
	public String goFindpwd(){
		return "login/forget-password";
	}
}
