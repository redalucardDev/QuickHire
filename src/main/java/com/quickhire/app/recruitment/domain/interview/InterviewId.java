package com.quickhire.app.recruitment.domain.interview;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record InterviewId(UUID id) {
  public InterviewId {
    Assert.notNull("id", id);
  }

  public static InterviewId newId() {
    return new InterviewId(UUID.randomUUID());
  }
}
