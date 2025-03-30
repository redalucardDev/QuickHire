package com.quickhire.app.recruitment.domain.application;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record ApplicationId(UUID id) {
  public ApplicationId {
    Assert.notNull("id", id);
  }

  public static ApplicationId newId() {
    return new ApplicationId(UUID.randomUUID());
  }
}
