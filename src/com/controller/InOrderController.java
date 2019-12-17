package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.InOrder;
import com.entity.Medicine;
import com.entity.PageBean;
import com.entity.User;
import com.service.InOrderService;
import com.service.MedicineService;
import com.service.UserService;
import com.utils.DateJsonValueProcessor;
import com.utils.ResponseUtil;
import com.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 进货订单Controller类
 */
@Controller
@RequestMapping("/inOrder")
public class InOrderController {
	
	@Autowired
	private InOrderService inOrderService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MedicineService medicineService;
	
	/**
	 * 添加进货订单信息
	 * @param request
	 * @param response
	 * @param cName
	 * @param eName
	 * @param price
	 * @param nums
	 * @param userId
	 * @param in_nums
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="cName",required=false)String cName,
			@RequestParam(value="eName",required=false)String eName,
			@RequestParam(value="price",required=false)String price,
			@RequestParam(value="nums",required=false)String nums,
			@RequestParam(value="userId",required=false)String userId,
			@RequestParam(value="in_nums",required=false)String in_nums,
			@RequestParam(value="id",required=false)String id) throws Exception{
		
		InOrder order = new InOrder();
		order.setcName(cName);
		order.seteName(eName);
		order.setNums(Integer.parseInt(in_nums));
		order.setPrice(Double.parseDouble(price));
		order.setUserId(Integer.parseInt(userId));
		
		int resultNum = 0;
		User user = userService.findById(Integer.parseInt(userId));
		
		if(user.getIdentity().getId()==1){  //系统管理员生产订单无需批准
			order.setStatus(2);
			resultNum = inOrderService.add(order);
			
			//修改药品库存信息
			Medicine m = medicineService.findById(Integer.parseInt(id));
			Medicine medicine = new Medicine();
			medicine.setId(m.getId());
			medicine.setNums(m.getNums()+Integer.parseInt(in_nums));
			medicineService.update(medicine);
		}else{
			
			order.setStatus(1);
			resultNum = inOrderService.add(order);
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
	 * 获取所有未批准订单的信息
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/Nlist")
	public String Nlist(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="trueName",required=false)String trueName) throws Exception{
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		
		if(StringUtil.isNotEmpty(trueName)){
			map.put("trueName", trueName);
		}
		
		int total = inOrderService.getNCount(map);
		List<InOrder> list = inOrderService.Nlist(map);
		
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
	 * 获取所有已批准订单的信息
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/Ylist")
	public String Ylist(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="trueName",required=false)String trueName) throws Exception{
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		
		if(StringUtil.isNotEmpty(trueName)){
			map.put("trueName", trueName);
		}
		
		int total = inOrderService.getYCount(map);
		List<InOrder> list = inOrderService.Ylist(map);
		
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
	 * 审核批准订单
	 * @param request
	 * @param response
	 * @param id
	 * @param jstatus
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/jOrder")
	public String jOrder(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id",required=false)String id,
			@RequestParam(value="jstatus",required=false)String jstatus) throws Exception{
		
		InOrder order = inOrderService.findById(Integer.parseInt(id));
		order.setStatus(Integer.parseInt(jstatus));
		int resultNum = inOrderService.update(order);
		
		//修改药品库存信息
		Medicine m = medicineService.findByCName(order.getcName());
		Medicine medicine = new Medicine();
		medicine.setId(m.getId());
		medicine.setNums(m.getNums()+order.getNums());
		medicineService.update(medicine);
		
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
	 * 删除订单信息
	 * @param request
	 * @param response
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="ids",required=false)String ids) throws Exception{
		
		int resultNum = 0;
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			resultNum = inOrderService.delete(Integer.parseInt(id[i]));
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
}
