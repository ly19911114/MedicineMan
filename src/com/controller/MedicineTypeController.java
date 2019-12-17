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
import com.entity.MedicineType;
import com.entity.PageBean;
import com.service.MedicineService;
import com.service.MedicineTypeService;
import com.utils.DateJsonValueProcessor;
import com.utils.ResponseUtil;
import com.utils.StringUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/medicineType")
public class MedicineTypeController {

	@Autowired
	private MedicineTypeService medicineTypeService;
	
	@Autowired
	private MedicineService medicineService;
	
	/**
	 * 查询所有药品类别信息
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/medicineTypeList")
	public String list(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			@RequestParam(value="typeName",required=false)String typeName) throws Exception{
		
		PageBean pageBean = new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("pageSize", pageBean.getPageSize());
		
		if(StringUtil.isNotEmpty(typeName)){
			map.put("typeName", typeName);
		}
		
		int total = medicineTypeService.getCount(map);
		List<MedicineType> list = medicineTypeService.list(map);
		
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
	 * 添加药品类别
	 * @param response
	 * @param request
	 * @param cTypeName
	 * @param eTypeName
	 * @param typeDesc
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public String add(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="cTypeName",required=false)String cTypeName,
			@RequestParam(value="eTypeName",required=false)String eTypeName,
			@RequestParam(value="typeDesc",required=false)String typeDesc) throws Exception{
		
		MedicineType medicineType = new MedicineType();
		medicineType.setcTypeName(cTypeName);
		medicineType.seteTypeName(eTypeName);
		medicineType.setTypeDesc(typeDesc);
		int resultNum = medicineTypeService.add(medicineType);
		
		/**
		 * 更新session中的药品类别信息
		 */
		request.getSession().setAttribute("typeList", medicineTypeService.allList());
		
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
	 * 更新药品类别信息
	 * @param response
	 * @param request
	 * @param cTypeName
	 * @param eTypeName
	 * @param typeDesc
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/update")
	public String update(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(value="cTypeName",required=false)String cTypeName,
			@RequestParam(value="eTypeName",required=false)String eTypeName,
			@RequestParam(value="typeDesc",required=false)String typeDesc,
			@RequestParam(value="id",required=false)String id) throws Exception{
		
		MedicineType medicineType = new MedicineType();
		medicineType.setcTypeName(cTypeName);
		medicineType.seteTypeName(eTypeName);
		medicineType.setTypeDesc(typeDesc);
		medicineType.setId(Integer.parseInt(id));
		int resultNum = medicineTypeService.update(medicineType);
		
		/**
		 * 更新session中的药品类别信息
		 */
		request.getSession().setAttribute("typeList", medicineTypeService.allList());
		
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
	 * 删除药品类别信息
	 * @param request
	 * @param response
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteType")
	public String deleteType(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="ids",required=false)String ids) throws Exception{
		
		int resultNum = 0;
		String[] id = ids.split(",");
		for(int i=0;i<id.length;i++){
			//再删除药品类别信息之前，应该先删除主表与该类别有关的药品
			List<Medicine> list = medicineService.findByTypeId(Integer.parseInt(id[i]));
			for(int j=0;j<list.size();j++){
				medicineService.delete(list.get(j).getId());
			}
			
			resultNum = medicineTypeService.delete(Integer.parseInt(id[i]));
		}
		JSONObject result=new JSONObject();
		
		/**
		 * 更新session中的药品类别信息
		 */
		request.getSession().setAttribute("typeList", medicineTypeService.allList());
		
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
