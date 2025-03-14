package com.quickhire.app.job.domain.repositories;

import com.quickhire.app.job.domain.Job;
import com.quickhire.app.job.domain.JobId;

import java.util.Optional;

public interface JobRepository {

  void save(Job job);
  Optional<Job> findById(JobId jobId);
}
