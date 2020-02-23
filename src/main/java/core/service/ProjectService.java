package core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entity.Project;
import core.entity.join.ProjectLanguageCategory;
import core.iservice.IProjectService;
import core.mapper.CategoryMapper;
import core.mapper.LanguageMapper;
import core.mapper.ProjectMapper;

@Service
public class ProjectService implements IProjectService {

	@Autowired
	ProjectMapper projectMapper;
	CategoryMapper categoryMapper;
	LanguageMapper languageMapper;

	@Override
	public ProjectLanguageCategory getProjectList() {

		ProjectLanguageCategory listInfo = new ProjectLanguageCategory();
		listInfo.setProjectList(projectMapper.selectProjectList());
		listInfo.setCategoryList(categoryMapper.selectCategoryList());
		listInfo.setLanguageList(languageMapper.selectLanguageList());
		return listInfo;
	}

	@Override
	public void postProject(Project form) {

		projectMapper.insertProject(form.getTitle(), form.getDiscription(), form.getRequireNumber(), form.getAdministratorId(), form.getPassword());
	}

	@Override
	public void deleteProject(long projectId) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
