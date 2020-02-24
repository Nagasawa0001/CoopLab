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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.TempUser;
import core.entity.User;
import core.service.UserService;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin(origins = {"*"})
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/profile")
	@ResponseStatus(HttpStatus.OK)
	public User getProfile(@RequestParam(name = "id") @Validated long id) {

		return userService.getUser(id);
	}

	@PostMapping("/temp")
	@ResponseStatus(HttpStatus.CREATED)
	public void postTempUser(@RequestBody @Validated TempUser form) {
		userService.createTempUser(form);
	}

	@PostMapping("/{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void postUser(@PathVariable(name = "uuid", required = false) String uuid) {
		userService.createUser(uuid);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@RequestBody @Validated long id) {
		userService.deleteUser(id);
	}
}
