package com.quickhire.app.message.infrastructure.secondary.providers;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.providers.MessageSender;


public class MessageSenderImpl implements MessageSender {


  private final EmailDispatcher emailDispatcher = new EmailDispatcherStub();
  private final String senderEmail;
  private final String recipientEmail;

  public MessageSenderImpl(String senderEmail, String recipientEmail) {
    this.senderEmail = senderEmail;
    this.recipientEmail = recipientEmail;
  }


  @Override
  public boolean send(Message message, Message.MessageSendingMode mode) {

    if (mode.equals(Message.MessageSendingMode.EMAIL)) {
      return emailDispatcher.send(message, senderEmail, recipientEmail);
    }

    return false;
  }


}
