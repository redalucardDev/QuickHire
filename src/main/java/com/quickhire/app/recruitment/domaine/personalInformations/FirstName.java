package com.quickhire.app.recruitment.domaine.personalInformations;

import com.quickhire.app.shared.error.domain.Assert;

public record FirstName(String value) {
  public FirstName {
    Assert.notBlank("id", value);
  }
}
