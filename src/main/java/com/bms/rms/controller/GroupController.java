package com.bms.rms.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.bms.rms.model.po.TGroup;
import com.bms.rms.model.po.TGroupCustom;
import com.bms.rms.model.po.TRole;
import com.bms.rms.model.po.TUser;
import com.bms.rms.model.vo.PubRetrunMsg;
import com.bms.rms.model.vo.TGroupVo;
import com.boboface.base.util.BaseUtil;

/**
 * 
 * Title:GroupController
 * Description:用户组contoller类
 * @author    zwb
 * @date      2016年10月19日 下午3:23:12
 *
 */
@Controller
@RequestMapping("/rms/group")
public class GroupController extends BaseController {
	
	/**
	 * 用户组列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/group_lst";
	}
	
	/**
	 * 用户组列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TGroup> groups = iGroupService.findAll();
		data.put("list", groups);
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
			TGroup group = iGroupService.getById(id);
			TGroupCustom groupCustom = new TGroupCustom();
			BeanUtils.copyProperties(group,groupCustom);
			model.addAttribute("group", groupCustom);
		}else{//add
			TGroupCustom group = new TGroupCustom();
			group.setAddtime((int)BaseUtil.currentTimeMillis());
			model.addAttribute("group", group);
		}
		model.addAttribute("type", type);
		return "sys/group_save";
	}
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param groupVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TGroupVo groupVo){
		if("edit".equals(type)){
			TGroup group = iGroupService.getById(groupVo.getGroup().getId());
			group.setGroupname(groupVo.getGroup().getGroupname());
			group.setAddtime((int)(groupVo.getAddtime().getTime() / 1000L));
			iGroupService.updateSeletive(group);
		}else{//add
			TGroup group = new TGroup();
			group.setGroupname(groupVo.getGroup().getGroupname());
			group.setAddtime((int)(groupVo.getAddtime().getTime() / 1000L));
			iGroupService.saveSeletive(group);
		}
		return "result";
	}
	
	/**
	 * 删除用户组
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteGroup/{id}")
	public String deleteGroup(@PathVariable("id") Integer id){
		iGroupService.deleteGroup(id);
		return "result";
	}
	
	/**
	 * 批量删除用户组
	 * @param ids
	 * @return
	 */
	@PostMapping("/deleteGroups")
	public String deleteGroups(Integer[] ids){
		iGroupService.deleteGroup(ids);
		return "result";
	}
	
	/**
	 * 用户组-角色分配
	 * @param model
	 * @return
	 */
	@GetMapping("/groupRole/man")
	public String groupRoleMan(Model model){
		//获取组别列表
		List<TGroup> groups = iGroupService.findAll();
		model.addAttribute("groups", groups);
		
		//角色列表
		List<TRole> roles = iRoleService.findAll();
		model.addAttribute("roles", roles);
		return "sys/groupRole_man";
	}
	
	/**
	 * 根据用户组查询角色
	 * @param groupId
	 * @return
	 */
	@GetMapping("/rolesByGroup/{id}")
	@ResponseBody
	public PubRetrunMsg rolesByGroup(@PathVariable("id") Integer groupId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> roleIds = iGroupService.findRoleIdsByGroupId(groupId);
		data.put("roleIds", roleIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 用户组-角色分配提交
	 * @return
	 */
	@PostMapping("/groupRole/manSubmit")
	@ResponseBody
	public PubRetrunMsg groupRoleManSubmit(Integer groupId, Integer[] roleIds){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> listRoleIds;
		if(roleIds == null){
			listRoleIds = new ArrayList<Integer>();
		}else{
			listRoleIds = new ArrayList<Integer>(Arrays.asList(roleIds));
		}
		
		iGroupService.updateGroupRoleByGroupId(groupId, listRoleIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 用户组-用户分配
	 * @param model
	 * @return
	 */
	@GetMapping("/groupUser/man")
	public String groupUserMan(Model model){
		//获取组别列表
		List<TGroup> groups = iGroupService.findAll();
		model.addAttribute("groups", groups);
		
		//用户列表
		List<TUser> users = iUserService.findAll();
		model.addAttribute("users", users);
		return "sys/groupUser_man";
	}
	
	/**
	 * 根据用户组查询用户
	 * @param groupId
	 * @return
	 */
	@GetMapping("/usersByGroup/{id}")
	@ResponseBody
	public PubRetrunMsg usersByGroup(@PathVariable("id") Integer groupId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> userIds = iGroupService.findUserIdsByGroupId(groupId);
		data.put("userIds", userIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 用户组-用户分配提交
	 * @return
	 */
	@PostMapping("/groupUser/manSubmit")
	@ResponseBody
	public PubRetrunMsg groupUserManSubmit(Integer groupId, Integer[] userIds){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> listUserIds;
		if(userIds == null){
			listUserIds = new ArrayList<Integer>();
		}else{
			listUserIds = new ArrayList<Integer>(Arrays.asList(userIds));
		}
		
		iGroupService.updateGroupUserByGroupId(groupId, listUserIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
}
