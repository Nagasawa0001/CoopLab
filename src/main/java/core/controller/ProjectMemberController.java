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

import core.entity.ProjectMember;

@RestController
@RequestMapping(path = "/project/member")
@CrossOrigin(origins = {"*"})
public class ProjectMemberController {

	@Autowired

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProjectMember> getProjectMemberList() {
		return null;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProjectMember getProjecMember(@RequestParam(name = "id", required = false)long id) {
		return null;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postProjectMember(@RequestBody @Validated ProjectMember form) {
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProjectMember(long id) {
	}
}
