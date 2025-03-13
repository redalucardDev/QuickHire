package com.quickhire.app.message.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record Signature(String signature) {

  public Signature {
    Assert.notNull("signature", signature);
  }
}
