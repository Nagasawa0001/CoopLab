package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping(path = "/users")
@CrossOrigin(origins = {"*"})
public class UserController {

	@Autowired
	UserService userService;

	// プロフィール取得
	@GetMapping("/profile")
	@ResponseStatus(HttpStatus.OK)
	public User getProfile(@RequestParam(name = "id") @Validated long id) {

		return userService.getUser(id);
	}

	// 一時ユーザー登録
	@PostMapping("/temp")
	@ResponseStatus(HttpStatus.CREATED)
	public void postTempUser(@RequestBody @Validated TempUser form) {
		userService.createTempUser(form);
	}

	// ユーザー登録認証メールクリック時
	@GetMapping("/validate/{uuid}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void postUser(@PathVariable(name = "uuid", required = false) String uuid) {
		userService.createUser(uuid);
	}

	// ユーザー削除
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteUser(@RequestBody @Validated long id) {
		userService.deleteUser(id);
	}

	// ユーザー編集
	@PatchMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateUser(@RequestBody @Validated User form) {
		userService.updateUser(form);
	}

	// パスワード再設定の認証メール送信
	@PatchMapping("/reset/mail")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendResetMail(@RequestBody @Validated User form) {
		userService.sendResetMail(form);
	}

	// パスワード更新処理
	@PatchMapping("/reset/token/{token}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updatePassword(@RequestBody @Validated User form) {
		userService.updatePassword(form);
	}

	// ユーザー情報更新の認証メール送信
	@PatchMapping("/edit/mail")
	@ResponseStatus(HttpStatus.CREATED)
	public void sendEditMail(@RequestBody @Validated User form) {
		userService.sendResetMail(form);
	}

	// ユーザー情報更新処理
	@PatchMapping("/edit/token/{token}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEditword(@RequestBody @Validated User form) {
		userService.updatePassword(form);
	}
}
