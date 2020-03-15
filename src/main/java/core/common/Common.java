package core.common;

import java.util.Arrays;
import java.util.List;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Component;

@Component
public class Common {
	// プロジェクト参加用パスワード生成
	public String generatePassword() {
		List<CharacterRule> rules = Arrays.asList(
				new CharacterRule(EnglishCharacterData.UpperCase, 2),
				new CharacterRule(EnglishCharacterData.LowerCase, 2),
				new CharacterRule(EnglishCharacterData.Digit, 2)
				);

		PasswordGenerator generator = new PasswordGenerator();
		String password = generator.generatePassword(8, rules);
		System.out.println();
		return password;
	}
}
