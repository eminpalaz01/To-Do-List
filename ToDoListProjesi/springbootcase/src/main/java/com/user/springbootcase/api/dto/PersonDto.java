package com.user.springbootcase.api.dto;

import java.io.Serializable;


public class PersonDto implements Serializable{

	/*
	 * Default
	 */
	private static final long serialVersionUID = -8677004241049246214L;
	
	private Integer id;
	private String username;
	private String password;
	private String role;
	
	public PersonDto() {}
	
	public PersonDto(Integer id, String username, String password, String role) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
