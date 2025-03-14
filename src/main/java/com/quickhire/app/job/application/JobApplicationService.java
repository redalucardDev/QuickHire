package com.quickhire.app.job.application;

import com.quickhire.app.job.domain.Job;
import com.quickhire.app.job.domain.JobId;
import com.quickhire.app.job.domain.repositories.JobRepository;

public class JobApplicationService {


  private final JobRepository jobRepository;

  public JobApplicationService(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  public void create(Job job) {
      jobRepository.save(job);
  }

  public Job getJobBy(JobId jobId) {
    return jobRepository.findById(jobId);
  }
}
