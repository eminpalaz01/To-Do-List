package com.user.springbootcase.api.dto;

public class TodoSearchDto {
	private String tagName;
	private String title;
	
	public TodoSearchDto(){}
	
	public TodoSearchDto(String tagName, String title) {
		this.tagName = tagName;
		this.title = title;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
