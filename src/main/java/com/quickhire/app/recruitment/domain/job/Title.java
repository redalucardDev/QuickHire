package com.quickhire.app.recruitment.domain.job;

import com.quickhire.app.shared.error.domain.Assert;

public record Title(String value) {
  public Title {
    Assert.notBlank("id", value);
  }
}
