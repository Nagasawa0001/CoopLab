package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Project {

	private long id;
	private String title;
	private String discription;
	private long currentUser;
	private long administratorId;
	private String administratorName;
	private long progressStatus;
	private boolean isAccepted;
	private Timestamp createdDate;
	private Timestamp updatedDate;
}
