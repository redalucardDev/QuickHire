package com.quickhire.app.recruitment.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record CandidateId(UUID id) {
  public CandidateId {
    Assert.notNull("id", id);
  }
  public static CandidateId newId() {
    return new CandidateId(UUID.randomUUID());
  }
}
