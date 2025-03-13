package com.quickhire.app.prospect.domain;

import com.quickhire.app.shared.error.domain.Assert;

import java.util.UUID;

public record ProspectId(UUID prospectId) {
  public ProspectId {
    Assert.notNull("prospectId", prospectId);
  }
}
