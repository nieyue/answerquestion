package com.nieyue.service;

import com.nieyue.bean.Problem;

import java.util.List;

/**
 * 问题逻辑层接口
 * @author yy
 *
 */
public interface ProblemService {
	/** 新增 */
	public boolean add(Problem problem) ;
	/** 删除 */
	public boolean delete(Integer problemId) ;
	/** 更新*/
	public boolean update(Problem problem);
	/** 装载 */
	public Problem load(Integer problemId);
	/** 数目 */
	public int count(Integer studentCourseId,Integer answerId);
	/** 列表 */
	public List<Problem> list(
            Integer studentCourseId,
            Integer answerId,
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
