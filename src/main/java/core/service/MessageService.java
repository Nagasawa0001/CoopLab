package core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.mapper.MessageMapper;

@Service
public class MessageService {
	@Autowired
	MessageMapper messageMapper;

	public void updateMessageStatus(long id, long toUserId) {
		messageMapper.updateMessageStatus(id, toUserId);
	}
}
