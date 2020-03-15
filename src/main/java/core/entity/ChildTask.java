package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ChildTask {

	private long id;
	private long parentTaskId;
	private String title;
	private String content;
	private long creatorId;
	private String creatorName;
	private long progressStatus;
	private long status;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
