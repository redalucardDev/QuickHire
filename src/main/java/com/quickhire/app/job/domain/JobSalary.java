package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.math.BigDecimal;

public record JobSalary(BigDecimal salary) {
  public JobSalary {
    Assert.notNull("salary", salary);
  }
}
