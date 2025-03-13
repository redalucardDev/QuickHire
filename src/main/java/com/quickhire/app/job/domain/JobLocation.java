package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

record JobLocation(String city, String country) {

  JobLocation {
    Assert.notNull("city", city);
    Assert.notNull("country", country);
  }
}
