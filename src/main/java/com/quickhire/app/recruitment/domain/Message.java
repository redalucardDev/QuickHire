package com.quickhire.app.recruitment.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record Message(String value) {
  public Message {
    Assert.notNull("value", value);
  }
}
