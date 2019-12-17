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

import com.entity.Medicine;
import com.entity.PageBean;
import com.service.MedicineService;
import com.utils.DateJsonValueProcessor;
import com.utils.DateUtil;
import com.utils.ResponseUtil;
import com.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/medicine")
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	/**
	 * 分页查询所有药品信息
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param cName
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="cName",required=false)String cName,
			@RequestParam(value="typeId",required=false)String typeId) throws Exception{
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		
		if(StringUtil.isNotEmpty(cName)){
			map.put("cName", cName);
		}
		if(StringUtil.isNotEmpty(typeId)){
			map.put("typeId", Integer.parseInt(typeId));
		}
		
		int total = medicineService.getCount(map);
		List<Medicine> list = medicineService.list(map);
		
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
	 * 添加药品信息
	 * @param request
	 * @param response
	 * @param cName
	 * @param eName
	 * @param price
	 * @param nums
	 * @param manufacturer
	 * @param describle
	 * @param productDate
	 * @param safeDate
	 * @param standard
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public String add(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="cName",required=false)String cName,
			@RequestParam(value="eName",required=false)String eName,
			@RequestParam(value="price",required=false)String price,
			@RequestParam(value="nums",required=false)String nums,
			@RequestParam(value="manufacturer",required=false)String manufacturer,
			@RequestParam(value="describle",required=false)String describle,
			@RequestParam(value="productDate",required=false)String productDate,
			@RequestParam(value="safeDate",required=false)String safeDate,
			@RequestParam(value="standard",required=false)String standard,
			@RequestParam(value="oldPrice",required=false)Double oldPrice,
			@RequestParam(value="typeId",required=false)String typeId) throws Exception{
		
		Medicine medicine = new Medicine();
		medicine.setcName(cName);
		medicine.setDescrible(describle);
		medicine.seteName(eName);
		medicine.setManufacturer(manufacturer);
		medicine.setNums(Integer.parseInt(nums));
		medicine.setPrice(Double.parseDouble(price));
		medicine.setProductDate(DateUtil.formatString(productDate, "yyyy-mm-dd"));
		medicine.setSafeDate(safeDate);
		medicine.setStandard(standard);
		medicine.setOldPrice(oldPrice);;
		medicine.setTypeId(Integer.parseInt(typeId));
		
		int resultNum = medicineService.add(medicine);
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
	 * 更新药品信息
	 * @param request
	 * @param response
	 * @param cName
	 * @param eName
	 * @param price
	 * @param nums
	 * @param manufacturer
	 * @param describle
	 * @param productDate
	 * @param safeDate
	 * @param standard
	 * @param typeId
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="cName",required=false)String cName,
			@RequestParam(value="eName",required=false)String eName,
			@RequestParam(value="price",required=false)String price,
			@RequestParam(value="nums",required=false)String nums,
			@RequestParam(value="manufacturer",required=false)String manufacturer,
			@RequestParam(value="describle",required=false)String describle,
			@RequestParam(value="productDate",required=false)String productDate,
			@RequestParam(value="safeDate",required=false)String safeDate,
			@RequestParam(value="standard",required=false)String standard,
			@RequestParam(value="typeId",required=false)String typeId,
			@RequestParam(value="id",required=false)String id) throws Exception{
		
		Medicine medicine = new Medicine();
		medicine.setcName(cName);
		medicine.setDescrible(describle);
		medicine.seteName(eName);
		medicine.setManufacturer(manufacturer);
		medicine.setNums(Integer.parseInt(nums));
		medicine.setPrice(Double.parseDouble(price));
		medicine.setProductDate(DateUtil.formatString(productDate, "yyyy-MM-dd"));
		medicine.setSafeDate(safeDate);
		medicine.setStandard(standard);
		medicine.setTypeId(Integer.parseInt(typeId));
		medicine.setId(Integer.parseInt(id));
		
		int resultNum = medicineService.update(medicine);
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
	 * 删除药品信息
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
			resultNum = medicineService.delete(Integer.parseInt(id[i]));
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
