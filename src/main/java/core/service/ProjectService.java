package core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entity.Project;
import core.entity.join.ProjectMessage;
import core.mapper.MessageMapper;
import core.mapper.ProjectMapper;

@Service
public class ProjectService {
	@Autowired
	ProjectMapper projectMapper;

	@Autowired
	MessageMapper messageMapper;

	public ProjectMessage getProjectList(long userId) {

		ProjectMessage listInfo = new ProjectMessage();
		listInfo.setMessageList(messageMapper.selectMessageList(userId));
		listInfo.setProjectList(projectMapper.selectProjectList(userId));
		return listInfo;
	}

	public Project getProjectDetail(long projectId) {

		return null;
	}


	public void createProject(Project form) {
	}


	public void deleteProject(long projectId) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
