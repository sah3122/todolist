package kr.or.connect.todo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import kr.or.connect.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	private TodoDao dao;
	
	public TodoService(TodoDao dao) {
		this.dao = dao;
	}
	
	public Collection<Todo> findAll() {
		return dao.selectAll();
	}
	
	public Collection<Todo> activeAll() {
		return dao.activeAll();
	}

	public Collection<Todo> completeAll() {
		return dao.completeAll();
	}
	
	public Collection<Todo> leftCnt() {
		return dao.leftCnt();
	}
	
	public Todo create(Todo todo) {
		Integer id = dao.insert(todo);
		todo.setId(id);
		return todo;
	}
	
	public boolean update(Todo todo){
		int affected = dao.update(todo);
		return affected == 1;
	}
	
	public boolean delete(Integer id) {
		int affected = dao.deleteById(id);
		return affected == 1;
	}
	
	public boolean deleteCompleted() {
		int affected = dao.deleteCompleted();
		return affected == 1;
	}
}
