package com.bms.rms.model.po;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bms.data.type.conversion.date.DateDataTypeConversion;
import com.bms.data.type.conversion.date.vo.DATEFORMAT;

/**
 * 
 * Title:TUserCustom
 * Description:用户扩展类
 * @author    zwb
 * @date      2016年10月19日 下午4:20:28
 *
 */
public class TUserCustom extends TUser{
	
	private List<TPrivilegeCustom> hasPrivileges = new ArrayList<TPrivilegeCustom>();
	
	 /**
     * 获取添加时间戳转日期结果
     * @param date
     * @return
     */
    public String getAddTimeToDate(){
    	return DateDataTypeConversion.timeMillisToDate(getAddtime(), true, DATEFORMAT.YYYY_MM_DD_HH_MM);
    }
    
	public List<TPrivilegeCustom> getHasPrivileges() {
		return hasPrivileges;
	}

	public void setHasPrivileges(List<TPrivilegeCustom> hasPrivileges) {
		this.hasPrivileges = hasPrivileges;
	}
	
	/**
	 * 判断本用户是否有指定URL权限
	 * @param privilegeUrl
	 * @return
	 */
	public boolean hasPrivilegeByUrl(String privilegeUrl,HttpServletRequest request){
		
		if(isAdmin()){//admin超级管理员
			return true;
		}
		
		String privilegeUrlTemp = privilegeUrl;
		int strLen = "Submit".length();
		int pos = privilegeUrl.indexOf("Submit");
		if(pos > -1){
			privilegeUrl = privilegeUrlTemp.substring(0,pos) + privilegeUrlTemp.substring(pos + strLen ,privilegeUrl.length());
		}
		
		List<String> allPrivilege = (List<String>) request.getServletContext().getAttribute("allPrivilegeUrl");
		if(!allPrivilege.contains(privilegeUrl)){
			return true;
		}else{
			for (TPrivilege hasPrivilege : hasPrivileges) {
				if(privilegeUrl.equals(hasPrivilege.getPrivilegeurl())){
					return true;
				}
			}
			return false;
		}
	}
	
	private boolean isAdmin(){
		if("zowbman".equals(this.getUseraccount()))
			return true;
		return false;
	}
}
