package core.iservice;

import java.util.List;

import core.entity.Topic;
import core.entity.TopicComment;

public interface ITopicService {

	List<Topic> getTopicList();
	public void postTopic(Topic form);
	public void deleteTopic(long topicId);

	TopicComment getTopicComment(long topicId);
	public void postTopicComment(TopicComment form);
	public void deleteTopicComment(long topicCommentId);

}
