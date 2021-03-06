package core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entity.Project;
import core.entity.join.ProjectMessage;
import core.entity.join.ProjectParentTask;
import core.mapper.MessageMapper;
import core.mapper.ProjectMapper;
import core.mapper.TaskMapper;

@Service
@Transactional
public class ProjectService {
	@Autowired
	ProjectMapper projectMapper;

	@Autowired
	MessageMapper messageMapper;

	@Autowired
	TaskMapper taskMapper;

	public ProjectMessage getProjectList(long userId) {
		ProjectMessage listInfo = new ProjectMessage();
		listInfo.setMessageList(messageMapper.selectMessageList(userId));
		listInfo.setProjectList(projectMapper.selectProjectList(userId));
		return listInfo;
	}

	public ProjectParentTask getProjectDetail(long projectId, long status) {
		ProjectParentTask projectDetailInfo = new ProjectParentTask();
		projectDetailInfo.setProject(projectMapper.selectProjectDetail(projectId));
		projectDetailInfo.setParentTasks(taskMapper.selectParentTaskList(projectId, status));
		return projectDetailInfo;
	}

	public ProjectMessage searchProjectByTitle(long userId, String title) {
		ProjectMessage listInfo = new ProjectMessage();
		listInfo.setMessageList(messageMapper.selectMessageList(userId));
		listInfo.setProjectList(projectMapper.searchProjectByTitle(userId, title));
		return listInfo;
	}

	public void createProject(Project form) {
		projectMapper.insertProject(form.getTitle(), form.getDiscription(), form.getAdministratorId());
		long projectId = projectMapper.selectProjectId(form.getAdministratorId());
		projectMapper.insertUsersProjects(projectId, form.getAdministratorId());
	}

	public void updateProject(Project form) {
		projectMapper.updateProject(form.getTitle(), form.getDiscription(), form.getId());
	}

	public void deleteProject(long projectId) {
		projectMapper.deleteProject(projectId);
	}
}
