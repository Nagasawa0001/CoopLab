package core.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import core.entity.User;

@Mapper
public interface UserMapper {
	//仮ユーザー登録
	@Insert("INSERT INTO tempUsers "
			+ "(name, email, password, introduce, uuid) "
			+ "VALUES "
			+ "(#{name}, #{email}, #{password}, #{introduce}, #{uuid})")
	public void insertTempUser(@Param("name") String name, @Param("email") String email, @Param("password") String password,
								@Param("introduce") String introduce, @Param("uuid") String uuid);

	//仮ユーザー削除
	@Delete("DELETE FROM tempUsers WHERE uuid=#{uuid}")
	public void deleteTempUser(@Param("uuid") long uuid);

	//仮ユーザー取得 + 存在チェック
	@Select("SELECT * FROM tempUsers WHERE uuid=#{uuid})")
	public User selectTempUser(@Param("uuid") String uuid);

	//本ユーザー登録
	@Insert("INSERT INTO users "
			+ "(name, email, password, introduce, uuid) "
			+ "VALUES "
			+ "(#{name}, #{email}, #{password}, #{introduce})")
	public void insertUser(@Param("name") String name, @Param("email") String email, @Param("password") String password,
								@Param("introduce") String introduce);

	// 本ユーザー削除
	@Delete("DELETE FROM users WHERE id=#{id}")
	public void deleteUser(@Param("id") long id);
}
