package com.quickhire.app.job.domain.repositories;

import com.quickhire.app.job.domain.Job;

import java.util.Optional;

public interface JobRepository {

  void save(Job job);
  Optional<Job> findById(Job.JobId jobId);
}
