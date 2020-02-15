package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.Comment;
import core.entity.Project;

@Mapper
public interface ProjectMapper {

	//プロジェクト一覧を取得
	@Select("SELECT * FROM projects")
	List<Project> getProjectList();

	//プロジェクトを作成を投稿
	@Insert("INSERT INTO projects ("
			+ "title, discription, requireNumber, categoryId1, categoryId2, categoryId3, categoryId4, "
			+ "categoryId5, languageId1, languageId2, languageId3, administratorId, progressStatus, password "
			+ ") values ("
			+ "#{title}, #{discription}, #{requireNumber}, #{categoryId1}, #{categoryId2}, #{categoryId3}, #{categoryId4}, "
			+ "#{categoryId5}, #{languageId1}, #{languageId2}, #{languageId3}, #{administratorId}, #{progressStatus}, #{password})")
	public void postProject(@Param("title") String title, @Param("discription") String discription, @Param("requireNumber") long requireNumber,
							@Param("categoryId1") long categoryId1, @Param("categoryId2") long categoryId2, @Param("categoryId3") long categoryId3,
							@Param("categoryId4") long categoryId4, @Param("categoryId5") long categoryId5, @Param("languageId1") long languageId1,
							@Param("languageId2") long languageId2, @Param("languageId3") long languageId3, @Param("administratorId") long administratorId,
							@Param("progressStatus") long progressStatus, @Param("password") String password);

	//プロジェクトを削除（管理者用）ß
	@Delete("Delete FROM projects WHERE id=#{id}")
	public void deleteProject(@Param("id") long projectId);

	//チャットコメントを取得
	@Select("SELECT * FROM comments WHERE topic_id=#{topicId}")
	List<Comment> getCommentList(@Param("projectId") long projectId);

	//チャットコメントを投稿
	@Insert("INSERT INTO comments (content, projectId, projectMemberId) values (#{content}, #{projectId}, #{projectMemberId})")
	public void postComment(@Param("content") String content, @Param("projectId") long projectId, @Param("projectMemberId") long projectMemberId);

	//チャットコメントを削除（管理者用）
	@Delete("Delete FROM comments WHERE id=#{id}")
	public void deleteComment(@Param("id") long commentId);
}
