package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record OfferId(UUID id) {
  public OfferId {
    Assert.notNull("id", id);
  }
  public static OfferId newId() {
    return new OfferId(UUID.randomUUID());
  }
}
