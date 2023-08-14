package com.user.springbootcase.business.abstracts;

import java.util.List;

import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.entities.TodoFileImage;

public interface TodoFileImageService {

	DataResult<TodoFileImage> save(TodoFileImage todoFile);
	DataResult<List<TodoFileImage>> saveAll(List<TodoFileImage> todoFiles);
}
