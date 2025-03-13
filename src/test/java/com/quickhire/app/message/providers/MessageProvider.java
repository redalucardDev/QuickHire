package com.quickhire.app.message.providers;

import com.quickhire.app.message.domain.*;
import com.quickhire.app.message.domain.Template.*;

import java.util.UUID;

public class MessageProvider {


  public static Message createMessage(UUID id, UUID recipientId) {

    return new Message(new MessageId(id)
      , new Template(new Body("This is a test message"), new Signature("This is a test signature")), new RecipientId(recipientId));

  }
}
