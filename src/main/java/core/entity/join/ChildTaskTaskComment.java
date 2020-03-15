package core.entity.join;

import java.util.List;

import core.entity.ChildTask;
import core.entity.TaskComment;
import lombok.Data;

@Data
public class ChildTaskTaskComment {

	private ChildTask childTask;
	private List<TaskComment> taskCommentList;
}
