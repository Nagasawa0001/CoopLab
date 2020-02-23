package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.Admin;

@Mapper
public interface AdminMapper {

		@Insert("INSERT INTO admin "
				+ "(projectId, option1, option2, option3, option4, option5, creatorId) "
				+ "VALUES "
				+ "(#{projectId}, #{option1}, #{option2}, #{option3}, #{option4}, #{option5}, #{creatorId})")
		public void insertAdmin(@Param("projectId") long projectId, @Param("option1") long option1,
								@Param("option2") long option2, @Param("option3") long option3, @Param("option4") long option4,
								@Param("option5") long option5, @Param("creatorId") long creatorId);

		@Select("SELECT * FROM admin WHERE projectId=#{projectId}")
		public List<Admin> selectAdminByProject(@Param("projectId") long projectId);

		@Delete("DELETE FROM admin WHERE id=#{id}")
		public void deleteAdmin(@Param("id") long id);
}
