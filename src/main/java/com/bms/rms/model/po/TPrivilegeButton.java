package com.bms.rms.model.po;

import javax.persistence.Id;

/**
 * 
 * Title:TPrivilegeButton
 * Description:按钮级别权限控制
 * @author    zwb
 * @date      2016年11月1日 下午6:08:57
 *
 */
public class TPrivilegeButton {
	@Id
    private Integer id;

    private Byte selectdommethod;

    private String selectdomname;

    private String name;

    private Integer privilegeid;
    
    private Integer addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getSelectdommethod() {
        return selectdommethod;
    }

    public void setSelectdommethod(Byte selectdommethod) {
        this.selectdommethod = selectdommethod;
    }

    public String getSelectdomname() {
        return selectdomname;
    }

    public void setSelectdomname(String selectdomname) {
        this.selectdomname = selectdomname == null ? null : selectdomname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(Integer privilegeid) {
        this.privilegeid = privilegeid;
    }
    
    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }
}