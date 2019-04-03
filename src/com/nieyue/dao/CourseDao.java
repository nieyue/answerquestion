package com.nieyue.dao;

import com.nieyue.bean.Course;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程数据库接口
 * @author yy
 *
 */
@Mapper
public interface CourseDao {
    /** 新增*/
    public boolean add(Course course) ;
    /** 删除 */
    public boolean delete(Integer courseId) ;
    /** 更新*/
    public boolean update(Course course);
    /** 装载 */
    public Course load(Integer courseId);
    /**总共数目 */
    public int count(@Param("majorId") Integer majorId);
    /** 列表 */
    public List<Course> list(
            @Param("majorId") Integer majorId,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}