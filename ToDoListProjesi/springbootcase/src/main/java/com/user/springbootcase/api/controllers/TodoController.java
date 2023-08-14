package com.user.springbootcase.api.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.springbootcase.api.dto.TodoDto;
import com.user.springbootcase.api.dto.TodoSaveDto;
import com.user.springbootcase.api.dto.TodoSearchDto;
import com.user.springbootcase.business.abstracts.TodoService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

	private final TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}

	@PostMapping
	public ResponseEntity<DataResult<TodoSaveDto>> save(@RequestBody TodoSaveDto TodoSaveDto) {
		return ResponseEntity.ok(todoService.save(TodoSaveDto));
	}

	@GetMapping("/{username}")
	public ResponseEntity<DataResult<List<TodoDto>>> findByPersonUsername(@PathVariable String username) {
		return ResponseEntity.ok(todoService.findByPersonUsername(username));
	}

	@PostMapping("/{username}")
	public ResponseEntity<DataResult<List<TodoDto>>> findByTagsNameAndTitleContainingAndPersonUsername(
			@PathVariable String username, @RequestBody TodoSearchDto todoSearchDto) {
		return ResponseEntity
				.ok(todoService.findByTagsNameAndTitleContainingAndPersonUsername(todoSearchDto.getTagName(), todoSearchDto.getTitle(), username));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<DataResult<Boolean>> deleteById(
			@PathVariable Integer id) {
		return ResponseEntity
				.ok(todoService.deleteById(id));
	}

}
