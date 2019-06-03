package com.vip.dto;

import lombok.Data;

@Data
public class JSONResponse {
	private String code;
	private String message;
	private Object data;
	
	public static JSONResponse ok() {
		return new JSONResponse("ok",null,null);
	}
	public static JSONResponse ok(Object data) {
		return new JSONResponse("ok",null,data);
	}
	public static JSONResponse fail(String code, String message) {
		return new JSONResponse(code,message,null);
	}
	public static JSONResponse fail(String message) {
		return new JSONResponse("fail",message,null);
	}


	public JSONResponse(String code, String message, Object data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
