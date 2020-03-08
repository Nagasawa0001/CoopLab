package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ParentTask {

	private long id;
	private long parentTaskId;
	private String title;
	private String content;
	private long creatorId;
	private long progressStatus;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
