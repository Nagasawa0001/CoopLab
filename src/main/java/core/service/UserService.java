package core.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	public User getUser(long id) {
		return userMapper.selectUser(id);
	}

	public void createTempUser(TempUser form) {
		if(commonMapper.isExistEmail(form.getEmail())) {
			UUID uuid = UUID.randomUUID();
			String strUUID = uuid.toString();

			userMapper.insertTempUser(form.getName(), form.getEmail(), passwordEncoder.encode(form.getPassword()), strUUID);

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(form.getEmail());
			message.setSubject("ユーザー登録の手続き");
			message.setText("ご登録いただいたメールアドレスへ認証メールをお送りいたしました。"
					+ "下記URLよりユーザー認証を行ってください。"
					+ "http://localhost:8080/users/validate/" + strUUID);
			mailSender.send(message);
		}
	}

	public void createUser(String uuid) {
		TempUser tempUser = userMapper.selectTempUser(uuid);
		if(tempUser != null) {
			userMapper.insertUser(tempUser.getName(), tempUser.getEmail(), tempUser.getPassword());
			userMapper.deleteTempUser(uuid);
		} else {
			throw new RuntimeException();
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

	// パスワードトークン作成 + メール送信
	public String sendResetMail(User form) {
		UUID uuid = UUID.randomUUID();
		String token = uuid.toString();
		if(commonMapper.isExistEmail(form.getEmail())) {
			userMapper.updateToken(token, form.getId());
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(form.getEmail());
			message.setSubject("パスワード再設定の手続き");
			message.setText("パスワード再設定の認証メールをお送りいたしました。"
					+ "下記URLよりユーザー認証を行ってください。"
					+ "http://localhost:8080/users/token/" + token);
			mailSender.send(message);
		}
		return token;
	}

	// 新パスワード送信
	public void updatePassword(User form) {
		if(commonMapper.isExistToken(form.getToken())) {
			userMapper.updatePassword(form.getPassword(), form.getToken());
			userMapper.deleteToken(form.getId());
		}
	}

	// プロフィール編集
	public void updateUser(User form) {
		userMapper.updateUser(form.getName(), form.getEmail(), form.getId());
	}
}
