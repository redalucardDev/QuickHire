package com.quickhire.app.message.providers;

import com.quickhire.app.message.domain.*;

import java.util.UUID;

public class MessageProvider {


  public static Message createMessage(UUID id, UUID recipientId) {

    return new Message(new MessageId(id)
      , new Template("This is a test message", "This is a test signature"), new RecipientId(recipientId));

  }
}
