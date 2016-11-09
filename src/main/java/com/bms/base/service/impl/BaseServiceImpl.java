package com.bms.base.service.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.common.Mapper;

import com.bms.base.service.IBaseService;
import com.bms.rms.mapper.TDepartmentMapper;
import com.bms.rms.mapper.TGroupMapper;
import com.bms.rms.mapper.TMenuMapper;
import com.bms.rms.mapper.TPrivilegeButtonMapper;
import com.bms.rms.mapper.TPrivilegeMapper;
import com.bms.rms.mapper.TRoleMapper;
import com.bms.rms.mapper.TUserMapper;
import com.bms.rms.model.po.TDepartment;

@Service
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
	
	//泛型类
	private Class<T> clazz;
	
	@SuppressWarnings("unchecked")
	public BaseServiceImpl() {
		super();
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	/**
	 * 注入通用mapper
	 */
	@Autowired
	private Mapper<T> mapper;
	
	/**
	 * 菜单mapper接口
	 */
	@Autowired
	protected TMenuMapper tMenuMapper;
	
	/**
	 * 角色mapper接口
	 */
	@Autowired
	protected TRoleMapper tRoleMapper;

	/**
	 * 权限mapper接口
	 */
	@Autowired
	protected TPrivilegeMapper tPrivilegeMapper;

	/**
	 * 用户组mapper接口
	 */
	@Autowired
	protected TGroupMapper tGroupMapper;
	
	/**
	 * 用户mapper接口
	 */
	@Autowired
	protected TUserMapper tUserMapper;
	
	/**
	 * 按钮权限mapper
	 */
	@Autowired
	protected TPrivilegeButtonMapper tPrivilegeButtonMapper;
	
	/**
	 * 部门mapper接口
	 */
	@Autowired
	protected TDepartmentMapper tDepartmentMapper;

	public void save(T entity) {
		mapper.insert(entity);
	}
	
	public void saveSeletive(T entity) {
		mapper.insertSelective(entity);
	}

	public void delete(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void delete(Integer[] ids) {
		for (Integer id : ids) {
			mapper.deleteByPrimaryKey(id);
		}
	}
	public void update(T entity) {
		mapper.updateByPrimaryKey(entity);
	}

	public void updateSeletive(T entity) {
		mapper.updateByPrimaryKeySelective(entity);
	}

	public T getById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	public List<T> getByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> findAll() {
		return mapper.selectAll();
	}

	@Override
	public int getTotal(T entity) {
		return mapper.selectCount(entity);
	}
}
