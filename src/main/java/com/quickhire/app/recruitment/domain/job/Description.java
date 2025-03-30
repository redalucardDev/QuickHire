package com.quickhire.app.recruitment.domain.job;

import com.quickhire.app.shared.error.domain.Assert;

public record Description(String value) {
  public Description {
    Assert.notNull("id", value);
  }
}
