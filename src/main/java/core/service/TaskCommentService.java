package core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entity.TaskComment;
import core.mapper.TaskCommentMapper;

@Service
@Transactional
public class TaskCommentService {

	@Autowired
	TaskCommentMapper taskCommentMapper;

	// コメント投稿
	public void createTaskComment(TaskComment form) {
		taskCommentMapper.insertTaskComment(form.getChildTaskId(), form.getContent(), form.getCreatorId());
	}

	// コメント更新
	public void udpateTaskComment(TaskComment form) {
		taskCommentMapper.updateTaskComment(form.getContent(), form.getId());
	}

	// コメント削除
	public void deleteTaskComment(long id) {
		taskCommentMapper.deleteComment(id);
	}
}
