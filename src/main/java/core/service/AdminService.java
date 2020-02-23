package core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entity.Admin;
import core.iservice.IAdminService;
import core.mapper.AdminMapper;

@Service
public class AdminService implements IAdminService {

	@Autowired
	AdminMapper adminMapper;

	@Override
	public void createAdmin(Admin form) {
		adminMapper.insertAdmin(form.getProjectId(), form.getOption1(), form.getOption2(), form.getOption3(), form.getOption4(), form.getOption5(), form.getCreatorId());
	}

	@Override
	public List<Admin> getAdminList(long projectId) {
		return adminMapper.selectAdminByProject(projectId);
	}

	@Override
	public void deleteAdmin(long id) {
		adminMapper.deleteAdmin(id);
	}
}
