package com.quickhire.app.job.infrastructure.secondary.repositories;

import com.quickhire.app.job.domain.Job;
import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.job.domain.repositories.JobRepository;

public class FakeJobRepository implements JobRepository {
  @Override
  public void save(Job job) {

  }

  @Override
  public Job findById(JobId jobId) {
    return null;
  }
}
