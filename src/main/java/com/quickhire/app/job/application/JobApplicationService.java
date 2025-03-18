package com.quickhire.app.job.application;

import com.quickhire.app.job.domain.Job;
import com.quickhire.app.job.domain.repositories.JobRepository;

import java.util.NoSuchElementException;

public class JobApplicationService {


  private final JobRepository jobRepository;

  public JobApplicationService(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  public Job create(Job job) {
    jobRepository.save(job);
    return job;
  }

  public Job getJobBy(Job.JobId jobId) {
    return jobRepository.findById(jobId).orElseThrow(() -> new NoSuchElementException("Job not found"));
  }
}
