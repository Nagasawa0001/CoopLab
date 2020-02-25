package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Project {

	private long id;
	private String title;
	private String discription;
	private long requireNumber;
	private long administratorId;
	private String administratorName;
	private long progressStatus;
	private String password;
	private long currentNumber;
	private Timestamp createDate;
	private long categoryId1;
	private String categoryName1;
	private long categoryId2;
	private String categoryName2;
	private long languageId1;
	private String languageName1;
	private String imageURL;
	private long languageId2;
	private String languageName2;
	private long languageId3;
	private String languageName3;
}
