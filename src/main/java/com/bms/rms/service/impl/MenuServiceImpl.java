package com.bms.rms.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.bms.base.service.impl.BaseServiceImpl;
import com.bms.rms.model.po.TMenu;
import com.bms.rms.model.po.TMenuCustom;
import com.bms.rms.model.vo.MenuTypeEnum;
import com.bms.rms.service.IMenuService;

/**
 * 
 * Title:MenuServiceImpl
 * Description:菜单service实现类
 * @author    zwb
 * @date      2016年9月28日 下午6:29:44
 *
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<TMenu> implements IMenuService {
	
	@Override
	public List<TMenu> findMasterMenusByStatus(Byte status) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", status);
		criteria.andEqualTo("menutype", 0);
		return tMenuMapper.selectByExample(example);
	}
	

	@Override
	public List<TMenu> findMasterMenusByStatusAndNotMe(Byte status, Integer menuId) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("status", status);
		criteria.andEqualTo("menutype", 0);
		criteria.andNotEqualTo("id", menuId);
		return tMenuMapper.selectByExample(example);
	}

	@Override
	public List<TMenuCustom> findTopSlaveMenusAndPrivilege() {
		return tMenuMapper.findTopSlaveMenusAndPrivilege();
	}

	@Override
	public List<TMenu> findTopMenus(MenuTypeEnum menuTypeEnum) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		if(MenuTypeEnum.master.equals(menuTypeEnum)){
			criteria.andEqualTo("menutype", menuTypeEnum.getValue());
		}else{
			criteria.andEqualTo("menutype", menuTypeEnum.getValue());
		}
		criteria.andIsNull("parentid");
		return tMenuMapper.selectByExample(example);
	}

	@Override
	public List<TMenu> findChildrenSlaveMenus(Integer parentId) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menutype", 1);
		criteria.andEqualTo("parentid", parentId);
		return tMenuMapper.selectByExample(example);
	}

	@Override
	public List<TMenu> findSlaveMenus() {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("menutype", 1);
		return tMenuMapper.selectByExample(example);
	}
	
	@Override
	public List<TMenuCustom> findMenusForCascade(Integer masterMenuId) {
		return tMenuMapper.findMenusByMasterIdForCascade(masterMenuId);
	}

	@Override
	public void updateSlaveToMasterMenu(Integer masterMenuId, Integer parentMenuId) {
		List<TMenu> childrenMenus = findChildrenSlaveMenus(parentMenuId);
		//把当前节点的子节点的parentId为空，且把masterMenuId设置为当前节点id
		for (TMenu tMenu : childrenMenus) {
			tMenu.setParentid(null);
			tMenu.setMastermenuid(masterMenuId);
			tMenuMapper.updateByPrimaryKey(tMenu);
		}
	}

	@Override
	public Byte findMenuMaxSort() {
		Byte maxSort = tMenuMapper.findMenuMaxSort();
		if(maxSort == null){
			return 1;
		}
		return ++maxSort;
	}


	@Override
	public List<Integer> findMenuAndChildrenMenuIdsForRecursion(Integer id, List<Integer> ids) {
		ids.add(id);
		Example example = new Example(TMenu.class);
		example.selectProperties("id");
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("parentid", id);
		List<TMenu> tMenus = tMenuMapper.selectByExample(example);
		if(tMenus != null && tMenus.size() > 0){
			for (TMenu tMenu : tMenus) {
				findMenuAndChildrenMenuIdsForRecursion(tMenu.getId(),ids);
			}
		}
		return ids;
	}


	@Override
	public List<TMenu> findAllBottomMenus() {
		List<TMenuCustom> tMenuCustoms = tMenuMapper.findAllForCascade();
		List<TMenu> bottomMenus = recursionFindBottomMenus(tMenuCustoms, new ArrayList<TMenu>(),null);
		return bottomMenus;
	}
	
	/**
	 * 递归获取底层菜单（没有子菜单的菜单）
	 * @param menuCustoms
	 * @param bottomMenus
	 * @param bottomMenu
	 * @return
	 */
	private List<TMenu> recursionFindBottomMenus(List<TMenuCustom> menuCustoms, List<TMenu> bottomMenus, TMenu bottomMenu) {
		if(menuCustoms != null && menuCustoms.size() > 0){
			for (TMenuCustom menuCustom : menuCustoms) {
				recursionFindBottomMenus(menuCustom.getSlaveChildrenMenus(),bottomMenus,menuCustom);
			}
		}else{
			if(bottomMenu != null){
				bottomMenus.add(bottomMenu);
			}
		}
		return bottomMenus;
	}

	@Override
	public List<TMenu> findMenusForRecursion(Integer masterMenuId) {
		List<TMenuCustom> menuCustoms = tMenuMapper.findMenusByMasterIdForCascade(masterMenuId);
		return recursionMenusForSelect(menuCustoms, new ArrayList<TMenu>(),"┗",true);
	}
	
	@Override
	public List<TMenu> findMenusIsNotMenuForRecursion(Integer masterMenuId, Integer isNotMenuId) {
		List<TMenuCustom> menuCustoms = tMenuMapper.findMenusByMasterIdForCascade(masterMenuId);
		List<TMenu> resultMenus = recursionMenusForSelect(menuCustoms, new ArrayList<TMenu>(),"┗",true);
		
		//已经删除，已经更改
		boolean trueRemove = false,trueChange = false;
		Integer tempId = -1;
		Iterator<TMenu> it = resultMenus.iterator();
		while (it.hasNext()) {
			TMenu menu = it.next();
			
			if(menu.getId().equals(isNotMenuId)){
				it.remove();
				trueRemove = true;
				tempId = menu.getId();
			}
			if(tempId.equals(menu.getParentid())){
				menu.setMenuname(menu.getMenuname().replaceAll("[-┗]", ""));
				trueChange = false;
			}
			if(trueRemove && trueChange){
				break;
			}
		}
		return resultMenus;
	}

	/**
	 * 递归获取菜单（且处理菜单名称，显得有层次感）
	 * @param menuCustoms 处理数据
	 * @param resultMenu 结果数据
	 * @param str 插入字符
	 * @param isFirst 是否一次层
	 * @return
	 */
	private List<TMenu> recursionMenusForSelect(List<TMenuCustom> menuCustoms, List<TMenu> resultMenu, String str, boolean isFirst){
		if(menuCustoms != null && menuCustoms.size() > 0){
			for (TMenuCustom menuCustom : menuCustoms) {
				if(!isFirst){
					//处理
					menuCustom.setMenuname(str + menuCustom.getMenuname());
				}
				resultMenu.add(menuCustom);
				recursionMenusForSelect(menuCustom.getSlaveChildrenMenus(),resultMenu, ("-" + str), false);
			}
		}
		return resultMenu;
	}

	@Override
	public void updateMenuSeletive(TMenu tMenu) {
		boolean flag = true;
		if(tMenu.getParentid() != null){
			Example example = new Example(TMenu.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("id", tMenu.getParentid());
			List<TMenu> parentMenus = tMenuMapper.selectByExample(example);
			if(parentMenus != null && parentMenus.size() == 1){
				if(tMenu.getId().equals(parentMenus.get(0).getParentid())){
					parentMenus.get(0).setParentid(null);
					tMenuMapper.updateByPrimaryKey(parentMenus.get(0));
				}
			}else if(parentMenus.size() > 1){
				flag = false;
			}
		}
		if(flag){
			tMenuMapper.updateByPrimaryKeySelective(tMenu);
		}
	}

	@Override
	public List<TMenuCustom> findMenusForCascade() {
		return tMenuMapper.findMenusForCascade();
	}

	@Override
	public void deleteMenu(Integer[] ids) {
		Example example = new Example(TMenu.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", Arrays.asList(ids));
		tMenuMapper.deleteByExample(example);
		//这个id作为主菜单全部为NULL
		tMenuMapper.updateClearMasterMenuByMasterMenuIds(Arrays.asList(ids));	
		//权限
		tPrivilegeMapper.updateClearMenuByMenuIds(Arrays.asList(ids));
		
		//...后续可以选择级联删除功能
	}
}
