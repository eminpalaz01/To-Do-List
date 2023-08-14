package com.user.springbootcase.core.utilities.concretes;

public class TokenDataResult<T> extends Result {
	private T token;

	public TokenDataResult(T token, boolean success) {
		super(success);
		this.token = token;
	}

	public TokenDataResult(boolean success, String message) {
		super(success, message);
		this.token = null;
	}
	
	public TokenDataResult(T token, boolean success, String message) {
		super(success, message);
		this.token = token;
	}

	public T getToken() {
		return this.token;
	}
}
