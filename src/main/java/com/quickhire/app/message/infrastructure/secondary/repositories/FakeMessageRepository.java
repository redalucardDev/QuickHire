package com.quickhire.app.message.infrastructure.secondary.repositories;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.message.domain.repositories.MessageRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeMessageRepository implements MessageRepository {

  Map<MessageId, Message> messages = new HashMap<>();

  @Override
  public void save(Message message) {
      messages.put(message.messageId(), message);
  }

  @Override
  public Message findById(MessageId messageId) {
    return messages.get(messageId);
  }
}
