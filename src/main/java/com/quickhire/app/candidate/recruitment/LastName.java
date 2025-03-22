package com.quickhire.app.candidate.recruitment;

import com.quickhire.app.shared.error.domain.Assert;

public record LastName(String value) {
  public LastName {
    Assert.notBlank("value", value);
  }
}
