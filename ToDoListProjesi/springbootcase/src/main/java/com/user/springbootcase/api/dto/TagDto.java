package com.user.springbootcase.api.dto;

import java.util.List;

public class TagDto {

	private Integer id;
	private String name;
	private List<TodoDto> todos;

	public TagDto() {
	}

	public TagDto(Integer id, String name, List<TodoDto> todos) {
		this.id = id;
		this.name = name;
		this.todos = todos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TodoDto> getTodos() {
		return todos;
	}

	public void setTodos(List<TodoDto> todos) {
		this.todos = todos;
	}
}
