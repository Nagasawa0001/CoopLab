package core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.entity.Topic;
import core.entity.TopicComment;
import core.iservice.ITopicService;
import core.mapper.TopicMapper;

@Service
@Transactional
public class TopicService implements ITopicService {

	@Autowired
	private TopicMapper topicMapper;

//	@Autowired
//	private CommentRepository commentRepository;

	@Override
	public List<Topic> getTopicList() {
		return topicMapper.getTopicList();
	}

	@Override
	public void postTopic(Topic form) {
		topicMapper.postTopic(form.getTitle(), form.getContent(), form.getCategory_tag_id());
	}

	@Override
	public void deleteTopic(long topicId) {
		topicMapper.deleteTopic(topicId);
	}

	@Override
	public TopicComment getTopicComment(long topicId) {
		return topicMapper.getTopicCommentList(topicId);
	}

	@Override
	public void postTopicComment(TopicComment form) {
		topicMapper.postTopicComment(form.getContent(), form.getTopicId());
		topicMapper.addCommentNumber(form.getTopicId());
	}

	@Override
	public void deleteTopicComment(long topicCommentId) {
		topicMapper.deleteTopicComment(topicCommentId);
	}

}
