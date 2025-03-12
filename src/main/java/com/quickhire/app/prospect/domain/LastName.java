package com.quickhire.app.prospect.domain;

import com.quickhire.app.shared.error.domain.Assert;

record LastName(String lastName) {

    public LastName {
      Assert.notBlank("lastName", lastName);

    }
}
