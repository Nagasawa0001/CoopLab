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
	@PostMapping("/signup/temp")
	@ResponseStatus(HttpStatus.OK)
	public String postTempUser(@RequestBody @Validated TempUser form) {
		return userService.createTempUser(form);
	}

	// ユーザー登録認証トークン入力
	@PostMapping("/validate/{token}")
	@ResponseStatus(HttpStatus.OK)
	public boolean postUser(@PathVariable(name = "token", required = false) String token) {
		return userService.createUser(token);
	}

	// ユーザー削除
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUser(@RequestBody @Validated long id) {
		userService.deleteUser(id);
	}

	// ユーザー編集
	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public boolean updateUser(@RequestBody @Validated User form) {
		return userService.updateUser(form);
	}

	// パスワード再設定の認証メール送信
	@PatchMapping("/reset/mail")
	@ResponseStatus(HttpStatus.OK)
	public boolean sendResetMail(@RequestBody @Validated User form) {
		return userService.sendResetMail(form);
	}

	// パスワード更新処理
	@PatchMapping("/reset/password/token/{token}")
	@ResponseStatus(HttpStatus.OK)
	public boolean updatePassword(@RequestBody @Validated User form) {
		return userService.updatePassword(form);
	}

	// ユーザー情報更新の認証メール送信
	@PatchMapping("/edit/mail")
	@ResponseStatus(HttpStatus.OK)
	public boolean sendEditMail(@RequestBody @Validated User form) {
		return userService.sendResetMail(form);
	}

	// ユーザー情報更新処理
	@PatchMapping("/edit/token/{token}")
	@ResponseStatus(HttpStatus.OK)
	public boolean updateEditword(@RequestBody @Validated User form) {
		return userService.updatePassword(form);
	}
}
