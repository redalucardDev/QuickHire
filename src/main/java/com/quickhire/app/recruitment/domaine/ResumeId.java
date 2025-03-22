package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record ResumeId(UUID value) {
  public ResumeId {
    Assert.notNull("value", value);
  }
  public static ResumeId newId() {
    return new ResumeId(UUID.randomUUID());
  }
}
