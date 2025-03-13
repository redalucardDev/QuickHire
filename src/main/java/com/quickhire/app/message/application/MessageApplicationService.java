package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.providers.MessageSender;
import com.quickhire.app.message.domain.MessageSendingMode;

class MessageApplicationService {

  private final MessageSender messageSender;

  MessageApplicationService(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  boolean sendMessage(Message message, MessageSendingMode messageSendingMode) {
    return messageSender.send(message, messageSendingMode);
  }


}
