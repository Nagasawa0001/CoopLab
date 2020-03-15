package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import core.entity.ChildTask;
import core.entity.ParentTask;

@Mapper
public interface TaskMapper {
	//親タスク一覧を取得
	@Select("SELECT * FROM parentTasks WHERE projectId = #{projectId}")
	List<ParentTask> selectParentTaskList(@Param("projectId") long projectId);

	// 親タスク詳細取得
	@Select("SELECT parentTasks.id, parentTasks.projectId, parentTasks.title, parentTasks.content, parentTasks.creatorId, users.name AS creatorName, parentTasks.progressStatus, parentTasks.createdDate, parentTasks.updatedDate "
			+ "FROM parentTasks "
			+ "INNER JOIN users "
			+ "ON parentTasks.creatorId = users.id "
			+ "WHERE parentTasks.id = #{id}")
	ParentTask selectParentTask(@Param("id") long id);

	// 子タスク一覧取得
	@Select("SELECT * FROM childTasks WHERE parentTaskId = #{parentTaskId}")
	List<ChildTask> selectChildTaskList(@Param("parentTaskId") long parentTaskId);

	// 子タスク詳細取得
	@Select("SELECT childTasks.id, childTasks.parentTaskId, childTasks.title AS parentTaskTitle, childTasks.content, childTasks.creatorId, users.name AS creatorName, childTasks.progressStatus, childTasks.createdDate, childTasks.updatedDate "
			+ "FROM childTasks "
			+ "INNER JOIN users "
			+ "ON childTasks.creatorId = users.id "
			+ "INNER JOIN parentTasks "
			+ "ON parentTasks.id = childTasks.parentTaskId "
			+ "WHERE childTasks.id = #{id}")
	ChildTask selectChildTask(@Param("id") long id);

	// 親タスク作成
	@Insert("INSERT INTO parentTasks (projectId, title, content, creatorId) "
			+ "VALUES (#{projectId}, #{title}, #{content}, #{creatorId})")
	public void insertParentTask(@Param("projectId")long projectId, @Param("title")String title, @Param("content")String content, @Param("creatorId")long creatorId);

	// 子タスク作成
	@Insert("INSERT INTO childTasks (parentTaskId, title, content, creatorId) "
			+ "VALUES (#{parentTaskId}, #{title}, #{content}, #{creatorId})")
	public void insertChildTask(@Param("parentTaskId")long parentTaskId, @Param("title")String title, @Param("content")String content, @Param("creatorId")long creatorId);

	// 親タスク編集
	@Update("UPDATE parentTasks SET title = #{title}, content = #{content} WHERE id = #{id}")
	public void updateParentTask(@Param("title")String title, @Param("content")String content, @Param("id")long id);

	// 子タスク編集
	@Update("UPDATE childTasks SET title = #{title}, content = #{content} WHERE id = #{id}")
	public void updateChildTask(@Param("title")String title, @Param("content")String content, @Param("id")long id);

	// 親タスク更新（Done or Cancel）
	@Update("UPDATE parentTasks SET status = #{status} WHERE id = #{id}")
	public void updateParentTaskStatus(@Param("status") long status, @Param("id")long id);

	// 子タスク更新（Done or Cancel）
	@Update("UPDATE childTasks SET status = #{status} WHERE id = #{id}")
	public void updateChildTaskStatus(@Param("status") long status, @Param("id")long id);

	// 親タスク進捗ステータス更新
	@Update("UPDATE parentTasks SET progressStatus = #{progressStatus} WHERE id = #{id}")
	public void updateProgressStatusInParentTask(@Param("progressStatus") long progressStatus, @Param("id")long id);


	// 子タスク進捗ステータス更新
	@Update("UPDATE childTasks SET progressStatus = #{progressStatus} WHERE id = #{id}")
	public void updateProgressStatusInChildTask(@Param("progressStatus") long progressStatus, @Param("id")long id);
}
