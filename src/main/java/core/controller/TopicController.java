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

import core.entity.Topic;
import core.entity.TopicComment;
import core.service.TopicService;

@RestController
@RequestMapping(path = "/topic")
@CrossOrigin(origins = {"*"})
public class TopicController {

	@Autowired
	private TopicService topicService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Topic> getTopicList() {
		return topicService.getTopicList();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postTopic(@RequestBody @Validated Topic form) {
		topicService.postTopic(form);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTopic(long topicId) {
		topicService.deleteTopic(topicId);
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public TopicComment getTopicCommentList(@RequestBody long topicId) {
		return topicService.getTopicComment(topicId);
	}

	@PostMapping("/comment")
	@ResponseStatus(HttpStatus.CREATED)
	public void postTopicComment(@RequestBody @Validated TopicComment form) {
		topicService.postTopicComment(form);
	}

	@DeleteMapping("/comment/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTopicComment(long topicCommentId) {
		topicService.deleteTopicComment(topicCommentId);
	}
}
