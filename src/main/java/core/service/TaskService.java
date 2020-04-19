package core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entity.ChildTask;
import core.entity.ParentTask;
import core.entity.join.ChildTaskTaskComment;
import core.entity.join.ParentTaskChildTask;
import core.mapper.TaskCommentMapper;
import core.mapper.TaskMapper;

@Service
@Transactional
public class TaskService {

	@Autowired
	TaskMapper taskMapper;

	@Autowired
	TaskCommentMapper taskCommentMapper;

	// 親タスク詳細取得
	public ParentTaskChildTask getParentTask(long parentTaskId, long status) {
		ParentTaskChildTask taskInfo = new ParentTaskChildTask();
		taskInfo.setParentTask(taskMapper.selectParentTask(parentTaskId));
		taskInfo.setChildTaskList(taskMapper.selectChildTaskList(parentTaskId, status));
		return taskInfo;
	}

	// 親タスク作成
	public void createParentTask(ParentTask form) {
		taskMapper.insertParentTask(form.getProjectId(), form.getTitle(), form.getContent(), form.getCreatorId());
	}

	// 親タスク更新
	public void updateParentTask(ParentTask form) {
		taskMapper.updateParentTask(form.getTitle(), form.getContent(), form.getId());
	}

	// 親タスクステータス更新(Done or Cancel)
	public void updateParentTaskStatus(ParentTask form) {
		taskMapper.updateParentTaskStatus(form.getId(), form.getStatus());
	}

	// 子タスク詳細取得 + タスクコメント一覧取得
	public ChildTaskTaskComment getChildTask(long childTaskId) {
		ChildTaskTaskComment childTaskInfo = new ChildTaskTaskComment();
		childTaskInfo.setChildTask(taskMapper.selectChildTask(childTaskId));
		childTaskInfo.setTaskCommentList(taskCommentMapper.selectComment(childTaskId));
		return childTaskInfo;
	}

	// 子タスク作成
	public void createChildTask(ChildTask form) {
		taskMapper.insertChildTask(form.getParentTaskId(), form.getTitle(), form.getContent(), form.getCreatorId());
	}

	// 子タスク更新
	public void updateChildTask(ChildTask form) {
		taskMapper.updateChildTask(form.getTitle(), form.getContent(), form.getId());
	}

	// 子タスクステータス更新(Done or Cancel)
	public void updateChildTaskStatus(ChildTask form) {
		System.out.println(form);
		taskMapper.updateChildTaskStatus(form.getId(), form.getStatus());
	}
}
