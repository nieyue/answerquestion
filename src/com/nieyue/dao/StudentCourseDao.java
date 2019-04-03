package com.nieyue.dao;

import com.nieyue.bean.StudentCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生课程数据库接口
 * @author yy
 *
 */
@Mapper
public interface StudentCourseDao {
    /** 新增*/
    public boolean add(StudentCourse studentCourse) ;
    /** 删除 */
    public boolean delete(Integer studentCourseId) ;
    /** 更新*/
    public boolean update(StudentCourse studentCourse);
    /** 装载 */
    public StudentCourse load(Integer studentCourseId);
    /**总共数目 */
    public int count(
            @Param("courseId") Integer courseId,
            @Param("accountId") Integer accountId
    );
    /** 列表 */
    public List<StudentCourse> list(
            @Param("courseId") Integer courseId,
            @Param("accountId") Integer accountId,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}