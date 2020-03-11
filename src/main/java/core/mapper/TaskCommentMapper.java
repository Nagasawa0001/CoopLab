package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import core.entity.TaskComment;

@Mapper
public interface TaskCommentMapper {
	//タスクコメント一覧を取得
	@Select("SELECT taskComments.id, taskComments.childTaskId, taskComments.content, taskComments.creatorId, users.name AS creatorName, taskComments.createdDate, taskComments.updatedDate "
			+ "FROM taskComments "
			+ "INNER JOIN users "
			+ "ON taskComments.creatorId = users.id "
			+ "WHERE taskComments.childTaskId = #{childTaskId}")
	List<TaskComment> selectComment(@Param("childTaskId") long childTaskId);

	// タスクコメント投稿
	@Insert("INSERT INTO taskComments (childTaskId, content, creatorId) "
			+ "VALUES (#{childTaskId}, #{content}, #{creatorId})")
	public void insertTaskComment(@Param("childTaskId") long childTaskId, @Param("content") String content, @Param("creatorId") long creatorId);

	// タスクコメント更新（Done or Cancel）
	@Update("UPDATE taskComments SET content = #{content} WHERE id = #{id}")
	public void updateChildTaskStatus(@Param("content") String content, @Param("id")long id);

	// タスクコメント削除
	@Delete("DELETE FROM TaskComments WHERE id=#{id}")
	public void deleteComment(@Param("id") long id);
}
