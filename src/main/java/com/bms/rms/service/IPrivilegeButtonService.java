package com.bms.rms.service;

import java.util.List;

import com.bms.base.service.IBaseService;
import com.bms.rms.model.po.TPrivilegeButton;
import com.bms.rms.model.po.TPrivilegeButtonCustom;

/**
 * 
 * Title:IPrivilegeButtonService
 * Description:按钮级别权限控制service接口
 * @author    zwb
 * @date      2016年11月1日 下午6:25:05
 *
 */
public interface IPrivilegeButtonService extends IBaseService<TPrivilegeButton> {
	
	List<TPrivilegeButtonCustom> findAllPrivilegeButton();
}
