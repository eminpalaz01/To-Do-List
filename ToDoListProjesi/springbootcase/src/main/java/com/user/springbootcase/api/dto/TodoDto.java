package com.user.springbootcase.api.dto;

import java.util.List;

public class TodoDto {

	private Integer id;
	private String title;
	private String description;
	private PersonDto person;
	private List<TagDto> tags;

	public TodoDto() {

	}

	public TodoDto(Integer id, String title, String description, PersonDto person, List<TagDto> tags) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.person = person;
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

	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(PersonDto person) {
		this.person = person;
	}

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}
}
