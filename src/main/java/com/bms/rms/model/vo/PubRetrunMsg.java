package com.bms.rms.model.vo;

import java.util.Map;

import com.bms.helper.CodeHelper;
import com.bms.helper.CodeHelper.CODE;
import com.boboface.base.util.BaseUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * Title:PubRetrunMsg
 * Description:公共返回协议
 * @author    zwb
 * @date      2016年7月25日 上午11:20:30
 *
 */
public class PubRetrunMsg {
	private int code;
	private String msg;
	@JsonIgnore
	private String logMsg;//日志信息，不给用户看到的
	private long times;
	private Object data;
	
	public PubRetrunMsg() {
		super();
	}
	
	/**
	 * 
	 * @param code 状态码
	 */
	public PubRetrunMsg(CODE code){
		Map<Integer, String> map = CodeHelper.code(code);
		this.code = map.keySet().iterator().next();
		this.msg = map.values().iterator().next();
		this.times = BaseUtil.currentTimeMillis();
	}
	
	/**
	 * 
	 * @param code 状态码
	 * @param data 数据
	 */
	public PubRetrunMsg(CODE code, Object data) {
		Map<Integer, String> map = CodeHelper.code(code);
		this.code = map.keySet().iterator().next();
		this.msg = map.values().iterator().next();
		this.times = BaseUtil.currentTimeMillis();
		this.data = data;
	}
	
	/**
	 * 
	 * @param code 状态码
	 * @param logMsg 日志错误信息
	 */
	public PubRetrunMsg(CODE code, String logMsg){
		Map<Integer, String> map = CodeHelper.code(code);
		this.code = map.keySet().iterator().next();
		this.msg =  map.values().iterator().next();
		this.logMsg = logMsg;
		this.times = BaseUtil.currentTimeMillis();
	}
	
	/**
	 * 
	 * @param code 状态码
	 * @param logMsg 日志错误信息
	 * @param data 数据
	 */
	public PubRetrunMsg(CODE code, String logMsg, Object data){
		Map<Integer, String> map = CodeHelper.code(code);
		this.code = map.keySet().iterator().next();
		this.msg = map.values().iterator().next();
		this.logMsg = logMsg;
		this.times = BaseUtil.currentTimeMillis();
		this.data = data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getLogMsg() {
		return logMsg;
	}
	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}
	public long getTimes() {
		return times;
	}
	public void setTimes(long times) {
		this.times = times;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
