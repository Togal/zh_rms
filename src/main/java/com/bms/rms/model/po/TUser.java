package com.bms.rms.model.po;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Title:TUser
 * Description:用户po类
 * @author    zwb
 * @date      2016年9月29日 下午5:49:26
 *
 */
public class TUser {
	@Id
    private Integer id;

    private String useraccount;

    @JsonIgnore
    private String userpassword;

    private Integer departmentid;

    private Integer addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount == null ? null : useraccount.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public Integer getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Integer departmentid) {
        this.departmentid = departmentid;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }
}