package com.quickhire.app.recruitment.domaine.personalInformations;

import com.quickhire.app.shared.error.domain.Assert;

public record LastName(String value) {
  public LastName {
    Assert.notBlank("value", value);
  }
}
