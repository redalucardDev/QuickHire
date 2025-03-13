package com.quickhire.app.message.domain;

 import com.quickhire.app.shared.error.domain.Assert;

import java.util.UUID;

public record MessageId(UUID messageId) {
  public MessageId {
    Assert.notNull("messageId", messageId);
  }
}
