package com.bms.rms.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
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
import com.bms.rms.model.po.TGroup;
import com.bms.rms.model.po.TRole;
import com.bms.rms.model.po.TUser;
import com.bms.rms.model.po.TUserCustom;
import com.bms.rms.model.vo.PubRetrunMsg;
import com.bms.rms.model.vo.TUserVo;
import com.boboface.base.util.BaseUtil;

@Controller
@RequestMapping("/rms/user")
public class UserController extends BaseController {
	
	/**
	 * 用户列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/user_lst";
	}
	
	/**
	 * 用户组列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TUser> users = iUserService.findAll();
		data.put("list", users);
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
		
		//部门列表
		List<TDepartment> departments = iDepartmentService.findAll();
		model.addAttribute("departments", departments);
		if("edit".equals(type)){
			TUser user = iUserService.getById(id);
			TUserCustom userCustom = new TUserCustom();
			BeanUtils.copyProperties(user,userCustom);
			userCustom.setUserpassword(null);
			model.addAttribute("user", userCustom);
		}else{//add
			TUserCustom user = new TUserCustom();
			user.setAddtime((int)BaseUtil.currentTimeMillis());
			model.addAttribute("user", user);
		}
		model.addAttribute("type", type);
		return "sys/user_save";
	}
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param groupVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TUserVo userVo){
		//...参数校验
		
		if("edit".equals(type)){
			TUser user = iUserService.getById(userVo.getUser().getId());
			user.setUseraccount(userVo.getUser().getUseraccount());
			user.setDepartmentid(userVo.getUser().getDepartmentid() == -1 ? null : userVo.getUser().getDepartmentid());
			user.setAddtime((int)(userVo.getAddtime().getTime() / 1000L));
			iUserService.update(user);
		}else{//add
			//二次密码校验
			
			TUser user = new TUser();
			user.setUseraccount(userVo.getUser().getUseraccount());
			user.setDepartmentid(userVo.getUser().getDepartmentid() == -1 ? null : userVo.getUser().getDepartmentid());
			user.setAddtime((int)(userVo.getAddtime().getTime() / 1000L));
			user.setUserpassword(DigestUtils.md5Hex(userVo.getUserpassword()));
			iUserService.save(user);
		}
		return "result";
	}
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") Integer id){
		iUserService.deleteUser(id);
		return "result";
	}
	
	/**
	 * 批量删除用户
	 * @param ids
	 * @return
	 */
	@PostMapping("/deleteUsers")
	public String deleteUsers(Integer[] ids){
		iUserService.delete(ids);
		return "result";
	}
	
	/**
	 * 用户-角色分配
	 * @param model
	 * @return
	 */
	@GetMapping("/userRole/man")
	public String userRoleMan(Model model){
		//获取用户列表
		List<TUser> users = iUserService.findAll();
		model.addAttribute("users", users);
		
		//角色列表
		List<TRole> roles = iRoleService.findAll();
		model.addAttribute("roles", roles);
		return "sys/userRole_man";
	}
	
	/**
	 * 根据用户查询角色
	 * @param userId
	 * @return
	 */
	@GetMapping("/rolesByUser/{id}")
	@ResponseBody
	public PubRetrunMsg rolesByUser(@PathVariable("id") Integer userId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> roleIds = iUserService.findRoleIdsByUserId(userId);
		data.put("roleIds", roleIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 用户-角色分配提交
	 * @return
	 */
	@PostMapping("/userRole/manSubmit")
	@ResponseBody
	public PubRetrunMsg userRoleManSubmit(Integer userId, Integer[] roleIds){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> listRoleIds;
		if(roleIds == null){
			listRoleIds = new ArrayList<Integer>();
		}else{
			listRoleIds = new ArrayList<Integer>(Arrays.asList(roleIds));
		}
		
		iUserService.updateUserRoleByUserId(userId, listRoleIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 用户-用户组分配
	 * @param model
	 * @return
	 */
	@GetMapping("/userGroup/man")
	public String userGroupMan(Model model){
		//获取用户列表
		List<TUser> users = iUserService.findAll();
		model.addAttribute("users", users);
		
		//组别列表
		List<TGroup> groups = iGroupService.findAll();
		model.addAttribute("groups", groups);
		return "sys/userGroup_man";
	}
	
	/**
	 * 根据用户查询用户组
	 * @param userId
	 * @return
	 */
	@GetMapping("/groupsByUser/{id}")
	@ResponseBody
	public PubRetrunMsg groupsByUser(@PathVariable("id") Integer userId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> groupIds = iUserService.findGroupIdsByUserId(userId);
		data.put("groupIds", groupIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 用户-用户组分配提交
	 * @return
	 */
	@PostMapping("/userGroup/manSubmit")
	@ResponseBody
	public PubRetrunMsg userGroupManSubmit(Integer userId, Integer[] groupIds){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> listGroupIds;
		if(groupIds == null){
			listGroupIds = new ArrayList<Integer>();
		}else{
			listGroupIds = new ArrayList<Integer>(Arrays.asList(groupIds));
		}
		
		iUserService.updateUserGroupByUserId(userId, listGroupIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
}
