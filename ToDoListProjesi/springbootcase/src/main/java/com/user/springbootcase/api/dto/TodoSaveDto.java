package com.user.springbootcase.api.dto;

import java.util.List;

public class TodoSaveDto {

	private Integer id;
	private String title;
	private String description;
	private String personName;
	private List<String> tags;

	public TodoSaveDto() {

	}

	public TodoSaveDto(Integer id, String title, String description, String personName, List<String> tags) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.personName = personName;
		this.tags = tags;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}



	
}
