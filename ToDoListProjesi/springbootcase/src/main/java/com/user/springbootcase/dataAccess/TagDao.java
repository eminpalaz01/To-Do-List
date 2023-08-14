package com.user.springbootcase.dataAccess;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.user.springbootcase.entities.Tag;

@Repository
public interface TagDao extends JpaRepository<Tag,Integer>{
    List<Tag> findByNameIn(List<String> names);

}
