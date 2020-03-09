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
	@Select("SELECT projects.id, projects.title, projects.discription, projects.currentUser, projects.administratorId, users.name AS administratorName, projects.createdDate "
			+ "FROM projects "
			+ "INNER JOIN users "
			+ "ON projects.administratorId = users.id "
			+ "WHERE projects.id = #{id}")
	public Project selectProjectDetail(@Param("id") long id);

	//プロジェクト名前検索結果
	@Select("SELECT projects.id, projects.title, projects.discription, projects.currentUser, projects.currentUser, projects.progressStatus, projects.createdDate "
			+ "FROM projects "
			+ "LEFT OUTER JOIN users_projects "
			+ "ON projects.id = users_projects.projectId "
			+ "INNER JOIN users "
			+ "ON users_projects.userId = users.id "
			+ "WHERE users_projects.userId = #{userId}"
			+ "&& projects.title LIKE '%${title}%'")
	public List<Project> searchProjectByTitle(@Param("userId")long userId, @Param("title")String title);

	//プロジェクト登録
	@Insert("INSERT INTO projects "
			+ "(title, discription, administratorId) "
			+ "VALUES "
			+ "(#{title}, #{discription}, #{administratorId}")
	public void insertProject(@Param("title") String title, @Param("discription") String discription,
							@Param("administratorId") long administratorId);

	//プロジェクト削除
	@Delete("DELETE FROM projects WHERE id = #{id}")
	public void deleteProject(@Param("id")long id);
}
