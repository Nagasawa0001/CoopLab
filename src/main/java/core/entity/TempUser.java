package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TempUser {

	private long id;
	private String name;
	private String email;
	private String password;
	private String introduce;
	private Timestamp createDate;
	private Timestamp updateDate;
	private String uuid;
}
