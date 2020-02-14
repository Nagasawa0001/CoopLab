package core.entity;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class TopicComment {

	private long id;
	private String content;
	private long topicId;
	private Timestamp created_at;
	private Timestamp updated_at;
}
