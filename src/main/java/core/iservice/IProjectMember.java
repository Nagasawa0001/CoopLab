package core.iservice;

import java.util.List;

import core.entity.ProjectMember;

public interface IProjectMember {
	List<ProjectMember> getProjectMember(long projectId);
	public void createProjectMember(ProjectMember form);

}
