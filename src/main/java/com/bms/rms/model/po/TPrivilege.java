package com.bms.rms.model.po;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 
 * Title:TPrivilege
 * Description:权限po类
 * @author    zwb
 * @date      2016年9月29日 上午11:30:57
 *
 */
public class TPrivilege {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String privilegename;

    private String privilegeurl;

    private Integer menuid;
    
    private Integer parentid;

    private Integer addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrivilegename() {
        return privilegename;
    }

    public void setPrivilegename(String privilegename) {
        this.privilegename = privilegename == null ? null : privilegename.trim();
    }

    public String getPrivilegeurl() {
        return privilegeurl;
    }

    public void setPrivilegeurl(String privilegeurl) {
        this.privilegeurl = privilegeurl == null ? null : privilegeurl.trim();
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}
       
}