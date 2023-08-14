package com.user.springbootcase.api.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.user.springbootcase.api.dto.TagDto;
import com.user.springbootcase.business.abstracts.TagService;
import com.user.springbootcase.core.utilities.concretes.DataResult;

@RestController
@RequestMapping("/api/v1/tag")
public class TagController {

	private final TagService tagService;

	public TagController(TagService tagService) {
		this.tagService = tagService;
	}

	@PostMapping
	public ResponseEntity<DataResult<TagDto>> save(@RequestBody TagDto tagDto) {		
		return ResponseEntity.ok(tagService.save(tagDto));
	}

	@GetMapping
	public ResponseEntity<DataResult<List<TagDto>>> findAll() {
		return ResponseEntity.ok(tagService.findAll());
	}

}
