package com.quickhire.app.message.domain.repositories;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.MessageId;

import java.util.Optional;

public interface MessageRepository {
  void save(Message message);
  Optional<Message> findById(MessageId messageId);
}
