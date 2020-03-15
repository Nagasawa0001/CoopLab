package core.entity.join;

import java.util.List;

import core.entity.Project;
import lombok.Data;

@Data
//プロジェクト一覧 + カテゴリマスタ + 言語マスタ
public class ProjectMessage {
	private List<Project> projectList;
	private List<JoinMessage> messageList;
}
