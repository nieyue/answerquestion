package com.nieyue.service.impl;

import com.nieyue.bean.Account;
import com.nieyue.bean.Role;
import com.nieyue.dao.AccountDao;
import com.nieyue.service.AccountService;
import com.nieyue.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDao accountDao;
	@Autowired
	RoleService roleService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Account account) {
		account.setCreateDate(new Date());
		account.setLoginDate(new Date());
		boolean b = accountDao.add(account);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer accountId) {
		boolean b = accountDao.delete(accountId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(Account account) {
		boolean b = accountDao.update(account);
		return b;
	}

	@Override
	public Account load(Integer accountId) {
		Account r = accountDao.load(accountId);
		if(r!=null){
			Role role = roleService.load(r.getRoleId());
			r.setRole(role);
		}
		return r;
	}

	@Override
	public Account login(String adminName, String password,Integer accountId) {
		Account r = accountDao.login(adminName,password,accountId);
		if(r!=null){
			Role role = roleService.load(r.getRoleId());
			r.setRole(role);
		}
		return r;
	}

	@Override
	public int count(String realname,
						Integer roleId,
						Integer status) {
		int c = accountDao.count(realname,roleId,status);
		return c;
	}

	@Override
	public List<Account> list(
			String realname,
			Integer roleId,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Account> l = accountDao.list(
				realname,roleId,status,
				pageNum-1, pageSize, orderName, orderWay);
		l.forEach(e->{
			if(e!=null){
				Role role = roleService.load(e.getRoleId());
				e.setRole(role);
			}
		});
		return l;
	}

	
}
