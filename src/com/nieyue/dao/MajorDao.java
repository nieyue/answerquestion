package com.nieyue.dao;

import com.nieyue.bean.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专业数据库接口
 * @author yy
 *
 */
@Mapper
public interface MajorDao {
    /** 新增*/
    public boolean add(Major major) ;
    /** 删除 */
    public boolean delete(Integer majorId) ;
    /** 更新*/
    public boolean update(Major major);
    /** 装载 */
    public Major load(Integer majorId);
    /**总共数目 */
    public int count(@Param("academyId") Integer academyId);
    /** 列表 */
    public List<Major> list(
            @Param("academyId") Integer academyId,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}