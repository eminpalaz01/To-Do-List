package com.user.springbootcase.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "todos")
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Integer id;
    
	@Column(name = "title")
    private String title;
	
	@Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    
    @OneToOne(mappedBy = "todo")
    private TodoFileImage image;
    
    @OneToMany(mappedBy = "todo" )
    private List<TodoFile> todoFiles;
   
    @ManyToMany
    @JoinTable(
    		  name = "todo_tag_map", 
    		  joinColumns = @JoinColumn(name = "todo_id"), 
    		  inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

	public Todo() {

	}
	
	public Todo(Integer id, String title, String description, Person person, List<Tag> tags, List<TodoFile> todoFiles) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.person = person;
		this.tags = tags;
		this.todoFiles = todoFiles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<TodoFile> getTodoFiles() {
		return todoFiles;
	}

	public void setTodoFiles(List<TodoFile> todoFiles) {
		this.todoFiles = todoFiles;
	}

}
