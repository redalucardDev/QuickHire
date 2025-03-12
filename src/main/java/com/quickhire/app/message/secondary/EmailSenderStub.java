package com.quickhire.app.message.secondary;

import com.quickhire.app.message.domain.Message;

public class EmailSenderStub implements EmailSender {


  @Override
  public boolean send(Message message) {
    return true;
  }
}
