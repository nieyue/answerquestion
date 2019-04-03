package com.nieyue.dao;

import com.nieyue.bean.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色数据库接口
 * @author yy
 *
 */
@Mapper
public interface RoleDao {
    /** 新增*/
    public boolean add(Role role) ;
    /** 删除 */
    public boolean delete(Integer roleId) ;
    /** 更新*/
    public boolean update(Role role);
    /** 装载 */
    public Role load(Integer roleId);
    /**总共数目 */
    public int count();
    /** 列表 */
    public List<Role> list(
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}