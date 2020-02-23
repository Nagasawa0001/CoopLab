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

import core.entity.ProjectMember;
import core.service.ProjectMemberService;

@RestController
@RequestMapping(path = "/project/member")
@CrossOrigin(origins = {"*"})
public class ProjectMemberController {

	@Autowired
	ProjectMemberService projectMemberService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProjectMember> getProjectMemberList(@RequestParam(name = "projectId") @Validated long projectId) {
		return projectMemberService.getProjectMember(projectId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postProjectMember(@RequestBody @Validated ProjectMember form) {
		projectMemberService.createProjectMember(form);
	}
}
