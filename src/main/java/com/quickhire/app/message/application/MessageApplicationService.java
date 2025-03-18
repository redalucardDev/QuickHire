package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.providers.MessageSender;
import com.quickhire.app.message.domain.repositories.MessageRepository;

public class MessageApplicationService {

  private final MessageSender messageSender;
  private final MessageRepository messageRepository;

  public MessageApplicationService(MessageRepository messageRepository, MessageSender messageSender) {
    this.messageRepository = messageRepository;
    this.messageSender = messageSender;
  }

  boolean sendMessage(Message message, Message.MessageSendingMode messageSendingMode) {
    return messageSender.send(message, messageSendingMode);
  }

  public Message create(Message message) {
    messageRepository.save(message);
    return message;
  }
}
