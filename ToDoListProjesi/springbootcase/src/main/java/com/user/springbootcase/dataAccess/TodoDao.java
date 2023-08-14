package com.user.springbootcase.dataAccess;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.springbootcase.entities.Todo;

@Repository
public interface TodoDao extends JpaRepository<Todo, Integer> {
	List<Todo> findByPersonUsername(String username);
	List<Todo> findByTagsNameAndPersonUsername(String tagName, String username);
	List<Todo> findByTagsNameAndTitleContainingAndPersonUsername(String tagName,String title, String username);
	List<Todo> findByTitleContainingAndPersonUsername(String title,String username);
}
