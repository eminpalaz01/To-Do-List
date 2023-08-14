package com.user.springbootcase.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.user.springbootcase.entities.TodoFileImage;
 
@Repository
public interface TodoFileImageDao extends JpaRepository<TodoFileImage, Integer>{

}
