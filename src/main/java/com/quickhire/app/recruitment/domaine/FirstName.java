package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.shared.error.domain.Assert;

public record FirstName(String value) {
  public FirstName {
    Assert.notBlank("value", value);
  }
}
