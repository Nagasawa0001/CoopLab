package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.Project;

@Mapper
public interface ProjectMapper {

	//プロジェクトリスト取得
	@Select("SELECT projects.id, projects.title, projects.discription, projects.currentUser, projects.currentUser, projects.progressStatus, projects.createdDate "
			+ "FROM projects "
			+ "LEFT OUTER JOIN users_projects "
			+ "ON projects.id = users_projects.projectId "
			+ "INNER JOIN users "
			+ "ON users_projects.userId = users.id "
			+ "WHERE users_projects.userId = #{userId}")
	public List<Project> selectProjectList(@Param("userId")long userId);

	//プロジェクト詳細を取得（ユーザー名も取得）
	@Select("SELECT projects.id, projects.title, projects.discription, projects.requireNumber, projects.administratorId, projects.currentNumber, users.name AS administratorName, projects.createdDate "
			+ "FROM projects "
			+ "INNER JOIN users "
			+ "ON projects.administratorId = users.id "
			+ "WHERE projects.id = #{id}")
	public Project selectProjectDetail(@Param("id") long id);

	//プロジェクト登録
	@Insert("INSERT INTO projects "
			+ "(title, discription, requireNumber, administratorId, progressStatus, password) "
			+ "VALUES "
			+ "(#{title}, #{discription}, #{requireNumber}, #{administratorId}, 0, #{password})")
	public void insertProject(@Param("title") String title, @Param("discription") String discription,
							@Param("requireNumber") long requireNumber, @Param("administratorId") long administratorId,
							@Param("password") String password);
	// プロジェクト/カテゴリ登録
	@Insert("INSERT INTO projects_categories (projectId, categoryId) VALUES (#{projectId}, #{categoryId})")
	public void insertProjectCategory(@Param("projectId") long projectId, @Param("categoryId") long categoryId);

	// プロジェクト/言語登録
	@Insert("INSERT INTO projects_languages (projectId, languageId) VALUES (#{projectId}, #{languageId});")
	public void insertProjectLanguage(@Param("projectId") long projectId, @Param("languageId") long languageId);

	@Delete("DELETE FROM projects WHERE id=#{id}")
	public void deleteProject(@Param("id") long id);

}
