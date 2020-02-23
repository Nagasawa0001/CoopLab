package core.iservice;

import core.entity.TempUser;
import core.entity.User;

public interface IUserService {
	public User getUser();
	public void createTempUser(TempUser form);
	public void createUser(String uuid);
	public void deleteUser(long id);
}
