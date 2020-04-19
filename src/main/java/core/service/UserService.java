package core.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.common.Common;
import core.entity.LoginUser;
import core.entity.TempUser;
import core.entity.User;
import core.mapper.CommonMapper;
import core.mapper.UserMapper;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	MailSender mailSender;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	CommonMapper commonMapper;

	@Autowired
	Common common;

	public User getUser(long id) {
		return userMapper.selectUser(id);
	}

	public String createTempUser(TempUser form) {
		if(!commonMapper.isExistEmailTemp(form.getEmail())) {
			String token = common.generatePassword();
			userMapper.insertTempUser(form.getName(), form.getEmail(), passwordEncoder.encode(form.getPassword()), token);
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(form.getEmail());
			message.setSubject("CoopLab:ユーザー登録");
			message.setText("ユーザー情報更新用の認証メールをお送りいたしました。"
					+ "発行されたトークンを使用してユーザー登録を完了してください"
					+ "認証トークン：" + token);
			mailSender.send(message);
			return token;
		} else {
			return null;
		}
	}

	public boolean createUser(String token) {
		TempUser tempUser = userMapper.selectTempUser(token);
		if(tempUser != null) {
			userMapper.insertUser(tempUser.getName(), tempUser.getEmail(), tempUser.getPassword());
			userMapper.deleteTempUser(token);
			return true;
		} else {
			return false;
		}
	}

	public void deleteUser(long id) {
		userMapper.deleteUser(id);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = Optional.ofNullable(userMapper.findByEmail(email)).orElseThrow(() -> new UsernameNotFoundException("ログインに失敗しました"));
		return new LoginUser(user);
	}

	// パスワード認証トークン作成 + メール送信
	public boolean sendResetMail(User form) {
		if(commonMapper.isExistEmail(form.getEmail())) {
			String token = common.generatePassword();
			userMapper.updateToken(token, form.getId());
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(form.getEmail());
			message.setSubject("CoopLab:パスワード再設定");
			message.setText("パスワード再設定の認証メールをお送りいたしました。"
					+ "下記トークンを使用してパスワード更新を完了してください"
					+ "認証トークン：" + token);
			mailSender.send(message);
			return true;
		} else {
			return false;
		}
	}

	// 新パスワード送信
	public boolean updatePassword(User form) {
		if(commonMapper.isExistToken(form.getToken())) {
			userMapper.updatePassword(form.getPassword(), form.getToken());
			userMapper.deleteToken(form.getId());
			return true;
		} else {
			return false;
		}
	}

	// ユーザー更新認証トークン作成 + メール送信
	public boolean sendEditMail(User form) {
		if(commonMapper.isExistEmail(form.getEmail())) {
			String token = common.generatePassword();
			userMapper.updateToken(token, form.getId());
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(form.getEmail());
			message.setSubject("CoopLab:パスワード情報変更");
			message.setText("ユーザー情報更新用の認証メールをお送りいたしました。"
					+ "発行されたトークンを使用してユーザー情報更新を完了してください"
					+ "認証トークン：" + token);
			mailSender.send(message);
			return true;
		} else {
			return false;
		}
	}

	// プロフィール編集
	public boolean updateUser(User form) {
		if(commonMapper.isExistToken(form.getToken())) {
			userMapper.updateUser(form.getName(), form.getEmail(), form.getId());
			userMapper.deleteToken(form.getId());
			return true;
		} else {
			return false;
		}
	}
}
