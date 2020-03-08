package core.entity.join;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class JoinMessage {

	private long id;
	private long toUserId;
	private long fromUserId;
	private String fromUserName;
	private long projectId;
	private String projectName;
	private String title;
	private String content;
	private Timestamp createdDate;
	private boolean isConfirmed;
}
