package com.nieyue.service;

import com.nieyue.bean.Answer;

import java.util.List;

/**
 * 回答逻辑层接口
 * @author yy
 *
 */
public interface AnswerService {
	/** 新增 */
	public boolean add(Answer answer) ;
	/** 删除 */
	public boolean delete(Integer answerId) ;
	/** 更新*/
	public boolean update(Answer answer);
	/** 装载 */
	public Answer load(Integer answerId);
	/** 数目 */
	public int count(Integer problemId);
	/** 列表 */
	public List<Answer> list(
            Integer problemId,
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
