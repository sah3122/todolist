package kr.or.connect.todo.api;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.domain.Todo;
import kr.or.connect.todo.service.TodoService;


@RestController
@RequestMapping("/api/todos")
public class TodoController {
	private final TodoService service;
	private final Logger log = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	public TodoController(TodoService service){
		this.service = service;
	}
	
	@GetMapping
	public Collection<Todo> readList(){
		return service.findAll();
	}
	
	@GetMapping("/{gubun}")
	public Collection<Todo> selectList(@PathVariable String gubun){
		 return	gubun.equals("active") ? service.activeAll() : service.completeAll();
	}
	
	@GetMapping("/left")
	public Collection<Todo> leftCnt(){
		return service.leftCnt();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Todo create(@RequestBody Todo todo) {
		Todo newTodo = service.create(todo);
		return todo; 
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @RequestBody Todo todo) {
		todo.setId(id);
		service.update(todo);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@DeleteMapping("/clear")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCompleted() {
		service.deleteCompleted();
	}
}
