package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

import java.util.UUID;

record JobId(UUID jobId) {

  JobId {
    Assert.notNull("id", jobId);
  }
}
