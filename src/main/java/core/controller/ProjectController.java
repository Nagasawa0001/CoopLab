package core.controller;

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

import core.entity.Project;
import core.entity.join.ProjectLanguageCategory;
import core.service.ProjectService;

@RestController
@RequestMapping(path = "/project")
@CrossOrigin(origins = {"*"})
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ProjectLanguageCategory getProjectList() {
		return projectService.getProjectList();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Project getProjectDetail(@RequestParam(name = "id", required = false)long id) {
		System.out.println(id);
		return projectService.getProjectDetail(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postProject(@RequestBody @Validated Project form) {
		projectService.postProject(form);
	}

	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProject(long topicId) {
		projectService.deleteProject(topicId);
	}
}
