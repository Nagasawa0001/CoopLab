package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.Comment;
import core.service.CommentService;

@RestController
@RequestMapping(path = "/chat")
@CrossOrigin(origins = {"*"})
public class CommentController {

	@Autowired
	CommentService commentService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Comment> getCommentList(@RequestParam(name = "projectId") @Validated long projectId) {
		return commentService.getComment(projectId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postComment(@RequestBody @Validated Comment form) {
		commentService.postComment(form);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteComment(@RequestParam(name = "id") @Validated long id) {
		commentService.deleteComment(id);
	}
}
