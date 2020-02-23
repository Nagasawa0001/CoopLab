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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.Comment;

@RestController
@RequestMapping(path = "/chat")
@CrossOrigin(origins = {"*"})
public class CommentController {

	@Autowired

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Comment> getCommentList() {
		return null;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postComment(@RequestBody @Validated Comment form) {
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteComment(long id) {
	}
}
