package com.nieyue.service;

import java.util.List;

import com.nieyue.bean.Role;

/**
 * 角色逻辑层接口
 * @author yy
 *
 */
public interface RoleService {
	/** 新增 */
	public boolean add(Role role) ;
	/** 删除 */
	public boolean delete(Integer roleId) ;
	/** 更新*/
	public boolean update(Role role);
	/** 装载 */
	public Role load(Integer roleId);
	/** 数目 */
	public int count();
	/** 列表 */
	public List<Role> list(
			int pageNum,
			int pageSize,
			String orderName,
			String orderWay) ;
}
