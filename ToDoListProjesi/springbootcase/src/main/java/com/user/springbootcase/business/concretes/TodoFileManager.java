package com.user.springbootcase.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.springbootcase.business.abstracts.TodoFileService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.dataAccess.TodoFileDao;
import com.user.springbootcase.entities.TodoFile;

@Service
public class TodoFileManager implements TodoFileService{
	
	private final TodoFileDao todoFileDao;

    public TodoFileManager(TodoFileDao todoFileDao) {
        this.todoFileDao = todoFileDao;
    }

    @Override
    public DataResult<TodoFile> save(TodoFile todoFile) {
    	TodoFile savedTodoFile = todoFileDao.save(todoFile);
    	
    	 if (savedTodoFile != null) {
    		 return new DataResult<TodoFile>(savedTodoFile, true, "Todo dosyası kaydedildi.");
    	 }else {
    		 return new DataResult<TodoFile>(false, "Todo dosyası kaydedilemedi.");
    	 }
    }

	@Override
	public DataResult<List<TodoFile>> saveAll(List<TodoFile> todoFiles) {
		  List<TodoFile> savedTodoFiles = todoFileDao.saveAll(todoFiles);
	        
	        if (!savedTodoFiles.isEmpty()) {
	            return new DataResult<List<TodoFile>>(savedTodoFiles, true ,"Todo dosyaları kaydedildi.");
	        } else {
	            return new DataResult<List<TodoFile>>(false, "Todo dosyaları kaydedilemedi.");
	        }
	}
}
