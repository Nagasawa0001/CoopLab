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

	public User getUser(long id) {
		return userMapper.selectUser(id);
	}

	public void createTempUser(TempUser form) {

		UUID uuid = UUID.randomUUID();
		String strUUID = uuid.toString();

		userMapper.insertTempUser(form.getName(), form.getEmail(), passwordEncoder.encode(form.getPassword()), strUUID);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(form.getEmail());
		message.setSubject("ユーザー登録の手続き");
		message.setText("ご登録いただいたメールアドレスへ認証メールをお送りいたしました。"
				+ "下記URLよりユーザー認証を行ってください。"
				+ "http://localhost:8080/users/validate/" + strUUID);
		System.out.println(message);

		mailSender.send(message);

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

//	private Collection<GrantedAuthority> getAuthorities(User user) {
//
//		if (user.getRole().equals("ROLE_ADMIN")) {
//			return AuthorityUtils.createAuthorityList("ROLE_ADMIN", "ROLE_USER");
//		} else {
//			return AuthorityUtils.createAuthorityList("ROLE_USER");
//		}
//
//	}
}
