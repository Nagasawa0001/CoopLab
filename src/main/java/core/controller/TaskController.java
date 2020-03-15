package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.ChildTask;
import core.entity.ParentTask;
import core.entity.join.ChildTaskTaskComment;
import core.entity.join.ParentTaskChildTask;
import core.service.TaskService;

@RestController
@RequestMapping(path = "/task")
@CrossOrigin(origins = {"*"})
public class TaskController {

	@Autowired
	TaskService taskService;

	// 親タスク詳細取得 + 子タスク一覧
	@GetMapping("/parent")
	@ResponseStatus(HttpStatus.OK)
	public ParentTaskChildTask getParentTask(@RequestParam(name = "id", required = false)long id) {
		return taskService.getParentTask(id);
	}

	// 親タスク作成
	@PostMapping("/parent")
	@ResponseStatus(HttpStatus.OK)
	public void createParentTask(@RequestBody @Validated ParentTask form) {
		taskService.createParentTask(form);
	}

	// 親タスク更新
	@PatchMapping("/parent")
	@ResponseStatus(HttpStatus.OK)
	public void updateParentTask(@RequestBody @Validated ParentTask form) {
		taskService.updateParentTask(form);
	}

	// 親タスクステータス更新(Done or Cancel)
	@PatchMapping("/parent/status")
	@ResponseStatus(HttpStatus.OK)
	public void updateParentTaskStatus(@RequestBody @Validated ParentTask form) {
		taskService.updateParentTaskStatus(form);
	}

	// 子タスク一覧詳細 + タスクコメント一覧取得
	@GetMapping("/child")
	@ResponseStatus(HttpStatus.OK)
	public ChildTaskTaskComment getChildTask(@RequestParam(name = "id", required = false)long id) {
		return taskService.getChildTask(id);
	}

	// 子タスク作成
	@PostMapping("/child")
	@ResponseStatus(HttpStatus.OK)
	public void createChildTask(@RequestBody @Validated ChildTask form) {
		taskService.createChildTask(form);
	}

	// 子タスク更新
	@PatchMapping("/child")
	@ResponseStatus(HttpStatus.OK)
	public void updateChildTask(@RequestBody @Validated ChildTask form) {
		taskService.updateChildTask(form);
	}

	// 子タスクステータス更新(Done or Cancel)
	@PatchMapping("/child/status")
	@ResponseStatus(HttpStatus.OK)
	public void updateChildTaskStatus(@RequestBody @Validated ChildTask form) {
		taskService.updateChildTaskStatus(form);
	}
}
