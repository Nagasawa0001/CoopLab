package core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entity.ProjectMember;
import core.iservice.IProjectMember;
import core.mapper.ProjectMemberMapper;

@Service
public class ProjectMemberService implements IProjectMember {

	@Autowired
	ProjectMemberMapper projectMemberMapper;

	@Override
	public List<ProjectMember> getProjectMember(long projectId) {
		return projectMemberMapper.selectProjectMember(projectId);
	}

	@Override
	public void createProjectMember(ProjectMember form) {
		projectMemberMapper.insertProjectMember(form.getProjectId(), form.getUserId(), form.getUserName(), form.getAdminId());
	}

}
