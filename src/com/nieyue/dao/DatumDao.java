package com.nieyue.dao;

import com.nieyue.bean.Datum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资料数据库接口
 * @author yy
 *
 */
@Mapper
public interface DatumDao {
    /** 新增*/
    public boolean add(Datum datum) ;
    /** 删除 */
    public boolean delete(Integer datumId) ;
    /** 更新*/
    public boolean update(Datum datum);
    /** 装载 */
    public Datum load(Integer datumId);
    /**总共数目 */
    public int count(
            @Param("accountId") Integer accountId);
    /** 列表 */
    public List<Datum> list(
            @Param("accountId") Integer accountId,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}