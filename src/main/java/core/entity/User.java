package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class User {

	private long id;
	private String name;
	private String email;
	private String password;
	private Timestamp createdDate;
	private Timestamp updatedDate;
	private String token;
	private String jsessionId;
}
