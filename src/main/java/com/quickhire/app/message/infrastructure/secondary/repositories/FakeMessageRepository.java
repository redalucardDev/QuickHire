package com.quickhire.app.message.infrastructure.secondary.repositories;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.message.domain.repositories.MessageRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeMessageRepository implements MessageRepository {

  Map<MessageId, Optional<Message>> messages = new HashMap<>();

  @Override
  public void save(Message message) {
      messages.put(message.messageId(), Optional.of(message));
  }

  @Override
  public Optional<Message> findById(MessageId messageId) {
    return messages.getOrDefault(messageId, Optional.empty());
  }
}
