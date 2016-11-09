package com.bms.rms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bms.base.service.impl.BaseServiceImpl;
import com.bms.rms.model.po.TPrivilegeButton;
import com.bms.rms.model.po.TPrivilegeButtonCustom;
import com.bms.rms.service.IPrivilegeButtonService;

/**
 * 
 * Title:PrivilegeButtonServiceImpl
 * Description:按钮级别权限service实现类
 * @author    zwb
 * @date      2016年11月1日 下午6:26:06
 *
 */
@Service
public class PrivilegeButtonServiceImpl extends BaseServiceImpl<TPrivilegeButton> implements IPrivilegeButtonService {

	@Override
	public List<TPrivilegeButtonCustom> findAllPrivilegeButton() {
		return tPrivilegeButtonMapper.findAllPrivilegeButton();
	}

}
