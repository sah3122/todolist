package kr.or.connect.persistance;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.domain.Todo;
import kr.or.connect.todo.TodoApplication;
import kr.or.connect.todo.persistence.TodoDao;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class)
@Transactional
public class TodoDaoTest {
	@Autowired
	private TodoDao	todoDao;
	
	@Test
	public void shouldSelectAll() {
		List<Todo> todoList = todoDao.selectAll();
		assertThat(todoList, is(notNullValue()));
	}
	
	@Test
	public void shouldActiveAll() {
		List<Todo> activeList = todoDao.activeAll();
		assertThat(activeList, is(notNullValue()));
	}
	
	@Test
	public void shouldCompleteAll() {
		List<Todo> completeList = todoDao.completeAll();
		assertThat(completeList, is(notNullValue()));
	}
	
	@Test
	public void shouldLeftCount() {
		List<Todo> count = todoDao.leftCnt();
	}
	
	@Test
	public void shouldInsertAndSelect() {
		Timestamp time = new Timestamp(0);
		Todo todo = new Todo(1, "insertTest", 0, time);

		Integer id = todoDao.insert(todo);

		List<Todo> todoList = todoDao.selectAll();
		assertThat(todoList, is(notNullValue()));
	}
	
	@Test
	public void shouldDelete() {
		Timestamp time = new Timestamp(0);
		Todo todo = new Todo(1, "insertTest", 0, time);
		
		Integer id = todoDao.insert(todo);

		int affected = todoDao.deleteById(id);

		assertThat(affected, is(1));
	}
	
	@Test
	public void shouldDeleteCompleted() {
		Timestamp time = new Timestamp(0);
		Todo todo = new Todo(1, "insertTest", 1, time);
		
		Integer id = todoDao.insert(todo);

		int affected = todoDao.deleteCompleted();

		assertThat(affected, is(1));
	}
	
	@Test
	public void shouldUpdate() {
		Timestamp time = new Timestamp(0);
		Todo todo = new Todo(1, "insertTest", 0, time);

		Integer id = todoDao.insert(todo);

		todo.setId(id);
		todo.setTodo("test");
		
		int affected = todoDao.update(todo);

		assertThat(affected, is(1));
		
		List<Todo> todoList = todoDao.selectAll();
		assertThat(todoList, is(notNullValue()));
		
	}

}
