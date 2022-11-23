package com.lawencon.lawenconcommunity.dto;

public class ExceptionDto<T>{
	private T message;

	public T getMessage() {
		return message;
	}

	public void setMessage(T message) {
		this.message = message;
	}


}
