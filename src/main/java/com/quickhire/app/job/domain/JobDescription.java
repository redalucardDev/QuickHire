package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record JobDescription(String description) {
  public JobDescription {
    Assert.notNull("description", description);
  }
}
