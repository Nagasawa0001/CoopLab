package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ProjectJoinLanguage {
	private long id;
	private String title;
	private long languageId1;
	private Timestamp createDate;
	private String name;
	private String imageURL;
}
