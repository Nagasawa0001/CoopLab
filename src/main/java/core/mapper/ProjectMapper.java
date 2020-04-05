package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
			+ "WHERE users_projects.userId = #{userId} AND users_projects.isAccepted = true")
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
			+ "(#{title}, #{discription}, #{administratorId})")
	public void insertProject(@Param("title") String title, @Param("discription") String discription,
							@Param("administratorId") long administratorId);

	//登録したプロジェクトID, 作成者IDを取得
			@Select("SELECT id FROM projects WHERE administratorId=#{administratorId} ORDER BY id DESC LIMIT 1")
			public long selectProjectId(@Param("administratorId") long administratorId);

	//projects, users中間テーブルに登録
		@Insert("INSERT INTO users_projects (userId, projectId, isAccepted) VALUES (#{administratorId}, #{projectId}, true)")
		public void insertUsersProjects(@Param("projectId")long projectId, @Param("administratorId") long administratorId);

	//プロジェクト削除
	@Delete("DELETE FROM projects WHERE id = #{id}")
	public void deleteProject(@Param("id")long id);

	// プロジェクト更新
	@Update("UPDATE projects SET title = #{title} ,discription = #{discription} WHERE id = #{id}")
	public void updateProject(@Param("title") String title, @Param("discription") String discription, @Param("id")long id);

	// 進捗ステータス更新
	@Update("UPDATE projects SET progressStatus = #{progressStatus} WHERE id = #{id}")
	public void updateProgressStatus(@Param("progressStatus") long progressStatus, @Param("id")long id);

	// ユーザー数プラス１
	@Update("UPDATE projects SET currentUser = currentUser + 1 WHERE id = #{id}")
	public void updateSUMCurrentUser(@Param("id")long id);

	// ユーザー承認
	@Update("UPDATE users_projects SET isAccepted = true WHERE userId = #{userId} AND projectId = #{projectId}")
	public void acceptProjectUser(@Param("userId")long userId, @Param("projectId")long projectId);
}
