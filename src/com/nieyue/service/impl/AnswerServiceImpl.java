package com.nieyue.service.impl;

import com.nieyue.bean.Answer;
import com.nieyue.dao.AnswerDao;
import com.nieyue.service.AnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService{
	@Resource
	AnswerDao answerDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Answer answer) {
		answer.setCreateDate(new Date());
		answer.setUpdateDate(new Date());
		boolean b = answerDao.add(answer);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer answerId) {
		boolean b = answerDao.delete(answerId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(Answer answer) {
		answer.setUpdateDate(new Date());
		boolean b = answerDao.update(answer);
		return b;
	}

	@Override
	public Answer load(Integer answerId) {
		Answer r = answerDao.load(answerId);
		return r;
	}

	@Override
	public int count(Integer problemId) {
		int c = answerDao.count(problemId);
		return c;
	}

	@Override
	public List<Answer> list( Integer problemId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Answer> l = answerDao.list(problemId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
