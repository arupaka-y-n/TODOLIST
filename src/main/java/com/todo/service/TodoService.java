package com.todo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.dao.TodoDao;
import com.todo.entity.TodoEntity;

@Service
@Transactional
public class TodoService {

	@Autowired
	TodoDao todoDao;
	
	
//	データを登録するためのメソッド
	public void insertTodo(TodoEntity todoEntity) {
		todoDao.insertTodoDB(todoEntity);
	}
	
	public void todoUpdate(TodoEntity todoEntity) {
		todoDao.todoUpdate(todoEntity);
	}
	
	public TodoEntity getOneTask(int id) {
		return todoDao.getOne(id);
	}
	
	public List<TodoEntity> getAllTask() {
		return todoDao.findAllTask();
	}
	
	public void deleteTask(int id){
		todoDao.deleteState(id);
	}
	
	public void completeTask(int id){
		todoDao.completeState(id);
	}
}
