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
import com.bms.rms.model.po.TMenuCustom;
import com.bms.rms.model.po.TPrivilege;
import com.bms.rms.model.po.TPrivilegeButtonCustom;
import com.bms.rms.model.po.TPrivilegeCustom;
import com.bms.rms.model.po.TRole;
import com.bms.rms.model.vo.PubRetrunMsg;
import com.bms.rms.model.vo.TPrivilegeVo;
import com.boboface.base.util.BaseUtil;

/**
 * 
 * Title:PrivilegeController
 * Description:权限管理控制类
 * @author    zwb
 * @date      2016年10月19日 下午12:01:07
 *
 */
@Controller
@RequestMapping("/rms/privilege")
public class PrivilegeController extends BaseController {
	
	/**
	 * 权限列表页面
	 * @param model
	 * @return String
	 */
	@GetMapping("/list")
	public String list(Model model){
		return "sys/privilege_lst";
	}
	
	/**
	 * 权限列表数据
	 * @return PubRetrunMsg
	 */
	@GetMapping("/listData")
	@ResponseBody
	public PubRetrunMsg listData(){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TPrivilege> privileges = iPrivilegeService.findTopPrivileges();
		data.put("list", privileges);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 权限子级列表数据
	 * @param parentId 父级id
	 * @return PubRetrunMsg
	 */
	@GetMapping("/childrenListData/{parentId}")
	@ResponseBody
	public PubRetrunMsg childrenListData(@PathVariable("parentId") Integer parentId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<TPrivilege> privileges = iPrivilegeService.findChildrenPrivileges(parentId);
		data.put("list", privileges);
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
		
		//获取底层菜单（没有子菜单的菜单）
		//List<TMenu> menus = iMenuService.findAllBottomMenus();
		//model.addAttribute("menus", menus);
		
		//父级菜单
		List<TMenuCustom> parentMenus = iMenuService.findMenusForCascade();
		model.addAttribute("parentMenus", parentMenus);
		
		//绑定的按钮
		List<TPrivilegeButtonCustom> privilegeButtons = iPrivilegeButtonService.findAllPrivilegeButton();
		model.addAttribute("privilegeButtons", privilegeButtons);
		
		//获取父级权限列表
		List<TPrivilegeCustom> parentPrivileges = iPrivilegeService.findPrivilegesForCascade();
		model.addAttribute("parentPrivileges", parentPrivileges);
		if("edit".equals(type)){
			TPrivilege privilege = iPrivilegeService.getById(id);
			TPrivilegeCustom privilegeCustom = new TPrivilegeCustom();
			BeanUtils.copyProperties(privilege,privilegeCustom);
			model.addAttribute("privilege", privilegeCustom);
		}else{//add
			TPrivilegeCustom privilege = new TPrivilegeCustom();
			privilege.setAddtime((int)BaseUtil.currentTimeMillis());
			model.addAttribute("privilege", privilege);
		}
		model.addAttribute("type", type);
		return "sys/privilege_save";
	}

	/**
	 * 编辑提交
	 * @param model
	 * @param type 编辑类型
	 * @param privilegeVo
	 * @return
	 */
	@PostMapping("/saveSubmit/{type}")
	public String saveSubmit(Model model, @PathVariable("type") String type, TPrivilegeVo privilegeVo){
		if("edit".equals(type)){
			TPrivilege privilege = iPrivilegeService.getById(privilegeVo.getPrivilege().getId());
			privilege.setPrivilegename(privilegeVo.getPrivilege().getPrivilegename());
			privilege.setPrivilegeurl(privilegeVo.getPrivilege().getPrivilegeurl());
			privilege.setMenuid(privilegeVo.getPrivilege().getMenuid() == -1 ? null : privilegeVo.getPrivilege().getMenuid());
			privilege.setParentid(privilegeVo.getPrivilege().getParentid() == -1 ? null : privilegeVo.getPrivilege().getParentid());
			privilege.setAddtime((int)(privilegeVo.getAddtime().getTime() / 1000L));
			iPrivilegeService.updatePrivilege(privilege, privilegeVo.getPrivilegeButtonId() == -1 ? null : privilegeVo.getPrivilegeButtonId());
		}else{//add
			TPrivilege privilege = new TPrivilege();
			privilege.setPrivilegename(privilegeVo.getPrivilege().getPrivilegename());
			privilege.setPrivilegeurl(privilegeVo.getPrivilege().getPrivilegeurl().length() == 0 ? null : privilegeVo.getPrivilege().getPrivilegeurl());
			privilege.setMenuid(privilegeVo.getPrivilege().getMenuid() == -1 ? null : privilegeVo.getPrivilege().getMenuid());
			privilege.setParentid(privilegeVo.getPrivilege().getParentid() == -1 ? null : privilegeVo.getPrivilege().getParentid());
			privilege.setAddtime((int)(privilegeVo.getAddtime().getTime() / 1000L));
			iPrivilegeService.savePrivilege(privilege, privilegeVo.getPrivilegeButtonId() == -1 ? null : privilegeVo.getPrivilegeButtonId());
		}
		return "result";
	}
	
	/**
	 * 删除权限
	 * @param id
	 * @return
	 */
	@GetMapping("/deletePrivilege/{id}")
	public String deletePrivilege(@PathVariable("id") Integer id){
		iPrivilegeService.deletePrivilegeByIdForRecursion(id);
		return "result";
	}
	
	/**
	 * 批量删除权限
	 * @param ids
	 * @return
	 */
	@PostMapping("/deletePrivileges")
	public String deletePrivileges(Integer[] ids){
		iPrivilegeService.deletePrivilegeByIdsForRecursion(ids);
		return "result";
	}
	
	/**
	 * 权限-角色分配
	 * @param model
	 * @return
	 */
	@GetMapping("/privilegeRole/man")
	public String privilegeRoleMan(Model model){
		//获取父级权限列表
		List<TPrivilegeCustom> parentPrivileges = iPrivilegeService.findPrivilegesForCascade();
		model.addAttribute("parentPrivileges", parentPrivileges);
		
		//角色列表
		List<TRole> roles = iRoleService.findAll();
		model.addAttribute("roles", roles);
		return "sys/privilegeRole_man";
	}
	
	/**
	 * 根据权限查询角色
	 * @param privilegeId
	 * @return
	 */
	@GetMapping("/rolesByPrivilege/{id}")
	@ResponseBody
	public PubRetrunMsg rolesByPrivilege(@PathVariable("id") Integer privilegeId){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> roleIds = iPrivilegeService.findRoleIdsByPrivilegeId(privilegeId);
		data.put("roleIds", roleIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
	
	/**
	 * 权限-角色分配提交
	 * @return
	 */
	@PostMapping("/privilegeRole/manSubmit")
	@ResponseBody
	public PubRetrunMsg privilegeRoleManSubmit(Integer privilegeId, Integer[] roleIds){
		Map<String, Object> data = new HashMap<String, Object>();
		List<Integer> listRoleIds;
		if(roleIds == null){
			listRoleIds = new ArrayList<Integer>();
		}else{
			listRoleIds = new ArrayList<Integer>(Arrays.asList(roleIds));
		}
		
		iPrivilegeService.updatePrivilegeRoleByPrivilegeId(privilegeId, listRoleIds);
		return new PubRetrunMsg(CODE.D100000, data);
	}
}
