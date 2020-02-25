package core.entity.join;

import java.sql.Timestamp;

import lombok.Data;

@Data
// プロジェクト一覧画面のリスト必須カラム
public class ProjectList {
	private long id;
	private String title;
	private String imageURL;
	private long currentNumber;
	private Timestamp createdDate;
}
