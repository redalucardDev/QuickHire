package com.quickhire.app.message.application;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.secondary.EmailSender;
import com.quickhire.app.message.secondary.EmailSenderStub;


class MessageSenderImpl implements MessageSender {


  private final EmailSender emailSender = new EmailSenderStub();
  private final String sender;
  private final String receiver;

  MessageSenderImpl(String sender, String receiver) {
    this.sender = sender;
    this.receiver = receiver;
  }


  @Override
  public boolean send(Message message, MessageSendingMode mode) {

    if (mode.equals(MessageSendingMode.EMAIL)) {
      return emailSender.send(message, sender, receiver);
    }

    return false;
  }


}
