package com.user.springbootcase.core.utilities.concretes;

public class DataResult<T> extends Result {

	private T data;

	public DataResult(T data, boolean success) {
		super(success);
		this.data = data;
	}

	public DataResult(boolean success, String message) {
		super(success, message);
		this.data = null;
	}
	
	public DataResult(T data, boolean success, String message) {
		super(success, message);
		this.data = data;
	}

	public T getData() {
		return this.data;
	}

}
