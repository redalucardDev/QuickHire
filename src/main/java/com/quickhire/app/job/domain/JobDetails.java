package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record JobDetails(JobSalary jobSalary, JobDescription jobDescription, JobLocation jobLocation) {
  public JobDetails {
    Assert.notNull("jobSalary", jobSalary);
    Assert.notNull("jobDescription", jobDescription);
    Assert.notNull("jobLocation", jobLocation);
  }

}
