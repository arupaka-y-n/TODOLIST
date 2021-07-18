package com.todo.entity;


import java.sql.Time;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "TODO")
public class TodoEntity {

	@Id
	@Column(name = "TODO_ID")
	@NotNull
	private int todoId;
	
	@Column(name = "TODO_TITLE")
	@NotNull
	private String todoTitle;
	
	@Column(name = "TODO_DATE")

	private Date todoDate;
	
	@Column(name = "TODO_TIME")
	private Time todoTime;
	
	@Column(name = "TODO_PLACE")
	private String todoPlace;
	
	@Column(name = "COMPLETE_CHECK")
	private boolean completeCheck;
	
	@Column(name = "COMPLETE_FLAG")
	private boolean completeFlag;
	
	public boolean isCompleteCheck() {
		return completeCheck;
	}

	public void setCompleteCheck(boolean completeCheck) {
		this.completeCheck = completeCheck;
	}

	@Column(name = "DELETE_FLAG")
	private boolean deleteFlag;

	public int getTodoId() {
		return todoId;
	}

	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}

	public String getTodoTitle() {
		return todoTitle;
	}

	public void setTodoTitle(String todoTitle) {
		this.todoTitle = todoTitle;
	}

	public Date getTodoDate() {
		return todoDate;
	}

	public void setTodoDate(Date todoDate) {
		this.todoDate = todoDate;
	}

	public Time getTodoTime() {
		return todoTime;
	}

	public void setTodoTime(Time todoTime) {
		this.todoTime = todoTime;
	}

	public String getTodoPlace() {
		return todoPlace;
	}

	public void setTodoPlace(String todoPlace) {
		this.todoPlace = todoPlace;
	}

	public boolean isCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(boolean completeFlag) {
		this.completeFlag = completeFlag;
	}

	public boolean isDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
}
