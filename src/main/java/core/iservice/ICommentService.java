package core.iservice;

import java.util.List;

import core.entity.Comment;

public interface ICommentService {
	List<Comment> getComment(long projectId);
	public void postComment(Comment form);
	public void deleteComment(long commentId);
}
