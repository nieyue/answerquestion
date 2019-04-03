package com.nieyue.service;

import com.nieyue.bean.Course;

import java.util.List;

/**
 * 课程逻辑层接口
 * @author yy
 *
 */
public interface CourseService {
	/** 新增 */
	public boolean add(Course course) ;
	/** 删除 */
	public boolean delete(Integer courseId) ;
	/** 更新*/
	public boolean update(Course course);
	/** 装载 */
	public Course load(Integer courseId);
	/** 数目 */
	public int count(Integer majorId);
	/** 列表 */
	public List<Course> list(
            Integer majorId,
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
