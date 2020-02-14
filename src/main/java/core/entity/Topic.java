package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class Topic {

	private long id;
	private String title;
	private String content;
	private long category_tag_id;
	private long commentNumber;
	private Timestamp created_at;
	private Timestamp updated_at;
}
