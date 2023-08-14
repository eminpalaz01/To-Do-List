package com.user.springbootcase.business.abstracts;

import java.util.List;

import com.user.springbootcase.api.dto.TodoDto;
import com.user.springbootcase.api.dto.TodoSaveDto;
import com.user.springbootcase.core.utilities.concretes.DataResult;

public interface TodoService {
	DataResult<TodoSaveDto> save(TodoSaveDto TodoSaveDto);

	DataResult<List<TodoDto>> findByPersonUsername(String username);

	DataResult<List<TodoDto>> findByTagsNameAndTitleContainingAndPersonUsername(String tagName, String title,
			String username);

	DataResult<Boolean> deleteById(Integer id);

}
