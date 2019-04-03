package com.nieyue.service.impl;

import com.nieyue.bean.Academy;
import com.nieyue.dao.AcademyDao;
import com.nieyue.service.AcademyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AcademyServiceImpl implements AcademyService{
	@Resource
	AcademyDao academyDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Academy academy) {
		academy.setCreateDate(new Date());
		academy.setUpdateDate(new Date());
		boolean b = academyDao.add(academy);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer academyId) {
		boolean b = academyDao.delete(academyId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(Academy academy) {
		academy.setUpdateDate(new Date());
		boolean b = academyDao.update(academy);
		return b;
	}

	@Override
	public Academy load(Integer academyId) {
		Academy r = academyDao.load(academyId);
		return r;
	}

	@Override
	public int count() {
		int c = academyDao.count();
		return c;
	}

	@Override
	public List<Academy> list(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Academy> l = academyDao.list(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
