package com.quickhire.app.job.providers;

import com.quickhire.app.job.domain.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class JobProvider {

  public static Job createJob(UUID id) {
    Job.JobId jobId = new Job.JobId(id);
    JobTitle jobTitle = new JobTitle("Software Engineer");
    JobDetails jobDetails = new JobDetails(
      new JobSalary(BigDecimal.valueOf(50000)),
      new JobDescription("This is a Software developer job description"),
      new JobLocation("Paris", "France")
    );
    return new Job(jobId, jobTitle, jobDetails, new Job.CandidatureIds(List.of()));
  }
}
