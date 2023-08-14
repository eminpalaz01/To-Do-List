package com.user.springbootcase.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.springbootcase.business.abstracts.TodoFileService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.entities.TodoFile;

@RestController
@RequestMapping("/api/v1/file")
public class TodoFileController {

	private final TodoFileService todoFileService;

	public TodoFileController(TodoFileService todoFileService) {
		this.todoFileService = todoFileService;
	}

	@PostMapping
	public ResponseEntity<DataResult<TodoFile>> save(@RequestBody TodoFile todoFile) {		
		return ResponseEntity.ok(todoFileService.save(todoFile));
	}

	@GetMapping
	public ResponseEntity<DataResult<List<TodoFile>>> saveAll(@RequestBody List<TodoFile> todoFiles) {
		return ResponseEntity.ok(todoFileService.saveAll(todoFiles));
	}

}
