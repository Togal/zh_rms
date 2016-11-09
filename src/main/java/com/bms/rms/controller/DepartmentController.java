package com.bms.rms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bms.base.controller.BaseController;
import com.bms.helper.CodeHelper.CODE;
import com.bms.rms.model.po.TDepartment;
import com.bms.rms.model.po.TDepartmentCustom;
import com.bms.rms.model.vo.PubRetrunMsg;
import com.bms.rms.model.vo.TDepartmentVo;
import com.boboface.base.util.BaseUtil;

/**
 * 
 * Title:DepartmentController
 * Description:部门controller
 * @author    zwb
 * @date      2016年10月19日 下午2:35:01
 *
 */
@Controller
@RequestMapping("/rms/department")
public class DepartmentController extends BaseController {
	
	/**
	 * 部门列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/department_lst";
	}
	
	/**
	 * 部门列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TDepartment> departments = iDepartmentService.findAll();
		data.put("list", departments);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 编辑页
	 * @param type 编辑类型
	 * @param id
	 * @return
	 */
	@GetMapping("/save/{type}")
	public String save(Model model, @PathVariable("type") String type, Integer id){
		//...参数校验
		
		if("edit".equals(type)){
			TDepartment department = iDepartmentService.getById(id);
			TDepartmentCustom departmentCustom = new TDepartmentCustom();
			BeanUtils.copyProperties(department, departmentCustom);
			model.addAttribute("department", departmentCustom);
		}else{//add
			TDepartmentCustom department = new TDepartmentCustom();
			department.setAddtime((int)BaseUtil.currentTimeMillis());
			model.addAttribute("department", department);
		}
		model.addAttribute("type", type);
		return "sys/department_save";
	}
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param departmentVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TDepartmentVo departmentVo){
		if("edit".equals(type)){
			TDepartment department = iDepartmentService.getById(departmentVo.getDepartment().getId());
			department.setDepartmentname(departmentVo.getDepartment().getDepartmentname());
			department.setAddtime((int)(departmentVo.getAddtime().getTime() / 1000L));
			iDepartmentService.updateSeletive(department);
		}else{//add
			TDepartment department = new TDepartment();
			department.setDepartmentname(departmentVo.getDepartment().getDepartmentname());
			department.setAddtime((int)(departmentVo.getAddtime().getTime() / 1000L));
			iDepartmentService.saveSeletive(department);
		}
		return "result";
	}
	
	/**
	 * 删除部门
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteDepartment/{id}")
	public String deleteDepartment(@PathVariable("id") Integer id){
		iDepartmentService.deleteDepartment(id);
		return "result";
	}
	
	/**
	 * 批量删除部门
	 * @param ids
	 * @return
	 */
	@PostMapping("/deleteDepartments")
	public String deleteDepartments(Integer[] ids){
		iDepartmentService.deleteDepartment(ids);
		return "result";
	}
}
