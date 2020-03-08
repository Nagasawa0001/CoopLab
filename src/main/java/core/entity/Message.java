package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Message {

	private long id;
	private long toUserId;
	private long fromUserId;
	private String title;
	private String content;
	private Timestamp createdDate;
	private boolean isConfirmed;
}
