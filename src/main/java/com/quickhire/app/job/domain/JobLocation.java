package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record JobLocation(String city, String country) {

  public JobLocation {
    Assert.notNull("city", city);
    Assert.notNull("country", country);
  }
}
