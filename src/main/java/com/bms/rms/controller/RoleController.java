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
import com.bms.rms.model.po.TPrivilegeCustom;
import com.bms.rms.model.po.TRole;
import com.bms.rms.model.po.TRoleCustom;
import com.bms.rms.model.po.TUser;
import com.bms.rms.model.vo.PubRetrunMsg;
import com.bms.rms.model.vo.TRoleVo;
import com.boboface.base.util.BaseUtil;

/**
 * 
 * Title:RoleController
 * Description:角色管理controller
 * @author    zwb
 * @date      2016年10月19日 下午12:55:18
 *
 */
@Controller
@RequestMapping("/rms/role")
public class RoleController extends BaseController {
	
	/**
	 * 角色列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/role_lst";
	}
	
	/**
	 * 角色列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TRole> roles = iRoleService.findAll();
		data.put("list", roles);
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
			TRole role = iRoleService.getById(id);
			TRoleCustom roleCustom = new TRoleCustom();
			BeanUtils.copyProperties(role,roleCustom);
			model.addAttribute("role", roleCustom);
		}else{//add
			TRoleCustom role = new TRoleCustom();
			role.setAddtime((int)BaseUtil.currentTimeMillis());
			role.setStatus((byte)0);
			model.addAttribute("role", role);
		}
		model.addAttribute("type", type);
		return "sys/role_save";
	}
	
	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param roleVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TRoleVo roleVo){
		if("edit".equals(type)){
			TRole role = iRoleService.getById(roleVo.getRole().getId());
			role.setRolename(roleVo.getRole().getRolename());
			role.setAddtime((int)(roleVo.getAddtime().getTime() / 1000L));
			role.setStatus(roleVo.getRole().getStatus());
			iRoleService.updateSeletive(role);
		}else{//add
			TRole role = new TRole();
			role.setRolename(roleVo.getRole().getRolename());
			role.setStatus(roleVo.getRole().getStatus());
			role.setAddtime((int)(roleVo.getAddtime().getTime() / 1000L));
			iRoleService.saveSeletive(role);
		}
		return "result";
	}
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@GetMapping("/deleteRole/{id}")
	public String deleteRole(@PathVariable("id") Integer id){
		iRoleService.deleteRole(id);
		return "result";
	}
	
	/**
	 * 批量删除角色
	 * @param ids
	 * @return
	 */
	@PostMapping("/deleteRoles")
	public String deleteRoles(Integer[] ids){
		iRoleService.delteRole(ids);
		return "result";
	}
	
	/**
	 * 角色-权限分配
	 * @param model
	 * @return
	 */
	@GetMapping("/rolePrivilege/man")
	public String rolePrivilegeMan(Model model){
		//角色列表
		List<TRole> roles = iRoleService.findAll();
		model.addAttribute("roles", roles);
		
		//权限列表
		List<TPrivilegeCustom> privileges = iPrivilegeService.findPrivilegesForCascade();
		model.addAttribute("privileges", privileges);
		return "sys/rolePrivilege_man";
	}
	
	/**
	 * 根据角色查询权限
	 * @param roleId
	 * @return
	 */
	@GetMapping("/privilegesByRole/{id}")
	@ResponseBody
	public PubRetrunMsg privilegesByRole(@PathVariable("id") Integer roleId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> privilegeIds = iRoleService.findPrivilegeIdsByRoleId(roleId);
		data.put("privilegeIds", privilegeIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 角色-权限分配提交
	 * @return
	 */
	@PostMapping("/rolePrivilege/manSubmit")
	@ResponseBody
	public PubRetrunMsg rolePrivilegeManSubmit(Integer roleId, Integer[] privilegeIds){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> listPrivilegeIds;
		if(privilegeIds == null){
			listPrivilegeIds = new ArrayList<Integer>();
		}else{
			listPrivilegeIds = new ArrayList<Integer>(Arrays.asList(privilegeIds));
		}
		
		iRoleService.updateRolePrivilegeByRoleId(roleId,listPrivilegeIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 角色-用户分配
	 * @param model
	 * @return
	 */
	@GetMapping("/roleUser/man")
	public String roleUserMan(Model model){
		//角色列表
		List<TRole> roles = iRoleService.findAll();
		model.addAttribute("roles", roles);
		
		//用户列表
		List<TUser> users = iUserService.findAll();
		model.addAttribute("users", users);
		return "sys/roleUser_man";
	}
	
	/**
	 * 根据角色查询用户
	 * @param roleId
	 * @return
	 */
	@GetMapping("/usersByRole/{id}")
	@ResponseBody
	public PubRetrunMsg usersByRole(@PathVariable("id") Integer roleId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> userIds = iRoleService.findUserIdsByRoleId(roleId);
		data.put("userIds", userIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 角色-用户分配提交
	 * @return
	 */
	@PostMapping("/roleUser/manSubmit")
	@ResponseBody
	public PubRetrunMsg roleUserManSubmit(Integer roleId, Integer[] userIds){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> listUserIds;
		if(userIds == null){
			listUserIds = new ArrayList<Integer>();
		}else{
			listUserIds = new ArrayList<Integer>(Arrays.asList(userIds));
		}
		
		iRoleService.updateRoleUserByRoleId(roleId,listUserIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 角色-用户组分配
	 * @param model
	 * @return
	 */
	@GetMapping("/roleGroup/man")
	public String roleGroupMan(Model model){
		//角色列表
		List<TRole> roles = iRoleService.findAll();
		model.addAttribute("roles", roles);
		
		//用户列表
		List<TGroup> groups = iGroupService.findAll();
		model.addAttribute("groups", groups);
		return "sys/roleGroup_man";
	}
	
	/**
	 * 根据角色查询用户组
	 * @param roleId
	 * @return
	 */
	@GetMapping("/groupsByRole/{id}")
	@ResponseBody
	public PubRetrunMsg groupsByRole(@PathVariable("id") Integer roleId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> groupIds = iRoleService.findGroupIdsByRoleId(roleId);
		data.put("groupIds", groupIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 角色-用户组分配提交
	 * @return
	 */
	@PostMapping("/roleGroup/manSubmit")
	@ResponseBody
	public PubRetrunMsg roleGroupManSubmit(Integer roleId, Integer[] groupIds){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> listGroupIds;
		if(groupIds == null){
			listGroupIds = new ArrayList<Integer>();
		}else{
			listGroupIds = new ArrayList<Integer>(Arrays.asList(groupIds));
		}
		
		iRoleService.updateRoleGroupByRoleId(roleId,listGroupIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
}
