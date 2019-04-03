package com.nieyue.service.impl;

import com.nieyue.bean.StudentCourse;
import com.nieyue.dao.StudentCourseDao;
import com.nieyue.service.StudentCourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class StudentCourseServiceImpl implements StudentCourseService{
	@Resource
	StudentCourseDao studentCourseDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(StudentCourse studentCourse) {
		studentCourse.setCreateDate(new Date());
		studentCourse.setUpdateDate(new Date());
		boolean b = studentCourseDao.add(studentCourse);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer studentCourseId) {
		boolean b = studentCourseDao.delete(studentCourseId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(StudentCourse studentCourse) {
		studentCourse.setUpdateDate(new Date());
		boolean b = studentCourseDao.update(studentCourse);
		return b;
	}

	@Override
	public StudentCourse load(Integer studentCourseId) {
		StudentCourse r = studentCourseDao.load(studentCourseId);
		return r;
	}

	@Override
	public int count(Integer courseId,Integer accountId) {
		int c = studentCourseDao.count(courseId,accountId);
		return c;
	}

	@Override
	public List<StudentCourse> list(Integer courseId,Integer accountId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<StudentCourse> l = studentCourseDao.list(courseId,accountId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
