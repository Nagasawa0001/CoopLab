package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.join.JoinMessage;

@Mapper
public interface MessageMapper {
	//ログイン
	@Select("SELECT messages.id, messages.toUserId, messages.fromUserId, users.name AS fromUserName, messages.projectId, projects.title AS projectTitle, messages.title AS messageTitle, messages.content, messages.createdDate, messages.isConfirmed "
			+ "FROM messages "
			+ "INNER JOIN users "
			+ "ON messages.fromUserId = users.id "
			+ "INNER JOIN projects "
			+ "ON messages.projectId = projects.id "
			+ "WHERE messages.toUserId = #{userId}")
	public List<JoinMessage> selectMessageList(@Param("userId")long userId);
}
