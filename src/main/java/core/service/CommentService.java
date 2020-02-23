package core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entity.Comment;
import core.iservice.ICommentService;
import core.mapper.CommentMapper;

@Service
public class CommentService implements ICommentService {

	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<Comment> getComment(long projectId) {
		return commentMapper.selectComment(projectId);
	}

	@Override
	public void postComment(Comment form) {
		commentMapper.insertComment(form.getContent(), form.getProjectId(), form.getProjectMemberId());
	}

	@Override
	public void deleteComment(long id) {
		commentMapper.deleteComment(id);
	}
}
