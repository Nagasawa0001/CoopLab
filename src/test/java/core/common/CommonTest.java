package core.common;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonTest {

	private Common common;

	@Before
	public void setUp() {
		common = new Common();
	}

	@Test
	public void generatePasswordTest() {
		for(int i=0; i<10; i++) {
			assertThat(common.generatePassword(), is("aaaa"));
		}

	}
}
