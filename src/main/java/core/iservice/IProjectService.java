package core.iservice;

import java.util.List;

import core.entity.Comment;
import core.entity.Project;
import core.entity.join.ProjectLanguageCategory;

public interface IProjectService {

	ProjectLanguageCategory getProjectList();
	public void postProject(Project form);
	public void deleteProject(long projectId);

	List<Comment> getComment(long projectId);
	public void postComment(Comment form);
	public void deleteComment(long commentId);

}
