package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.secondary.EmailSender;
import com.quickhire.app.message.secondary.EmailSenderStub;


class MessageSenderImpl implements MessageSender {


  private final EmailSender emailSender = new EmailSenderStub();

  MessageSenderImpl(String senderContact) {
  }


  @Override
  public boolean send(Message message, MessageSendingMode mode) {

    if (mode.equals(MessageSendingMode.EMAIL)) {
      return emailSender.send(message);
    }

    return false;
  }


}
