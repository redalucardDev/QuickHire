package com.quickhire.app.job.providers;

import com.quickhire.app.job.domain.*;
import java.math.BigDecimal;
import java.util.UUID;

public class JobProvider {

  public static Job createJob(UUID id) {
    Job.JobId jobId = new Job.JobId(id);
    JobTitle jobTitle = new JobTitle("Software Engineer");
    JobDetails jobDetails = new JobDetails(
      new JobSalary(BigDecimal.valueOf(100)),
      new JobDescription("Java Developer"),
      new JobLocation("Paris", "France")
    );
    return new Job(jobId, jobTitle, jobDetails);
  }
}
