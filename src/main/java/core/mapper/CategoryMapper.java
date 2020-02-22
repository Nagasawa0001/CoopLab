package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import core.entity.Category;

@Mapper
public interface CategoryMapper {
	//カテゴリ一覧を取得
	@Select("SELECT * FROM categories")
	List<Category> selectCategoryList();
}
