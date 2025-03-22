package com.quickhire.app.recruitment.domaine.job;

import com.quickhire.app.shared.error.domain.Assert;

public record Title(String value) {
  public Title {
    Assert.notBlank("value", value);
  }
}
