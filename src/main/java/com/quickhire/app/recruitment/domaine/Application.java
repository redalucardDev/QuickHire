package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.recruitment.domaine.job.Job;
import com.quickhire.app.shared.error.domain.Assert;

public record Application(Job job, Resume resume) {
  public Application {
    Assert.notNull("job", job);
    Assert.notNull("resume", resume);
  }
}
