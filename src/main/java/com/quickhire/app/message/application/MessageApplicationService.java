package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.providers.MessageSender;

public class MessageApplicationService {

  private final MessageSender messageSender;

  MessageApplicationService(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  boolean sendMessage(Message message, Message.MessageSendingMode messageSendingMode) {
    return messageSender.send(message, messageSendingMode);
  }


}
