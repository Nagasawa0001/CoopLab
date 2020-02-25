package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.Language;

@Mapper
public interface LanguageMapper {
	//言語一覧を取得
	@Select("SELECT * FROM languages")
	List<Language> selectLanguageList();

	//プロジェクトに紐付く言語取得
	@Select("SELECT projects_languages.languageId AS id, languages.name, languages.imageURL "
			+ "FROM projects_languages "
			+ "INNER JOIN languages "
			+ "ON projects_languages.languageId = languages.id "
			+ "WHERE projects_languages.projectId = #{projectId};")
	public List<Language> selectLanguageByProject(@Param("projectId") long projectId);
}
