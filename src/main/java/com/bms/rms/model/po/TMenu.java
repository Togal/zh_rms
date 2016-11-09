package com.bms.rms.model.po;

import javax.persistence.Id;

/**
 * 
 * Title:TMenu
 * Description:菜单po类
 * @author    zwb
 * @date      2016年9月29日 上午11:30:49
 *
 */
public class TMenu {
	
	@Id
    private Integer id;

    private String menuname;

    private String menuicon;

    private Byte sort;

    private Integer parentid;

    private Byte status;

    private Byte menutype;
    
    private Integer mastermenuid;

    private Integer addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenuicon() {
        return menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon == null ? null : menuicon.trim();
    }

    public Byte getSort() {
        return sort;
    }

    public void setSort(Byte sort) {
        this.sort = sort;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getMenutype() {
        return menutype;
    }

    public void setMenutype(Byte menutype) {
        this.menutype = menutype;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

	public Integer getMastermenuid() {
		return mastermenuid;
	}

	public void setMastermenuid(Integer mastermenuid) {
		this.mastermenuid = mastermenuid;
	}
    
}