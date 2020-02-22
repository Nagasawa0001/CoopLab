package core.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import core.entity.Language;

@Mapper
public interface LanguageMapper {
	//言語一覧を取得
	@Select("SELECT * FROM languages")
	List<Language> selectLanguageList();
}
