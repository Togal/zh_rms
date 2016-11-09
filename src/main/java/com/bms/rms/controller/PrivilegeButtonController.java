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
import com.bms.rms.model.po.TPrivilegeButton;
import com.bms.rms.model.po.TPrivilegeButtonCustom;
import com.bms.rms.model.vo.PubRetrunMsg;
import com.bms.rms.model.vo.TPrivilegeButtonVo;
import com.boboface.base.util.BaseUtil;

/**
 * 
 * Title:PrivilegeButtonController
 * Description:按钮级别权限控制控制类
 * @author    zwb
 * @date      2016年11月1日 下午6:17:53
 *
 */
@Controller
@RequestMapping("/rms/privilegeButton")
public class PrivilegeButtonController extends BaseController {
	
	/**
	 * 按钮级别权限控制列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/privilegeButton_lst";
	}
	
	/**
	 * 按钮级别权限控制列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TPrivilegeButton> privilegeButtons = iPrivilegeButtonService.findAll();
		data.put("list", privilegeButtons);
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
			TPrivilegeButton privilegeButton = iPrivilegeButtonService.getById(id);
			TPrivilegeButtonCustom privilegeButtonCustom = new TPrivilegeButtonCustom();
			BeanUtils.copyProperties(privilegeButton, privilegeButtonCustom);
			model.addAttribute("privilegeButton", privilegeButtonCustom);
		}else{//add
			TPrivilegeButtonCustom privilegeButton = new TPrivilegeButtonCustom();
			privilegeButton.setAddtime((int)BaseUtil.currentTimeMillis());
			model.addAttribute("privilegeButton", privilegeButton);
		}
		model.addAttribute("type", type);
		return "sys/privilegeButton_save";
	}
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param privilegeButtonVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TPrivilegeButtonVo privilegeButtonVo){
		if("edit".equals(type)){
			TPrivilegeButton privilegeButton = iPrivilegeButtonService.getById(privilegeButtonVo.getPrivilegeButton().getId());
			privilegeButton.setName(privilegeButtonVo.getPrivilegeButton().getName());
			privilegeButton.setSelectdommethod(privilegeButtonVo.getPrivilegeButton().getSelectdommethod());
			privilegeButton.setSelectdomname(privilegeButtonVo.getPrivilegeButton().getSelectdomname());
			privilegeButton.setAddtime((int)(privilegeButtonVo.getAddtime().getTime() / 1000L));
			iPrivilegeButtonService.updateSeletive(privilegeButton);
		}else{//add
			TPrivilegeButton privilegeButton = new TPrivilegeButton();
			privilegeButton.setName(privilegeButtonVo.getPrivilegeButton().getName());
			privilegeButton.setSelectdommethod(privilegeButtonVo.getPrivilegeButton().getSelectdommethod());
			privilegeButton.setSelectdomname(privilegeButtonVo.getPrivilegeButton().getSelectdomname());
			privilegeButton.setAddtime((int)(privilegeButtonVo.getAddtime().getTime() / 1000L));
			iPrivilegeButtonService.saveSeletive(privilegeButton);
		}
		return "result";
	}
	
	/**
	 * 删除按钮级别权限控制
	 * @param id
	 * @return
	 */
	@GetMapping("/delete/{id}")
	public String deletePrivilegeButton(@PathVariable("id") Integer id){
		iPrivilegeButtonService.delete(id);
		return "result";
	}
	
	/**
	 * 批量删除按钮级别权限控制
	 * @param ids
	 * @return
	 */
	@PostMapping("/deletes")
	public String deletes(Integer[] ids){
		iPrivilegeButtonService.delete(ids);
		return "result";
	}
}
