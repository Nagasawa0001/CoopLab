package core.iservice;

import core.entity.Project;
import core.entity.join.ProjectLanguageCategory;

public interface IProjectService {

	public ProjectLanguageCategory getProjectList();
	public void postProject(Project form);
	public void deleteProject(long projectId);
}
