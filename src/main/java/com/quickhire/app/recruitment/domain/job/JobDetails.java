package com.quickhire.app.recruitment.domain.job;

import com.quickhire.app.shared.error.domain.Assert;

public record JobDetails(Salary jobSalary, Description jobDescription, Location jobLocation) {
  public JobDetails {
    Assert.notNull("jobSalary", jobSalary);
    Assert.notNull("jobDescription", jobDescription);
    Assert.notNull("jobLocation", jobLocation);
  }
}
