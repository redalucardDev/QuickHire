package com.quickhire.app.interview.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record Report(String report) {
  public Report {
    Assert.notNull("report", report);
  }
}
