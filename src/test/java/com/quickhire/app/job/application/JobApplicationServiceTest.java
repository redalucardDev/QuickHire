package com.quickhire.app.job.application;

import com.quickhire.app.job.domain.*;
import com.quickhire.app.job.domain.repositories.JobRepository;
import com.quickhire.app.job.infrastructure.secondary.repositories.FakeJobRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class JobApplicationServiceTest {

  JobRepository jobRepository = new FakeJobRepository();
  JobApplicationService jobApplicationService = new JobApplicationService(jobRepository);


  @Test
  void shouldGetJob() {
    Job.JobId jobId = new Job.JobId(UUID.randomUUID());
    JobTitle jobTitle = new JobTitle("Software Engineer");
    JobDetails jobDetails = new JobDetails(new JobSalary(BigDecimal.valueOf(100))
      , new JobDescription("Java Developer")
      , new JobLocation("Paris", "France"));
    Job job = new Job(jobId, jobTitle, jobDetails);

    Job expectedJob = new Job(jobId, jobTitle, jobDetails);

    assertThat(jobApplicationService.create(job)).isEqualTo(expectedJob);
    assertThat(jobApplicationService.getJobBy(jobId)).isEqualTo(expectedJob);
  }

}
