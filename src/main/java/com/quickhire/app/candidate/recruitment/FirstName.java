package com.quickhire.app.candidate.recruitment;

import com.quickhire.app.shared.error.domain.Assert;

public record FirstName(String value) {
  public FirstName {
    Assert.notBlank("value", value);
  }
}
