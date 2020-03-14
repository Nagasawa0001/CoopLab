package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.TaskComment;
import core.service.TaskCommentService;

@RestController
@RequestMapping(path = "/comment")
@CrossOrigin(origins = {"*"})
public class TaskCommentController {

	@Autowired
	TaskCommentService taskCommentService;

	// コメント投稿
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createTaskComment(TaskComment form) {
		taskCommentService.createTaskComment(form);
	}

	// コメント更新
	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public void updateTaskComment(TaskComment form) {
		taskCommentService.udpateTaskComment(form);
	}

	// コメント削除
	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTaskComment(@RequestParam(name = "id", required = false) long id) {
		taskCommentService.deleteTaskComment(id);
	}
}
