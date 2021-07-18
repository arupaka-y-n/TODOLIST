package com.todo.form;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

import com.sun.istack.NotNull;

@Component
public class TodoForm {

	@Id
	@Column(name = "TODO_ID")
	@NotNull
	private int todoId;
	
	@Column(name = "TODO_TITLE")
	@NotNull
	@Size(max=100, message="タスクのタイトルは100文字以下で入力してください。")
	private String todoTitle;
	
	@Column(name = "TODO_DATE")
	private String todoDate;
	
	@Column(name = "TODO_TIME")
	private String todoTime;
	
	@Column(name = "TODO_PLACE")
	@Size(max=300, message="場所は300文字までしか入力できません。")
	private String todoPlace;
	
	@Column(name = "COMPLETE_CHECK")
	private boolean completeCheck;
	
	@Column(name = "COMPLETE_FLAG")
	private boolean completeFlag;

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

	public String getTodoDate() {
		return todoDate;
	}

	public void setTodoDate(String todoDate) {
		this.todoDate = todoDate;
	}

	public String getTodoTime() {
		return todoTime;
	}

	public void setTodoTime(String todoTime) {
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

	public boolean isCompleteCheck() {
		return completeCheck;
	}

	public void setCompleteCheck(boolean completeCheck) {
		this.completeCheck = completeCheck;
	}


}
