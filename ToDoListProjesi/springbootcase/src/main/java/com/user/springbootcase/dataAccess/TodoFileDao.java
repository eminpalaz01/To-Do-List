package com.user.springbootcase.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.springbootcase.entities.TodoFile;

@Repository
public interface TodoFileDao extends JpaRepository<TodoFile, Integer>{

}
