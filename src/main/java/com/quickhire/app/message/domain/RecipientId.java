package com.quickhire.app.message.domain;

import com.quickhire.app.shared.error.domain.Assert;

import java.util.UUID;

public record RecipientId(UUID recipientId) {

  public RecipientId {
    Assert.notNull("recipientId", recipientId);
  }
}
