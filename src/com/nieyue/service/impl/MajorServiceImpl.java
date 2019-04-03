package com.nieyue.service.impl;

import com.nieyue.bean.Major;
import com.nieyue.dao.MajorDao;
import com.nieyue.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MajorServiceImpl implements MajorService{
	@Resource
	MajorDao majorDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Major major) {
		major.setCreateDate(new Date());
		major.setUpdateDate(new Date());
		boolean b = majorDao.add(major);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer majorId) {
		boolean b = majorDao.delete(majorId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(Major major) {
		major.setUpdateDate(new Date());
		boolean b = majorDao.update(major);
		return b;
	}

	@Override
	public Major load(Integer majorId) {
		Major r = majorDao.load(majorId);
		return r;
	}

	@Override
	public int count(Integer academyId) {
		int c = majorDao.count(academyId);
		return c;
	}

	@Override
	public List<Major> list(Integer academyId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Major> l = majorDao.list(academyId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
