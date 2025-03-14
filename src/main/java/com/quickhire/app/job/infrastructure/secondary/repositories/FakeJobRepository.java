package com.quickhire.app.job.infrastructure.secondary.repositories;

import com.quickhire.app.job.domain.Job;
import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.job.domain.repositories.JobRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakeJobRepository implements JobRepository {

  private final Map<JobId, Optional<Job>> jobs = new HashMap<>();

  @Override
  public void save(Job job) {
    jobs.put(job.jobId(), Optional.of(job));
  }

  @Override
  public Optional<Job> findById(JobId jobId) {
    return jobs.get(jobId);
  }
}
