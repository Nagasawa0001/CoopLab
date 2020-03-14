package core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.entity.Message;
import core.mapper.MessageMapper;
import core.mapper.ProjectMapper;

@Service
public class MessageService {
	@Autowired
	MessageMapper messageMapper;

	@Autowired
	ProjectMapper projectMapper;

	public void updateMessageStatus(Message form) {
		messageMapper.updateMessageStatus(form.getId(), form.getToUserId());
		projectMapper.updateSUMCurrentUser(form.getToUserId());
		projectMapper.acceptProjectUser(form.getToUserId(), form.getProjectId());
	}
}
