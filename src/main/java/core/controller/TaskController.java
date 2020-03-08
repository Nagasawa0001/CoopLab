package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.ParentTask;
import core.service.TaskService;

@RestController
@RequestMapping(path = "/project/member")
@CrossOrigin(origins = {"*"})
public class TaskController {

	@Autowired
	TaskService projectMemberService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ParentTask> getProjectMemberList(@RequestParam(name = "projectId") @Validated long projectId) {
		return null;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postProjectMember(@RequestBody @Validated ParentTask form) {
	}
}
