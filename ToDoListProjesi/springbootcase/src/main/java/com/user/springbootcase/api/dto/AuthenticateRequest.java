package com.user.springbootcase.api.dto;

import java.io.Serializable;


public class AuthenticateRequest implements Serializable {
	
	/**
	 * Default
	 */
	private static final long serialVersionUID = 8959350587773545739L;
	
	private String username;
	private String password;
	
	public AuthenticateRequest() {}
	
	public AuthenticateRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
