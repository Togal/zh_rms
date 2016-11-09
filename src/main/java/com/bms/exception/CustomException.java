package com.bms.exception;

/**
 * 
 * Title:CustomException
 * Description:系统自定义异常类
 * @author    zwb
 * @date      2016年3月13日 下午11:01:20
 *
 */
public class CustomException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	//异常信息
	public String message; 
	
	public CustomException(String message){
		super(message);
		this.message=message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
