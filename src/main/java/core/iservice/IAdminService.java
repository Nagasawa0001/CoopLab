package core.iservice;

import java.util.List;

import core.entity.Admin;

public interface IAdminService {
	public void createAdmin(Admin form);
	public List<Admin> getAdminList(long projectId);
	public void deleteAdmin(long id);
}
