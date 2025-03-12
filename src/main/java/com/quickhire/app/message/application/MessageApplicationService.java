package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;

class MessageApplicationService {

  private final MessageSender messageSender;

  MessageApplicationService(MessageSender messageSender) {
    this.messageSender = messageSender;
  }

  boolean sendMessage(Message message, MessageSendingMode messageSendingMode) {
    return  messageSender.send(message, messageSendingMode);
  }







}
