package com.todo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.todo.entity.TodoEntity;

@Repository
public class TodoDao {

	@Autowired
	EntityManager em;

//	データベースにデータを登録する
	public void insertTodoDB(TodoEntity todoEntity) {
		em.persist(todoEntity);
	}	
	
//	全てのデータを返す
	@SuppressWarnings("unchecked")
	public List<TodoEntity> findAllTask() {
		
		StringBuilder findAllTaskQuery = new StringBuilder();
		
		findAllTaskQuery.append("SELECT TODO_ID,"
							+"TODO_TITLE,"
							+"CAST(TODO_DATE AS DATE) AS TODO_DATE,"
							+ "TODO_TIME,"
							+ "TODO_PLACE,"
							+ "COMPLETE_FLAG,"
							+ "DELETE_FLAG,"
							+ "COMPLETE_CHECK "
							+ "FROM TODO");
		
		List<TodoEntity> taskList
		  = (List<TodoEntity>)em.createNativeQuery(findAllTaskQuery.toString(),TodoEntity.class).getResultList();
			
		return taskList;

	}
	
//	一つのデータを返す
	public TodoEntity getOne(int id) {
		StringBuilder findTask = new StringBuilder();
		
		findTask.append("SELECT * FROM TODO WHERE TODO_ID = ?1");
		
		TodoEntity task = (TodoEntity)em.createNativeQuery(findTask.toString(), TodoEntity.class).setParameter(1,  id).getSingleResult();
		
		return task;
	}
	
//	public TodoEntity findOneTask(int id) {
//		TodoEntity task = em.find(TodoEntity.class, id);
//		
//		return task;
//	}
//	
//	public void insertTask(TodoEntity todoEntity) {
//		em.persist(todoEntity);
//	}
	
//	更新
	public void todoUpdate(TodoEntity todoEntity) {
		
		StringBuilder updateTask = new StringBuilder();
		
		updateTask.append("UPDATE todo SET"
						+ " TODO_TITLE = ?1," 
						+ " TODO_DATE = ?2," 
						+ " TODO_TIME = ?3," 
						+ " TODO_PLACE = ?4" 
						+ " WHERE" 
						+ " TODO_ID = ?5");
		
		em.createNativeQuery(updateTask.toString(), TodoEntity.class)
									.setParameter(1, todoEntity.getTodoTitle())
									.setParameter(2, todoEntity.getTodoDate())
									.setParameter(3, todoEntity.getTodoTime())
									.setParameter(4, todoEntity.getTodoPlace())
									.setParameter(5, todoEntity.getTodoId())
									.executeUpdate();
	}
	
	
//	削除
	public void deleteState(int id) {
		
		StringBuilder task = new StringBuilder();
		
		task.append("UPDATE todo SET delete_flag = true WHERE todo_id =?1");
		
		em.createNativeQuery(task.toString(), TodoEntity.class).setParameter(1, id).executeUpdate();
	}
	
//	完了
	public void completeState(int id) {
		StringBuilder task = new StringBuilder();
		
//		task.append("UPDATE todo SET complete_flag = true WHERE todo_id = ?1");
		
		task.append("UPDATE todo SET complete_flag = CASE complete_flag " 
					+ "WHEN 0 THEN true "
					+ "ELSE false END "
					+ "WHERE todo_id = ?1");
		
				em.createNativeQuery(task.toString(), TodoEntity.class).setParameter(1, id)
				.executeUpdate();

	}
}
