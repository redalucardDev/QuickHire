package com.quickhire.app.prospection.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record ProspectionId(UUID prospectionId) {
  public ProspectionId {
    Assert.notNull("prospectionId", prospectionId);
  }
}
