package com.quickhire.app.message.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record Template(Body body, Signature signature) {
    public record Signature(String signature) {

      public Signature {
        Assert.notNull("signature", signature);
      }
    }

    public  record Body(String body) {

      public Body {
        Assert.notNull("body", body);
      }

    }
}
