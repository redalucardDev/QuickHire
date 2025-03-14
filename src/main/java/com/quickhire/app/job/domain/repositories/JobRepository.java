package com.quickhire.app.job.domain.repositories;

import com.quickhire.app.job.domain.Job;
import com.quickhire.app.job.domain.JobId;

public interface JobRepository {

  void save(Job job);
  Job findById(JobId jobId);
}
