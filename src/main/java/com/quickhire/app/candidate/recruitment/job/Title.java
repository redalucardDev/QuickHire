package com.quickhire.app.candidate.recruitment.job;

import com.quickhire.app.shared.error.domain.Assert;

public record Title(String value) {
  public Title {
    Assert.notBlank("value", value);
  }
}
