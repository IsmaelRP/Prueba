package com.prueba.shared;

public class ResponseObj<T> {

	private T body;
	private String msg;
	
	public ResponseObj(T body, String msg) {
		this.body = body;
		this.msg = msg;
	}

	public T getBody() {
		return body;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
}
