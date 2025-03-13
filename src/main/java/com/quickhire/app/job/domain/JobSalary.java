package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

import java.math.BigDecimal;

record JobSalary(BigDecimal salary) {

  JobSalary {
    Assert.notNull("salary", salary);
  }
}
