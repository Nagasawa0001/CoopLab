package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Comment {

	private long id;
	private String content;
	private long projectId;
	private long projectMemberId;
	private Timestamp createdDate;
}
