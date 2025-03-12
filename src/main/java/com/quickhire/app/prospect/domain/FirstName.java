package com.quickhire.app.prospect.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record FirstName(String firstName) {

  public FirstName {
    Assert.notBlank("firstName", firstName);
  }
}
