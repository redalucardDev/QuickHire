package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record JobTitle(String title) {
  public JobTitle {
    Assert.notBlank("title", title);
  }
}
