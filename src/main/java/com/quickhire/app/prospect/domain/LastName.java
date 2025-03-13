package com.quickhire.app.prospect.domain;

import com.quickhire.app.shared.error.domain.Assert;

record LastName(String lastName) {

    LastName {
      Assert.notBlank("lastName", lastName);

    }
}
