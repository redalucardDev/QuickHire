package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

record JobDescription(String description) {

  JobDescription {
    Assert.notNull("description", description);
  }
}
