package com.vip.exception;
/**
 * 系统全局异常
 * @author Administrator
 *
 */
public class VipException extends RuntimeException {
	public final String code;

	public VipException(String code) {
		super();
		this.code = code;
	}
	public VipException(String code,String message) {
		super(message);
		this.code = code;
	}
	public VipException(String code,String message,Throwable e) {
		super(message,e);
		this.code = code;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
	
}
