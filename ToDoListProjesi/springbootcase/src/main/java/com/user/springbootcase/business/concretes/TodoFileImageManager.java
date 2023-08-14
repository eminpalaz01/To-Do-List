package com.user.springbootcase.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.springbootcase.business.abstracts.TodoFileImageService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.dataAccess.TodoDao;
import com.user.springbootcase.dataAccess.TodoFileImageDao;
import com.user.springbootcase.entities.TodoFileImage;

@Service
public class TodoFileImageManager implements TodoFileImageService{
	
	private final TodoFileImageDao todoFileImageDao;
	private final TodoDao todoDao;
	
	public TodoFileImageManager(TodoFileImageDao todoFileImageDao, TodoDao todoDao) {
		this.todoFileImageDao = todoFileImageDao;
		this.todoDao = todoDao;
	}

	@Override
    public DataResult<TodoFileImage> save(TodoFileImage todoFile) {
		TodoFileImage savedTodoFile = todoFileImageDao.save(todoFile);
		
        if (savedTodoFile != null) {        
            return new DataResult<TodoFileImage>(savedTodoFile, true, "Resim kaydedildi.");
        } else {
            return new DataResult<TodoFileImage>(false,"Resim Kaydedilemedi.");
        }
    }

    @Override
    public DataResult<List<TodoFileImage>> saveAll(List<TodoFileImage> todoFiles) {
        List<TodoFileImage> savedTodoFiles = todoFileImageDao.saveAll(todoFiles);

        if (!savedTodoFiles.isEmpty()) {
            return new DataResult<List<TodoFileImage>>(savedTodoFiles, true , "Todo files saved successfully.");
        } else {
            return new DataResult<List<TodoFileImage>>(false,"Failed to save todo files.");
        }
    }

}
