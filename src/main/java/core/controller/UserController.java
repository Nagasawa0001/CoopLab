package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.TempUser;
import core.entity.User;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = {"*"})
public class UserController {

	@Autowired

	@GetMapping("/profile")
	@ResponseStatus(HttpStatus.OK)
	public User getProfile() {
		return null;
	}

	@PostMapping("/register/temp")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerTempUser(@RequestBody @Validated TempUser form) {
	}

	@PostMapping("/register/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void registerUser(@PathVariable(name = "uuid", required = false) String uuid) {
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@RequestBody @Validated long userId) {
	}
}
