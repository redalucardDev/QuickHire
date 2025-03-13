package com.quickhire.app.message.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record Body(String body) {

  public Body {
    Assert.notNull("body", body);
  }

}
