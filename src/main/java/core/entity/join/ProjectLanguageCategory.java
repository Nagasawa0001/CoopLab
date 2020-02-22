package core.entity.join;

import java.util.List;

import core.entity.Category;
import core.entity.Language;
import lombok.Data;

@Data
//プロジェクト一覧 + カテゴリマスタ + 言語マスタ
public class ProjectLanguageCategory {
	private List<ProjectList> projectList;
	private List<Language> languageList;
	private List<Category> categoryList;
}
