package com.nieyue.service;

import com.nieyue.bean.StudentCourse;

import java.util.List;

/**
 * 学生课程逻辑层接口
 * @author yy
 *
 */
public interface StudentCourseService {
	/** 新增 */
	public boolean add(StudentCourse studentCourse) ;
	/** 删除 */
	public boolean delete(Integer studentCourseId) ;
	/** 更新*/
	public boolean update(StudentCourse studentCourse);
	/** 装载 */
	public StudentCourse load(Integer studentCourseId);
	/** 数目 */
	public int count(Integer courseId,Integer accountId);
	/** 列表 */
	public List<StudentCourse> list(
            Integer courseId,
            Integer accountId,
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
