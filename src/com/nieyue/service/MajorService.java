package com.nieyue.service;

import com.nieyue.bean.Major;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 专业逻辑层接口
 * @author yy
 *
 */
public interface MajorService {
	/** 新增 */
	public boolean add(Major major) ;
	/** 删除 */
	public boolean delete(Integer majorId) ;
	/** 更新*/
	public boolean update(Major major);
	/** 装载 */
	public Major load(Integer majorId);
	/** 数目 */
	public int count(Integer academyId);
	/** 列表 */
	public List<Major> list(
			Integer academyId,
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
