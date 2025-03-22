package com.quickhire.app.candidate.recruitment.contact;

import com.quickhire.app.shared.error.domain.Assert;

public record Email(String value) {
  public Email {
    Assert.notNull("value", value);
    if (!value.matches("([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-]+)\\.([a-zA-Z]{2,5})") && !value.isBlank()) {
      throw new IllegalArgumentException("Invalid email");
    }
  }
}
