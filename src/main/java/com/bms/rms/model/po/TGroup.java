package com.bms.rms.model.po;

import javax.persistence.Id;

/**
 * 
 * Title:TGroup
 * Description:组别po类
 * @author    zwb
 * @date      2016年9月29日 上午11:30:39
 *
 */
public class TGroup {
	@Id
    private Integer id;

    private String groupname;

    private Integer addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname == null ? null : groupname.trim();
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }
}