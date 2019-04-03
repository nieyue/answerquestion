package com.nieyue.service.impl;

import com.nieyue.bean.Problem;
import com.nieyue.dao.ProblemDao;
import com.nieyue.service.ProblemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService{
	@Resource
	ProblemDao problemDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Problem problem) {
		problem.setCreateDate(new Date());
		problem.setUpdateDate(new Date());
		boolean b = problemDao.add(problem);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer problemId) {
		boolean b = problemDao.delete(problemId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(Problem problem) {
		problem.setUpdateDate(new Date());
		boolean b = problemDao.update(problem);
		return b;
	}

	@Override
	public Problem load(Integer problemId) {
		Problem r = problemDao.load(problemId);
		return r;
	}

	@Override
	public int count(Integer studentCourseId,Integer answerId) {
		int c = problemDao.count(studentCourseId,answerId);
		return c;
	}

	@Override
	public List<Problem> list(Integer studentCourseId,Integer answerId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Problem> l = problemDao.list(studentCourseId,answerId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
