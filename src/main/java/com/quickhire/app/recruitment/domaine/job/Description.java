package com.quickhire.app.recruitment.domaine.job;

import com.quickhire.app.shared.error.domain.Assert;

public record Description(String value) {
  public Description {
    Assert.notNull("value", value);
  }
}
