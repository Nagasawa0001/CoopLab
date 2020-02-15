package core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entity.Comment;
import core.entity.Project;
import core.iservice.IProjectService;
import core.mapper.ProjectMapper;

@Service
@Transactional
public class ProjectService implements IProjectService {

	@Autowired
	private ProjectMapper projectMapper;

//	@Autowired
//	private CommentRepository commentRepository;

	@Override
	public List<Project> getProjectList() {
		return projectMapper.getProjectList();
	}

	@Override
	public void postProject(Project form) {
		projectMapper.postProject(form.getTitle(), form.getDiscription(), form.getRequireNumber(), form.getCategoryId1(), form.getCategoryId2(),
							  	form.getCategoryId3(), form.getCategoryId4(), form.getCategoryId5(), form.getLanguageId1(), form.getLanguageId2(),
								form.getLanguageId3(), form.getAdministratorId(), form.getProgressStatus(), form.getPassword());
	}

	@Override
	public void deleteProject(long projectId) {
		projectMapper.deleteProject(projectId);
	}

	@Override
	public List<Comment> getComment(long projectId) {
		return projectMapper.getCommentList(projectId);
	}

	@Override
	public void postComment(Comment form) {
		projectMapper.postComment(form.getContent(), form.getProjectId(), form.getProjectMemberId());
	}

	@Override
	public void deleteComment(long topicCommentId) {
		projectMapper.deleteComment(topicCommentId);
	}

}
