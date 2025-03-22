package com.quickhire.app.candidate.recruitment;

import com.quickhire.app.candidate.recruitment.job.Job;
import com.quickhire.app.shared.error.domain.Assert;

public record Application(Job job, Resume resume) {
  public Application {
    Assert.notNull("job", job);
    Assert.notNull("resume", resume);
  }
}
