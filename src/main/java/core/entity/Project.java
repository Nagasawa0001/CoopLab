package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Project {

	private long id;
	private String title;
	private String discription;
	private long requireNumber;
	private long categoryId1;
	private long categoryId2;
	private long categoryId3;
	private long categoryId4;
	private long categoryId5;
	private long languageId1;
	private long languageId2;
	private long languageId3;
	private long administratorId;
	private long progressStatus;
	private String password;
	private Timestamp createDate;
	private Category category;
}
