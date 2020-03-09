package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.ParentTask;

@Mapper
public interface TaskMapper {
	//プロジェクト一覧を取得
	@Select("SELECT * FROM parentTasks WHERE projectId = #{projectId}")
	List<ParentTask> selectParentTaskList(@Param("projectId") long projectId);
}
