package com.user.springbootcase.business.concretes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.user.springbootcase.api.dto.TagDto;
import com.user.springbootcase.api.dto.TodoDto;
import com.user.springbootcase.api.dto.TodoSaveDto;
import com.user.springbootcase.business.abstracts.TodoService;
import com.user.springbootcase.core.utilities.concretes.DataResult;
import com.user.springbootcase.dataAccess.PersonDao;
import com.user.springbootcase.dataAccess.TagDao;
import com.user.springbootcase.dataAccess.TodoDao;
import com.user.springbootcase.entities.Person;
import com.user.springbootcase.entities.Tag;
import com.user.springbootcase.entities.Todo;

@Service
public class TodoManager implements TodoService {

	private final PersonDao personDao;
	private final TagDao tagDao;
	private final TodoDao todoDao;

	public TodoManager(PersonDao personDao, TagDao tagDao, TodoDao todoDao) {
		this.personDao = personDao;
		this.tagDao = tagDao;
		this.todoDao = todoDao;
	}

	@Override
	public DataResult<TodoSaveDto> save(TodoSaveDto todoSaveDto) {
		// TODO Auto-generated method stub
		Person person = personDao.findByUsername(todoSaveDto.getPersonName());			
        List<Tag> tags = tagDao.findByNameIn(todoSaveDto.getTags()); 
        
        if(tags == null) {
        	tags = new ArrayList<Tag>();
        }
        	
		if (person != null && todoSaveDto.getTitle() != null) {
			Todo todo = new Todo();
			todo.setId(todoSaveDto.getId());
			todo.setPerson(person);
			todo.setDescription(todoSaveDto.getDescription());
			todo.setTags(tags);
			todo.setTitle(todoSaveDto.getTitle());

			todo = todoDao.save(todo);
			
			todoSaveDto.setId(todo.getId());
            
			return new DataResult<TodoSaveDto>(todoSaveDto, true, "Todo başarılı bir şekilde kaydedildi.");
		}

		return new DataResult<TodoSaveDto>(false, "Todo Kaydedilemedi.");
	}

	@Override
	public DataResult<List<TodoDto>> findByPersonUsername(String username) {
		// TODO Auto-generated method stub
		List<Todo> todos = todoDao.findByPersonUsername(username);
		List<TodoDto> todosDto = new ArrayList<TodoDto>();

		todos.forEach(todo -> {
			TodoDto todoDto = new TodoDto();
			List<TagDto> tagsDto = new ArrayList<>();
			todoDto.setId(todo.getId());
			todo.getTags().forEach(tag -> {
				TagDto tagDto = new TagDto();
				tagDto.setId(tag.getId());
				tagDto.setName(tag.getName());
				tagsDto.add(tagDto);
			});
			todoDto.setTags(tagsDto);
			todoDto.setTitle(todo.getTitle());
			todoDto.setDescription(todo.getDescription());
			todosDto.add(todoDto);
		});
		return new DataResult<List<TodoDto>>(todosDto, true, "Kullanıcının tüm Todo ları getirildi.");
	}

	@Override
	public DataResult<List<TodoDto>> findByTagsNameAndTitleContainingAndPersonUsername(String tagName, String title,
			String username) {
		// TODO Auto-generated method stub
		List<Todo> todos = new ArrayList<Todo>();
		if (tagName != null && title != null) {
			todos = todoDao.findByTagsNameAndTitleContainingAndPersonUsername(tagName, title, username);
		}
		if (tagName == null && title != null) {
			todos = todoDao.findByTitleContainingAndPersonUsername(title, username);
		}
		if (tagName != null && title == null) {
			todos = todoDao.findByTagsNameAndPersonUsername(tagName, username);
		}

		List<TodoDto> todosDto = new ArrayList<TodoDto>();

		todos.forEach(todo -> {
			TodoDto todoDto = new TodoDto();
			List<TagDto> tagsDto = new ArrayList<>();
			todoDto.setId(todo.getId());
			todo.getTags().forEach(tag -> {
				TagDto tagDto = new TagDto();
				tagDto.setId(tag.getId());
				tagDto.setName(tag.getName());
				tagsDto.add(tagDto);
			});
			todoDto.setTags(tagsDto);
			todoDto.setTitle(todo.getTitle());
			todoDto.setDescription(todo.getDescription());
			todosDto.add(todoDto);
		});
		return new DataResult<List<TodoDto>>(todosDto, true, "Kullanıcının tüm Todo ları getirildi.");
	}

	@Override
	public DataResult<Boolean> deleteById(Integer id) {
		// TODO Auto-generated method stub
		todoDao.deleteById(id);
		
		return new DataResult<Boolean>(Boolean.TRUE, id + "'li Todo başarıyla silindi.");
	}



}
