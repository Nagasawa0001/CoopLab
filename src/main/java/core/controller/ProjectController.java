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
import core.entity.join.ProjectMessage;
import core.service.ProjectService;

@RestController
@RequestMapping(path = "/project")
@CrossOrigin(origins = {"*"})
public class ProjectController {

	@Autowired
	ProjectService projectService;

	// プロジェクト一覧取得
	@GetMapping("/list")
	@ResponseStatus(HttpStatus.OK)
	public ProjectMessage getProjectList(@RequestParam(name = "userId", required = false)long userId) {
		System.out.println(userId);
		return projectService.getProjectList(userId);
	}

	// プロジェクト詳細取得 + 親タスク一覧取得
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Project getProjectDetail(@RequestParam(name = "id", required = false)long id) {
		System.out.println(id);
		return null;
	}

	// プロジェクト作成
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postProject(@RequestBody @Validated Project form) {
	}

	// プロジェクト削除
	@DeleteMapping("/delete/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProject(long topicId) {
	}
}
