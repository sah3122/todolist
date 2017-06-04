package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_ALL = 
			"SELECT id, todo, completed, date FROM todo order by date desc";
	static final String ACTIVE_ALL = 
			"SELECT id, todo, completed, date FROM todo WHERE completed = 0 order by date desc";
	static final String COMPLETE_ALL = 
			"SELECT id, todo, completed, date FROM todo WHERE completed = 1 order by date desc";
	static final String SELECT_LEFT = 
			"SELECT count(*) cnt  FROM todo WHERE completed = 0";
	static final String UPDATE_BY_ID = 
			"UPDATE todo SET completed = :completed WHERE id = :id";
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	static final String DELETE_COMPLETED =
			"DELETE FROM todo WHERE completed = 1";
}
