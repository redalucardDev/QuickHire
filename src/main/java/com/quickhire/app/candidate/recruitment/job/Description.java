package com.quickhire.app.candidate.recruitment.job;

import com.quickhire.app.shared.error.domain.Assert;

public record Description(String value) {
  public Description {
    Assert.notNull("value", value);
  }
}
