package com.bms.rms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.bms.base.service.impl.BaseServiceImpl;
import com.bms.rms.model.po.TGroup;
import com.bms.rms.service.IGroupService;
import com.boboface.base.util.BaseUtil;

/**
 * 
 * Title:GroupServiceImpl
 * Description:组别service实现类
 * @author    zwb
 * @date      2016年9月28日 下午6:29:56
 *
 */
@Service
public class GroupServiceImpl extends BaseServiceImpl<TGroup> implements IGroupService {

	@Override
	public List<Integer> findRoleIdsByGroupId(Integer groupId) {
		return tGroupMapper.findRoleIdsByGroupId(groupId);
	}

	@Override
	public void updateGroupRoleByGroupId(Integer groupId, List<Integer> newRoleIds) {
		List<Integer> oldRoleIds = findRoleIdsByGroupId(groupId);
		Map<String, Object> map = BaseUtil.compareArry(oldRoleIds, newRoleIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		
		for (Integer addId : add_arry) {
			tGroupMapper.insertGroupRole(groupId, addId);
		}
		
		for (Integer deleteId : delete_arry) {
			tGroupMapper.deleteGroupRoleByGroupIdAndRoleId(groupId, deleteId);
		}
	}

	@Override
	public List<Integer> findUserIdsByGroupId(Integer groupId) {
		return tGroupMapper.findUserIdsByGroupId(groupId);
	}

	@Override
	public void updateGroupUserByGroupId(Integer groupId, List<Integer> newUserIds) {
		List<Integer> oldUserIds = findUserIdsByGroupId(groupId);
		Map<String, Object> map = BaseUtil.compareArry(oldUserIds, newUserIds);
		//add_arry 添加的数组
		List<Integer> add_arry = (List<Integer>) map.get("add_arry");
		//delete_arry 删除的数组
		List<Integer> delete_arry = (List<Integer>) map.get("delete_arry");
		
		for (Integer addId : add_arry) {
			tGroupMapper.insertGroupUser(groupId, addId);
		}
		
		for (Integer deleteId : delete_arry) {
			tGroupMapper.deleteGroupUserByGroupIdAndUserId(groupId, deleteId);
		}
	}

	@Override
	public void deleteGroup(Integer groupId) {
		int affectRow = tGroupMapper.deleteByPrimaryKey(groupId);
		if(affectRow > 0){
			//用户组角色关联关系表
			tGroupMapper.deleteGroupRoleByGroupId(groupId);
			//用户组用户关联关系表
			tGroupMapper.deleteGroupUserByGroupId(groupId);
		}
	}

	@Override
	public void deleteGroup(Integer[] groupIds) {
		for (Integer groupId : groupIds) {
			deleteGroup(groupId);
		}
	}
}
