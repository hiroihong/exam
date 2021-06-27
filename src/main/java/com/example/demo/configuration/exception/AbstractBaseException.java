package com.example.demo.configuration.exception;

import com.example.demo.configuration.http.BaseResponseCode;

//커스텀으로 예외처리를 추가할경우
//추상클래스로 만들어 넣고 해당 클래스를 추가
public abstract class AbstractBaseException extends RuntimeException {
	private static final long serialVersionUID = 8342235231880246631L;
	
	protected BaseResponseCode responseCode;
	protected Object[] args;
	
	public AbstractBaseException() {
		
	}
	
	public AbstractBaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}
	
	public BaseResponseCode getResponseCode() {
		return responseCode;
	}
	
	public Object[] getArgs() {
		return args;
	}
	

}
