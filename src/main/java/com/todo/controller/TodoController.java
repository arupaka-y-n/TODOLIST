package com.todo.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todo.URL;
import com.todo.entity.TodoEntity;
import com.todo.form.TodoForm;
import com.todo.service.TodoService;

@Controller
@RequestMapping(URL.TODO)
public class TodoController {
	
	@Autowired
	TodoService todoService;

//	データを登録する処理
	@PostMapping(URL.CREATE)
	public String createTodo(@Validated TodoForm todoForm, BindingResult result, Model model) {

		if(result.hasErrors()) {
			model.addAttribute("title", "タスク一覧");
			
			return URL.TODO + URL.LIST;
		}

//		加工したデータを格納するためにTodoEntityのインスタンスを作る
		TodoEntity todoEntity = new TodoEntity();
		
//		Serviceに渡すためにデータを加工する
		todoEntity.setTodoTitle(todoForm.getTodoTitle());
		
//		2021-02-06 全部で10文字
		if(todoForm.getTodoDate() != null && todoForm.getTodoDate().length() > 9) {
			todoEntity.setTodoDate(Date.valueOf(todoForm.getTodoDate()));
		}
		
//		21:34:00 全部で8文字
		if(todoForm.getTodoTime() != null && todoForm.getTodoTime().length() == 8) {
			todoEntity.setTodoTime(Time.valueOf(todoForm.getTodoTime()));
		} 
//		21:34だったら、後ろに':00'を足す
		 else if(3 < todoForm.getTodoTime().length() && todoForm.getTodoTime().length() < 7) {
			 todoEntity.setTodoTime(Time.valueOf(todoForm.getTodoTime() + ":00"));
		}
		
		todoEntity.setTodoPlace(todoForm.getTodoPlace());
		
//		Serviceを呼び出して登録するデータを渡す
		todoService.insertTodo(todoEntity);
		
		return "redirect:" + URL.TODO + URL.LIST;
	}
	
	
//	全件取得し、リスト一覧を表示する
	@GetMapping(URL.LIST)
	public String displayAllList(TodoForm todoForm, TodoEntity todoEntity, Model model) {
		
//		データを取得する
		todoService.getAllTask();
		
//		リスト型で受け取る
		List<TodoEntity> taskList = todoService.getAllTask();
		
//		HTMLでデータを使うために、HTML用の名前をつける
		model.addAttribute("title","タスク一覧");
		model.addAttribute("taskList", taskList);
		
		return URL.TODO + URL.LIST;
	}
	
//	詳細画面
	@GetMapping(URL.DETAILS)
	public String displayDetails(TodoForm todoForm, TodoEntity todoEntity, Model model) {

//		データを受け取る      データを取得する
		TodoEntity task = todoService.getOneTask(todoForm.getTodoId());
		
//		HTMLでデータを使うために、HTML用の名前をつける
		model.addAttribute("task", task);
		
		return URL.TODO + URL.DETAILS;
	}
	
//	詳細を更新する
	@PostMapping(URL.UPDATE)
	public String updateDetail(TodoForm todoForm,Model model) {
//		詳細データを更新する
		TodoEntity todoDetails = new TodoEntity();
		
		todoDetails.setTodoId(todoForm.getTodoId());
		todoDetails.setTodoTitle(todoForm.getTodoTitle());
		
//		2021-02-06 全部で10文字
		if(todoForm.getTodoDate() != null && todoForm.getTodoDate().length() > 9) {
			todoDetails.setTodoDate(Date.valueOf(todoForm.getTodoDate()));
		}
		
//		21:34:00 全部で8文字
		if(todoForm.getTodoTime() != null && todoForm.getTodoTime().length() == 8) {
			todoDetails.setTodoTime(Time.valueOf(todoForm.getTodoTime()));
		} 
//		21:34だったら、後ろに':00'を足す
		 else if(3 < todoForm.getTodoTime().length() && todoForm.getTodoTime().length() < 7) {
			todoDetails.setTodoTime(Time.valueOf(todoForm.getTodoTime() + ":00"));
		}
		
		todoDetails.setTodoPlace(todoForm.getTodoPlace());
		
//		データを更新する
		todoService.todoUpdate(todoDetails);

//		受け取る　　　　　　　　　　　　　　取得する
		List<TodoEntity> updateTask = todoService.getAllTask();
		
//		HTMLでデータを使うために、HTML用の名前をつける
		model.addAttribute("taskList", updateTask);
		
		return "redirect:" + URL.TODO + URL.LIST;
	}
	
//	タスクを削除する
	@PostMapping(URL.DELETE)
	public String deleteTask(TodoForm todoForm,TodoEntity todoEntity, Model model) {
		
//		削除フラグを追加したデータに、更新する
		todoService.deleteTask(todoForm.getTodoId());
		
//		リスト型でデータを受け取る
		List<TodoEntity> updateTask = todoService.getAllTask();
		
//		HTMLでデータを使うために、HTML用の名前をつける
		model.addAttribute("taskList", updateTask);
		
		return "redirect:" + URL.TODO + URL.LIST;
	}


//	タスクを完了する
	@PostMapping(URL.COMPLETE)
	public String completeTask(TodoForm todoForm, Model model) {


//		値を反映したデータに、更新する
		todoService.completeTask(todoForm.getTodoId());
			
//		リスト型でデータを受け取る
		List<TodoEntity> updateTask = todoService.getAllTask();
		
//		HTMLでデータを使うために、HTML用の名前をつける
		model.addAttribute("taskList", updateTask);
		
		return "redirect:" + URL.TODO + URL.LIST;
	}

}
