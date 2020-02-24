package core.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entity.TempUser;
import core.entity.User;
import core.iservice.IUserService;
import core.mapper.UserMapper;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	UserMapper userMapper;

	@Autowired
	MailSender mailSender;

	@Override
	public User getUser(long id) {
		return userMapper.selectUser(id);
	}

	@Override
	public void createTempUser(TempUser form) {

		UUID uuid = UUID.randomUUID();
		String strUUID = uuid.toString();

		// Spring Security 導入後にエンコード設定する
		String encodedPassword = form.getPassword();

		userMapper.insertTempUser(form.getName(), form.getEmail(), encodedPassword, form.getIntroduce(), strUUID);

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("admin@cooplab.com");
		message.setTo(form.getEmail());
		message.setSubject("認証メールの受信について");
		message.setText("ご登録いただいたメールアドレスへ認証メールをお送りいたしました。"
				+ "下記URLよりユーザー認証を行ってください。"
				+ "http://localhost:8080/user/" + strUUID);
		System.out.println(message);

		mailSender.send(message);

	}

	@Override
	public void createUser(String uuid) {
		TempUser tempUser = userMapper.selectTempUser(uuid);
		if(tempUser != null) {
			userMapper.insertUser(tempUser.getName(), tempUser.getEmail(), tempUser.getPassword(), tempUser.getIntroduce());
			userMapper.deleteTempUser(uuid);
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public void deleteUser(long id) {
		userMapper.deleteUser(id);
	}
}
