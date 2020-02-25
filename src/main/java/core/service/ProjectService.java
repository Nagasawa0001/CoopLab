package core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entity.Category;
import core.entity.Language;
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

	@Autowired
	CategoryMapper categoryMapper;

	@Autowired
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
	public Project getProjectDetail(long projectId) {
		Project project = projectMapper.selectProjectDetail(projectId);
		List<Category> categoryList = categoryMapper.selectCategoryByProject(projectId);
		List<Language> languageList = languageMapper.selectLanguageByProject(projectId);

		project.setCategoryId1(categoryList.get(0).getId());
		project.setCategoryName1(categoryList.get(0).getName());
		project.setCategoryId2(categoryList.get(1).getId());
		project.setCategoryName2(categoryList.get(1).getName());
		project.setLanguageId1(languageList.get(0).getId());
		project.setLanguageName1(languageList.get(0).getName());
		project.setLanguageName1(languageList.get(0).getImageURL());
		project.setLanguageId2(languageList.get(1).getId());
		project.setLanguageName2(languageList.get(1).getName());
		project.setLanguageId3(languageList.get(2).getId());
		project.setLanguageName3(languageList.get(2).getName());

		return project;
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
