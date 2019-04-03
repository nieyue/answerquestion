package com.nieyue.dao;

import com.nieyue.bean.Problem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 问题数据库接口
 * @author yy
 *
 */
@Mapper
public interface ProblemDao {
    /** 新增*/
    public boolean add(Problem problem) ;
    /** 删除 */
    public boolean delete(Integer problemId) ;
    /** 更新*/
    public boolean update(Problem problem);
    /** 装载 */
    public Problem load(Integer problemId);
    /**总共数目 */
    public int count(
                @Param("studentCourseId") Integer studentCourseId,
                @Param("answerId") Integer answerId);
    /** 列表 */
    public List<Problem> list(
            @Param("studentCourseId") Integer studentCourseId,
            @Param("answerId") Integer answerId,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}