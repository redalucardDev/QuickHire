package com.quickhire.app.candidate.recruitment;

import com.quickhire.app.shared.error.domain.Assert;

public record Message(String value) {
  public Message {
    Assert.notNull("value", value);
  }
}
