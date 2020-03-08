package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.ParentTask;

@Mapper
public interface TaskMapper {
	//プロジェクト一覧を取得
	@Select("SELECT comments.id, comments.content, comments.projectId, comments.projectMemberId, projectMembers.userName, comments.createdDate "
			+ "FROM comments "
			+ "LEFT OUTER JOIN projectMembers "
			+ "ON projectMembers.id = comments.projectMemberId WHERE comments.projectId=#{projectId}")
	List<ParentTask> selectComment(@Param("projectId") long projectId);

	@Insert("INSERT INTO comments (content, projectId, projectMemberId) "
			+ "VALUES "
			+ "(#{content}, #{projectId}, #{projectMemberId})")
	public void insertComment(@Param("content") String content, @Param("projectId") long projectId, @Param("projectMemberId") long projectMemberId);

	@Delete("DELETE FROM comments WHERE id=#{id}")
	public void deleteComment(@Param("id") long id);
}
