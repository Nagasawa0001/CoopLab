package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Chat {

	private long id;
	private long projectId;
	private long resUserId;
	private String content;
	private long creatorId;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
