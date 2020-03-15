package core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommonMapper {

	// 該当メールアドレスのユーザー存在チェック
	@Select("SELECT EXISTS(SELECT * FROM users WHERE email=#{email})")
	public boolean isExistEmail(@Param("email")String email);

	// 該当トークンのユーザー存在チェック
	@Select("SELECT EXISTS(SELECT * FROM users WHERE token=#{token})")
	public boolean isExistToken(@Param("token")String token);
}
