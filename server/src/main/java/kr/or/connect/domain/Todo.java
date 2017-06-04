package kr.or.connect.domain;

import java.sql.Timestamp;

public class Todo {
	private Integer id;
	private String todo;
	private Integer completed;
	private Timestamp date;
	private Integer cnt;
	
	public Todo() {
	}
	
	public Todo(Integer id, String todo, Integer completed, Timestamp date) {
		this.id = id;
		this.todo = todo;
		this.completed = completed;
		this.date = date;
	}

	public Integer getCnt() {
		return cnt;
	}

	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public Integer getCompleted() {
		return completed;
	}

	public void setCompleted(Integer completed) {
		this.completed = completed;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", todo=" + todo + ", completed=" + completed + ", date=" + date + "]";
	}
	
	
}
