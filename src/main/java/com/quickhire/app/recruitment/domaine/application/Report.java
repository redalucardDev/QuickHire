package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.Message;
import com.quickhire.app.shared.error.domain.Assert;

public record Report(Message message) {
  public Report {
    Assert.notNull("message", message);
    if (message.value().length() < 100) {
      throw new IllegalArgumentException("Report message must be at least 100 characters long");
    }
  }
}
