package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Identity;
import com.entity.PageBean;
import com.entity.User;
import com.service.IdentityService;
import com.service.MedicineTypeService;
import com.service.UserService;
import com.utils.DateJsonValueProcessor;
import com.utils.EmailUtil;
import com.utils.ResponseUtil;
import com.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * �û�Controller��
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private MedicineTypeService medicineTypeService;
	
	/**
	 * �û���½��֤
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request){
		HttpSession session = request.getSession();
		User currentUser = userService.login(user);
		if(currentUser==null){
			request.setAttribute("errorMsg", "�û������������");
			request.setAttribute("user", user);
			return "login/login";
		}else{
			//��ȡ��ǰ�û�����ŵ�session��
			session.setAttribute("currentUser", currentUser);
			//��ȡ����Ȩ�ޣ���ŵ�session��
			session.setAttribute("identityList", identityService.list());
			//��ȡ����ҩƷ��𣬷ŵ�session�� 
			session.setAttribute("typeList", medicineTypeService.allList());
			return "redirect:/pages/main/mainTemp.jsp";
		}
	}
	
	/**
	 * �һ�����
	 * @param email
	 * @param request
	 * @return
	 */
	@RequestMapping("/findpwd")
	public String findBackPassword(@RequestParam(value="email")String email,HttpServletRequest request){
		User u = new User();
		u.setEmail(email);  //���û�������ʼ���ַ��װ��User������ȥ
		User user = userService.findpwd(u);
		if(user==null){
			request.setAttribute("user", u);
			request.setAttribute("Msg", "�����ַ��д����");
			return "/login/forget-password";
		}else{
			try {
				EmailUtil.sendEmail(user);
				request.setAttribute("Msg", "�ʼ����ͳɹ�����ע�����");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("Msg", "�ʼ�����ʧ��");
			}
			return "/login/forget-password";
		}
		
	}
	
	/**
	 * �޸��û�����
	 * @param newPassWord
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifypwd")
	public String modifyPassword(@RequestParam(value="newPassWord",required=false)String newPassWord,
			@RequestParam(value="id",required=false)String id,HttpServletResponse response)throws Exception{
		User user = new User();
		user.setId(Integer.parseInt(id));
		user.setPassWord(newPassWord);
		int resultNum = userService.updatePwd(user);
		
		JSONObject result = new JSONObject();
		if(resultNum>0){
			result.put("success", true);
			ResponseUtil.write(response, result);
		}else{
			result.put("success", false);
			ResponseUtil.write(response, result);
		}
		
		return null;
	}
	
	/**
	 * �˳���¼
	 * @param request
	 * @return
	 */
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("currentUser");
		
		return "redirect:/pages/main/mainTemp.jsp";
	}
	
	/**
	 * ����������ҳ��ѯ�����û���Ϣ
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param trueName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userlist")
	public String userlist(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="trueName",required=false)String trueName) throws Exception{
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		if(StringUtil.isNotEmpty(trueName)){
			map.put("trueName", trueName);
		}
		
		//��ȡ�ܼ�¼��
		int total = userService.getCount(map);
		
		//��ȡ��ҳ��ļ���
		List<User> list = userService.list(map);
		
		/**
		 * ����������溬��ʱ�����͵����ݣ�����ͨ�����·�ʽ��ʽ��Date����
		 */
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rows1 =JSONArray.fromObject(list, jsonConfig);
		
		JSONObject result=new JSONObject();
		result.put("total", total);
		result.put("rows", rows1);
		
		ResponseUtil.write(response, result);
		
		return null;
	}
	
	/**
	 * ɾ���û���Ϣ
	 * @param request
	 * @param response
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="ids",required=false)String ids) throws Exception{
		
		int resultNum = 0;
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			resultNum = userService.delete(Integer.parseInt(id[i]));
		}
		JSONObject result=new JSONObject();
		if(resultNum>0){
			result.put("success", true);
			ResponseUtil.write(response, result);
		}else{
			result.put("success", false);
			ResponseUtil.write(response, result);
		}
		
		return null;
	}
	
	/**
	 * ��ӹ���Ա
	 * @param request
	 * @param response
	 * @param userName
	 * @param passWord
	 * @param sex
	 * @param identity
	 * @param IDCard
	 * @param address
	 * @param email
	 * @param phone
	 * @param trueName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveUser")
	public String saveUser(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="userName",required=false)String userName,
			@RequestParam(value="passWord",required=false)String passWord,
			@RequestParam(value="sex",required=false)String sex,
			@RequestParam(value="identity",required=false)String identity,
			@RequestParam(value="IDCard",required=false)String IDCard,
			@RequestParam(value="address",required=false)String address,
			@RequestParam(value="email",required=false)String email,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="trueName",required=false)String trueName) throws Exception{
		
		User user = new User();
		user.setAddress(address);
		user.setEmail(email);
		user.setIDCard(IDCard);
		user.setIdenId(Integer.parseInt(identity));
		user.setPassWord(passWord);
		user.setPhone(phone);
		user.setSex(sex);
		user.setTrueName(trueName);
		user.setUserName(userName);
		
		int resultNum = userService.save(user);
		JSONObject result=new JSONObject();
		if(resultNum>0){
			result.put("success", true);
			ResponseUtil.write(response, result);
		}else{
			result.put("success", false);
			ResponseUtil.write(response, result);
		}
		return null;
	}
	
	/**
	 * ���¹���Ա��Ϣ
	 * @param request
	 * @param response
	 * @param userName
	 * @param passWord
	 * @param sex
	 * @param identity
	 * @param IDCard
	 * @param address
	 * @param email
	 * @param phone
	 * @param trueName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="userName",required=false)String userName,
			@RequestParam(value="passWord",required=false)String passWord,
			@RequestParam(value="sex",required=false)String sex,
			@RequestParam(value="identity",required=false)String identity,
			@RequestParam(value="IDCard",required=false)String IDCard,
			@RequestParam(value="address",required=false)String address,
			@RequestParam(value="email",required=false)String email,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="trueName",required=false)String trueName,
			@RequestParam(value="id",required=false)String id) throws Exception{
		
		User user = new User();
		user.setAddress(address);
		user.setEmail(email);
		user.setIDCard(IDCard);
		user.setIdenId(Integer.parseInt(identity));
		user.setPassWord(passWord);
		user.setPhone(phone);
		user.setSex(sex);
		user.setTrueName(trueName);
		user.setUserName(userName);
		user.setId(Integer.parseInt(id));
		
		int resultNum = userService.update(user);
		JSONObject result=new JSONObject();
		if(resultNum>0){
			result.put("success", true);
			ResponseUtil.write(response, result);
		}else{
			result.put("success", false);
			ResponseUtil.write(response, result);
		}
		return null;
	}
	
	/**
	 * �޸ĸ�����Ϣ���޷�����Ȩ��
	 * @param request
	 * @param response
	 * @param userName
	 * @param passWord
	 * @param sex
	 * @param IDCard
	 * @param address
	 * @param email
	 * @param phone
	 * @param trueName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update_personal")
	public String update_personal(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="userName",required=false)String userName,
			@RequestParam(value="passWord",required=false)String passWord,
			@RequestParam(value="sex",required=false)String sex,
			@RequestParam(value="IDCard",required=false)String IDCard,
			@RequestParam(value="address",required=false)String address,
			@RequestParam(value="email",required=false)String email,
			@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="trueName",required=false)String trueName,
			@RequestParam(value="id",required=false)String id) throws Exception{
		
		User user = new User();
		user.setAddress(address);
		user.setEmail(email);
		user.setIDCard(IDCard);
		user.setPassWord(passWord);
		user.setPhone(phone);
		user.setSex(sex);
		user.setTrueName(trueName);
		user.setUserName(userName);
		user.setId(Integer.parseInt(id));
		
		int resultNum = userService.update(user);
		
		User currentUser = userService.findById(Integer.parseInt(id));
		request.getSession().setAttribute("currentUser", currentUser);  //������Ϣ
		
		JSONObject result=new JSONObject();
		if(resultNum>0){
			result.put("success", true);
			ResponseUtil.write(response, result);
		}else{
			result.put("success", false);
			ResponseUtil.write(response, result);
		}
		return null;
	}
}
