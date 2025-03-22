package com.quickhire.app.recruitment.domaine.job;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record JobId(UUID id) {
  public JobId {
    Assert.notNull("id", id);
  }
  public static JobId newId() {
    return new JobId(UUID.randomUUID());
  }
}
