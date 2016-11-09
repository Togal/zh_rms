package com.bms.rms.model.po;

import javax.persistence.Id;

/**
 * 
 * Title:TRole
 * Description:角色po类
 * @author    zwb
 * @date      2016年9月29日 上午11:31:05
 *
 */
public class TRole {
	@Id
    private Integer id;

    private String rolename;

    private Byte status;

    private Integer addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }
}