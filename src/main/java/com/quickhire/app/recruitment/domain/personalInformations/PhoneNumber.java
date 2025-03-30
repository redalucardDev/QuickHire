package com.quickhire.app.recruitment.domain.personalInformations;

import com.quickhire.app.shared.error.domain.Assert;

public record PhoneNumber(String value) {
  public PhoneNumber {
    Assert.notNull("value", value);
  }
}
