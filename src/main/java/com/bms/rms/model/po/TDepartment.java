package com.bms.rms.model.po;

import javax.persistence.Id;

/**
 * 
 * Title:TDepartment
 * Description:部门po类
 * @author    zwb
 * @date      2016年9月29日 上午11:30:29
 *
 */
public class TDepartment {
	@Id
    private Integer id;

    private String departmentname;

    private Integer parentid;

    private Integer addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }
}