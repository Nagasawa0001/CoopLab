package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import core.entity.Topic;
import core.entity.TopicComment;

@Mapper
public interface TopicMapper {

	//トピックの一覧を取得
	@Select("SELECT * FROM topics")
	List<Topic> getTopicList();

	//トピックを投稿
	@Insert("INSERT INTO topics (title, content, category_tag_id, commentNumber) values (#{title}, #{content}, #{categoryTagId}, 0)")
	public void postTopic(@Param("title") String title, @Param("content") String content, @Param("categoryTagId") long categoryTagId);

	//投稿したトピックを削除（管理者用）ß
	@Delete("Delete FROM topics WHERE id=#{id}")
	public void deleteTopic(@Param("id") long topicId);

	//トピックのコメントを取得
	@Select("SELECT * FROM topic_comments WHERE topic_id=#{topicId}")
	TopicComment getTopicCommentList(@Param("topicId") long topicId);

	//トピックのコメントを投稿
	@Insert("INSERT INTO topic_comments (content, topic_id) values (#{content}, #{topicId})")
	public void postTopicComment(@Param("content") String content, @Param("topicId") long topicId);

	@Update("UPDATE topics SET commentNumber=commentNumber+1 WHERE id=#{id}")
	public void addCommentNumber(@Param("id") long topicId);

	//トピックのコメントを削除（管理者用）
	@Delete("Delete FROM topic_comments WHERE id=#{id}")
	public void deleteTopicComment(@Param("id") long topicCommentId);
}
