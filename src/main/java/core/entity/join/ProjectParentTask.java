package core.entity.join;

import java.util.List;

import core.entity.ParentTask;
import core.entity.Project;
import lombok.Data;

@Data
public class ProjectParentTask {

	private Project project;
	private List<ParentTask> parentTasks;
}
