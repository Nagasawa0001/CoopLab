package core.entity.join;

import java.util.List;

import core.entity.ChildTask;
import core.entity.ParentTask;
import lombok.Data;

@Data
public class ParentTaskChildTask {

	private ParentTask parentTask;
	private List<ChildTask> childTaskList;
}
