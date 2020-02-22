package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProjectMember {

	private long id;
	private long projectId;
	private long userId;
	private String userName;
	private boolean joinApproval;
	private long adminId;
	private Timestamp createDate;
	private Timestamp updateDate;
}
