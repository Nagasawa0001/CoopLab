package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.ProjectMember;

@Mapper
public interface ProjectMemberMapper {
	//プロジェクト一覧を取得
	@Select("SELECT *"
			+ "FROM projectMembers "
			+ "WHERE projectMembers.projectId=#{projectId};")
	List<ProjectMember> selectProjectMember(@Param("projectId") long projectId);

	@Insert("INSERT INTO projectMembers "
			+ "(projectId, userId, userName adminId) "
			+ "VALUES "
			+ "(#{projectId}, #{userId}, #{userName}, #{adminId})")
	public void insertProjectMember(@Param("projectId") long projectId, @Param("userId") long userId, @Param("userName") long userName, @Param("adminId") long adminId);
}
