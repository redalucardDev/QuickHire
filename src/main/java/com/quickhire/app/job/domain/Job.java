package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record Job(JobId jobId, JobTitle jobTitle, JobDetails jobDetails) {

  public Job {
    Assert.notNull("jobId", jobId);
    Assert.notNull("jobTitle", jobTitle);
    Assert.notNull("jobDetails", jobDetails);
  }
}
