package core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import core.entity.Message;
import core.service.MessageService;

@RestController
@RequestMapping(path = "/message")
@CrossOrigin(origins = {"*"})
public class MessageController {

	@Autowired
	MessageService messageService;

	// メッセージ確認更新
	@PatchMapping
	@ResponseStatus(HttpStatus.OK)
	public void updateMessageStatus(@RequestBody @Validated Message form) {
		System.out.println(form);
		messageService.updateMessageStatus(form);
	}
}
