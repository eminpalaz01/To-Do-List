package com.user.springbootcase.api.dto;

import com.user.springbootcase.entities.Todo;

public class TodoFileDto {
	
    private Integer id;
    private String fileName;
    private String fileType;
    private Todo todo;
    
	public TodoFileDto() {}

	public TodoFileDto(Integer id, String fileName, String fileType, Todo todo) {
		this.id = id;
		this.fileName = fileName;
		this.fileType = fileType;
		this.todo = todo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}
	
	
    
}
