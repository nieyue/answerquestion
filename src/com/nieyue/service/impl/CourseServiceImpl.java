package com.nieyue.service.impl;

import com.nieyue.bean.Course;
import com.nieyue.dao.CourseDao;
import com.nieyue.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService{
	@Resource
	CourseDao courseDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean add(Course course) {
		course.setCreateDate(new Date());
		course.setUpdateDate(new Date());
		boolean b = courseDao.add(course);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delete(Integer courseId) {
		boolean b = courseDao.delete(courseId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean update(Course course) {
		course.setUpdateDate(new Date());
		boolean b = courseDao.update(course);
		return b;
	}

	@Override
	public Course load(Integer courseId) {
		Course r = courseDao.load(courseId);
		return r;
	}

	@Override
	public int count(Integer majorId) {
		int c = courseDao.count(majorId);
		return c;
	}

	@Override
	public List<Course> list(Integer majorId,int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Course> l = courseDao.list(majorId,pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
