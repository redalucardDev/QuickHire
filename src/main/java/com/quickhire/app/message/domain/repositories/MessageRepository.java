package com.quickhire.app.message.domain.repositories;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.message.domain.MessageId;

public interface MessageRepository {
  void save(Message message);
  Message findById(MessageId messageId);
}
