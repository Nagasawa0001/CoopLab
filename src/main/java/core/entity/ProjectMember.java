package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProjectMember {

	private long id;
	private long projectId;
	private long userId;
	private long joinApproval;
	private long adminId;
	private Timestamp createDate;
	private Timestamp updateDate;
}
