package com.bms.base.service;

import java.util.List;

public interface IBaseService<T> {
	/**
	 * 保存实体(所有字段)
	 * @param entity 
	 */
	void save(T entity);
	
	/**
	 * 保存实体
	 * @param entity
	 */
	void saveSeletive(T entity);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Integer id);
	
	/**
	 * 批量删除
	 * @param ids
	 */
	void delete(Integer[] ids);
	
	/**
	 * 更新实体（所有字段）
	 * @param entity
	 */
	void update(T entity);
	
	/**
	 * 更新实体
	 * @param entity
	 */
	void updateSeletive(T entity);
	
	/**
	 * 根据Id查询实体
	 * @param id
	 * @return
	 */
	T getById(Integer id);
	
	/**
	 * 多个Id查询实体
	 * @param ids
	 * @return
	 */
	List<T> getByIds(Integer[] ids);
	
	/**
	 * 查询全部
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * 获取总记录数
	 * @param entity
	 * @return
	 */
	int getTotal(T entity); 
}
