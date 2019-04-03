package com.nieyue.service.impl;

import com.nieyue.bean.Role;
import com.nieyue.dao.RoleDao;
import com.nieyue.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
	@Resource
	RoleDao roleDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Role role) {
		role.setUpdateDate(new Date());
		boolean b = roleDao.add(role);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer roleId) {
		boolean b = roleDao.delete(roleId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(Role role) {
		role.setUpdateDate(new Date());
		boolean b = roleDao.update(role);
		return b;
	}

	@Override
	public Role load(Integer roleId) {
		Role r = roleDao.load(roleId);
		return r;
	}

	@Override
	public int count() {
		int c = roleDao.count();
		return c;
	}

	@Override
	public List<Role> list(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Role> l = roleDao.list(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
