package com.nieyue.dao;

import com.nieyue.bean.Academy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学院数据库接口
 * @author yy
 *
 */
@Mapper
public interface AcademyDao {
    /** 新增*/
    public boolean add(Academy academy) ;
    /** 删除 */
    public boolean delete(Integer academyId) ;
    /** 更新*/
    public boolean update(Academy academy);
    /** 装载 */
    public Academy load(Integer academyId);
    /**总共数目 */
    public int count();
    /** 列表 */
    public List<Academy> list(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}