package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

import java.util.UUID;

public record JobId(UUID jobId) {

  public JobId {
    Assert.notNull("id", jobId);
  }
}
