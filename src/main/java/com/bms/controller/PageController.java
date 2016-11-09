package com.bms.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bms.base.controller.BaseController;
import com.bms.rms.model.po.TMenu;
import com.bms.rms.model.po.TMenuCustom;


/**
 * 
 * Title:PageController
 * Description:jsp页面控制器
 * @author    zwb
 * @date      2016年9月29日 上午11:32:01
 *
 */
@Controller
public class PageController extends BaseController {
	
	/**
	 * 首页
	 * @return
	 */
	@GetMapping("")
	public String index(Model model){
		List<TMenu> masterMenus =  iMenuService.findMasterMenusByStatus((byte)0);
		model.addAttribute("masterMenus", masterMenus);
		List<TMenuCustom> topSlaveMenus = iMenuService.findTopSlaveMenusAndPrivilege();
		model.addAttribute("topSlaveMenus", topSlaveMenus);
		return "index";
	}

}
