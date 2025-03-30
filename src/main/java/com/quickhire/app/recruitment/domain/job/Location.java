package com.quickhire.app.recruitment.domain.job;

import com.quickhire.app.shared.error.domain.Assert;

public record Location(String city, String country) {
  public Location {
    Assert.notNull("city", city);
    Assert.notNull("country", country);
  }
}
