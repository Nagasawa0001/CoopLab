package core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.Admin;
import core.service.AdminService;

@RestController
@RequestMapping(path = "/admin")
@CrossOrigin(origins = {"*"})
public class AdminController {

	@Autowired
	AdminService adminService;

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Admin> getAdminList(@RequestParam(name = "projectId", required = false) @Validated long projectId) {
		return adminService.getAdminList(projectId);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postAdmin(@RequestBody @Validated Admin form) {
		adminService.createAdmin(form);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAdmin(long id) {
		adminService.deleteAdmin(id);
	}
}
