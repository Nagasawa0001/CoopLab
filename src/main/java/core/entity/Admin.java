package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Admin {

	private long id;
	private long projectId;
	private long option1;
	private long option2;
	private long option3;
	private long option4;
	private long option5;
	private long creatorId;
	private Timestamp createDate;
}
