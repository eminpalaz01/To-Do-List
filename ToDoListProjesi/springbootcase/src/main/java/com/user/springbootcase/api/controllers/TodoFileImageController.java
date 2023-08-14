package com.user.springbootcase.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.springbootcase.business.abstracts.TodoFileImageService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.entities.TodoFileImage;

@RestController
@RequestMapping("/api/v1/image")
public class TodoFileImageController {

	private final TodoFileImageService todoFileImageService;

	public TodoFileImageController(TodoFileImageService todoFileImageService) {
		this.todoFileImageService = todoFileImageService;
	}

	@PostMapping
	public ResponseEntity<DataResult<TodoFileImage>> save(@RequestBody TodoFileImage todoFileImage) {
		return ResponseEntity.ok(todoFileImageService.save(todoFileImage));
	}

	@GetMapping
	public ResponseEntity<DataResult<List<TodoFileImage>>> saveAll(@RequestBody List<TodoFileImage> todoFileImages) {
		return ResponseEntity.ok(todoFileImageService.saveAll(todoFileImages));
	}

}
