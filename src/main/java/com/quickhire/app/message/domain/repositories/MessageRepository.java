package com.quickhire.app.message.domain.repositories;

import com.quickhire.app.message.domain.Message;
import java.util.Optional;

public interface MessageRepository {
  void save(Message message);
  Optional<Message> findById(Message.MessageId messageId);
}
