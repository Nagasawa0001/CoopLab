package core.service;

import org.springframework.stereotype.Service;

import core.entity.TempUser;
import core.entity.User;
import core.iservice.IUserService;

@Service
public class UserService implements IUserService {

	@Override
	public User getUser() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void createTempUser(TempUser form) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void createUser(String uuid) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void deleteUser(long id) {
		// TODO 自動生成されたメソッド・スタブ

	}
}
