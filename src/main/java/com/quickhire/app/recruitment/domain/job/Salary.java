package com.quickhire.app.recruitment.domain.job;

import com.quickhire.app.shared.error.domain.Assert;
import java.math.BigDecimal;

public record Salary(BigDecimal value) {
  public Salary {
    Assert.notNull("id", value);
  }
}
