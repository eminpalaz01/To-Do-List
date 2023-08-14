package com.user.springbootcase.business.abstracts;

import java.util.List;

import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.entities.TodoFile;

public interface TodoFileService {

	DataResult<TodoFile> save(TodoFile todoFile);
	DataResult<List<TodoFile>> saveAll(List<TodoFile> todoFile);
}
