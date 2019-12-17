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
import com.entity.SellOrder;
import com.service.MedicineService;
import com.service.SellOrderService;
import com.utils.DateJsonValueProcessor;
import com.utils.ResponseUtil;
import com.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * 售药信息Controller类
 */
@Controller
@RequestMapping("/sellOrder")
public class SellOrderController {

	@Autowired
	private SellOrderService sellOrderService;
	
	@Autowired
	private MedicineService medicineService;
	
	/**
	 * 生成销售订单，此时不实现结账，药品库存也不用做变化
	 * @param request
	 * @param response
	 * @param id
	 * @param userId
	 * @param buyNums
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id",required=false)String id,
			@RequestParam(value="userId",required=false)String userId,
			@RequestParam(value="buyNums",required=false)String buyNums) throws Exception{
		
		Medicine medicine = medicineService.findById(Integer.parseInt(id));
		SellOrder order = new SellOrder();
		order.setBuyNums(Integer.parseInt(buyNums));
		order.setcName(medicine.getcName());
		order.setPrice(medicine.getPrice());
		order.setStatus(1);
		order.setUserId(Integer.parseInt(userId));
		order.setTotalMoney((medicine.getPrice())*(Integer.parseInt(buyNums)));
		
		int resultNum = sellOrderService.add(order);
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
	 * 获取所有未结账的订单的信息
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("Nlist")
	public String Nlist(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="cName",required=false)String cName) throws Exception{
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		
		if(StringUtil.isNotEmpty(cName)){
			map.put("cName", cName);
		}
		
		int total = sellOrderService.getNCount(map);
		List<SellOrder> list = sellOrderService.Nlist(map);
		
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
	 * 获取所有已结账的订单的信息
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("Ylist")
	public String Ylist(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="cName",required=false)String cName) throws Exception{
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		
		if(StringUtil.isNotEmpty(cName)){
			map.put("cName", cName);
		}
		
		int total = sellOrderService.getYCount(map);
		List<SellOrder> list = sellOrderService.Ylist(map);
		
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
			resultNum = sellOrderService.delete(Integer.parseInt(id[i]));
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
	 * 修改订单信息
	 * @param request
	 * @param response
	 * @param id
	 * @param buyNums
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="id",required=false)String id,
			@RequestParam(value="buyNums",required=false)String buyNums) throws Exception{
		
		SellOrder order = new SellOrder();
		order.setId(Integer.parseInt(id));
		order.setBuyNums(Integer.parseInt(buyNums));
		int resultNum = sellOrderService.update(order);
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
	 * 订单结账
	 * @param request
	 * @param response
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/pay")
	public String pay(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="ids",required=false)String ids) throws Exception{
		
		int resultNum = 0;
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			SellOrder order = new SellOrder();
			order.setId(Integer.parseInt(id[i]));
			order.setStatus(2);  //更改订单的状态
			resultNum = sellOrderService.update(order);
			
			//修改药品库存信息
			SellOrder o = sellOrderService.findById(Integer.parseInt(id[i]));
			Medicine m = medicineService.findByCName(o.getcName());
			Medicine medicine = new Medicine();
			medicine.setId(m.getId());
			medicine.setNums(m.getNums()-o.getBuyNums());
			medicineService.update(medicine);
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
