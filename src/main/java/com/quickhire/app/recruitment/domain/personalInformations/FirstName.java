package com.quickhire.app.recruitment.domain.personalInformations;

import com.quickhire.app.shared.error.domain.Assert;

public record FirstName(String value) {
  public FirstName {
    Assert.notBlank("value", value);
  }
}
