package com.quickhire.app.recruitment.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record ResumeId(UUID id) {
  public ResumeId {
    Assert.notNull("id", id);
  }
  public static ResumeId newId() {
    return new ResumeId(UUID.randomUUID());
  }
}
