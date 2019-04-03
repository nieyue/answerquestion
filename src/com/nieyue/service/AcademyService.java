package com.nieyue.service;

import com.nieyue.bean.Academy;

import java.util.List;

/**
 * 学院逻辑层接口
 * @author yy
 *
 */
public interface AcademyService {
	/** 新增 */
	public boolean add(Academy academy) ;
	/** 删除 */
	public boolean delete(Integer academyId) ;
	/** 更新*/
	public boolean update(Academy academy);
	/** 装载 */
	public Academy load(Integer academyId);
	/** 数目 */
	public int count();
	/** 列表 */
	public List<Academy> list(
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
