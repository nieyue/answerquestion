package com.nieyue.exception;


/**
 * {title}不存在异常
 * @author 聂跃
 * @date 2017年8月8日
 */
public class NotIsNotExistException extends CommonRollbackException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotIsNotExistException(){
	}
	public NotIsNotExistException(String title){
		this.setTitle(title);
	}

	
}


