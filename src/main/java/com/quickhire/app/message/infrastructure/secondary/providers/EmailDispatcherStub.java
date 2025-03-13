package com.quickhire.app.message.infrastructure.secondary.providers;

import com.quickhire.app.message.domain.Message;

public class EmailDispatcherStub implements EmailDispatcher {


  @Override
  public boolean send(Message message, String senderEmail, String recipientEmail) {
    return true;
  }
}
