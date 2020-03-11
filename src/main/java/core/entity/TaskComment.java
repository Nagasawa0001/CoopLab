package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TaskComment {

	private long id;
	private long childTaskId;
	private String title;
	private String content;
	private long creatorId;
	private String creatorName;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
