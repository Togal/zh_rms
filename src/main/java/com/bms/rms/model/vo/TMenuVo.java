package com.bms.rms.model.vo;

import java.util.Date;

import com.bms.rms.model.po.TMenu;

/**
 * 
 * Title:TMenuVo
 * Description:菜单vo包装类
 * @author    zwb
 * @date      2016年10月1日 上午12:16:27
 *
 */
public class TMenuVo {
	
	private TMenu menu;
	
	private Date addtime;//添加时间

	public TMenu getMenu() {
		return menu;
	}

	public void setMenu(TMenu menu) {
		this.menu = menu;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}	
}
