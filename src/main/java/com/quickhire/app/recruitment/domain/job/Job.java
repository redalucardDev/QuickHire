package com.quickhire.app.recruitment.domain.job;

import com.quickhire.app.shared.error.domain.Assert;

public record Job(JobId jobId, Title jobTitle, JobDetails jobDetails) {
  public Job {
    Assert.notNull("jobId", jobId);
    Assert.notNull("jobTitle", jobTitle);
    Assert.notNull("jobDetails", jobDetails);
  }
}
