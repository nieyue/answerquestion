package com.nieyue.dao;

import com.nieyue.bean.Answer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 回答数据库接口
 * @author yy
 *
 */
@Mapper
public interface AnswerDao {
    /** 新增*/
    public boolean add(Answer answer) ;
    /** 删除 */
    public boolean delete(Integer answerId) ;
    /** 更新*/
    public boolean update(Answer answer);
    /** 装载 */
    public Answer load(Integer answerId);
    /**总共数目 */
    public int count(
            @Param("problemId") Integer problemId);
    /** 列表 */
    public List<Answer> list(
            @Param("problemId") Integer problemId,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}