package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.Category;

@Mapper
public interface CategoryMapper {
	//カテゴリ一覧を取得
	@Select("SELECT * FROM categories")
	List<Category> selectCategoryList();

	//プロジェクトに紐付くカテゴリ取得
	@Select("SELECT projects_categories.categoryId AS id, categories.name "
			+ "FROM projects_categories "
			+ "INNER JOIN categories "
			+ "ON projects_categories.categoryId = categories.id "
			+ "WHERE projects_categories.projectId = #{projectId};")
	public List<Category> selectCategoryByProject(@Param("projectId") long projectId);
}
