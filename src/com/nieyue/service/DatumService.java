package com.nieyue.service;

import com.nieyue.bean.Datum;

import java.util.List;

/**
 * 资料逻辑层接口
 * @author yy
 *
 */
public interface DatumService {
	/** 新增 */
	public boolean add(Datum datum) ;
	/** 删除 */
	public boolean delete(Integer datumId) ;
	/** 更新*/
	public boolean update(Datum datum);
	/** 装载 */
	public Datum load(Integer datumId);
	/** 数目 */
	public int count(Integer accountId);
	/** 列表 */
	public List<Datum> list(
            Integer accountId,
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
