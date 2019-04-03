package com.nieyue.service.impl;

import com.nieyue.bean.Datum;
import com.nieyue.dao.DatumDao;
import com.nieyue.service.DatumService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class DatumServiceImpl implements DatumService{
	@Resource
	DatumDao datumDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Datum datum) {
		datum.setCreateDate(new Date());
		datum.setUpdateDate(new Date());
		boolean b = datumDao.add(datum);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer datumId) {
		boolean b = datumDao.delete(datumId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(Datum datum) {
		datum.setUpdateDate(new Date());
		boolean b = datumDao.update(datum);
		return b;
	}

	@Override
	public Datum load(Integer datumId) {
		Datum r = datumDao.load(datumId);
		return r;
	}

	@Override
	public int count(Integer accountId) {
		int c = datumDao.count(accountId);
		return c;
	}

	@Override
	public List<Datum> list( Integer accountId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Datum> l = datumDao.list(accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
