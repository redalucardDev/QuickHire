package com.quickhire.app.job.application;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quickhire.app.job.domain.*;
import com.quickhire.app.job.domain.repositories.JobRepository;
import com.quickhire.app.job.infrastructure.secondary.repositories.FakeJobRepository;
import com.quickhire.app.job.providers.JobProvider;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class JobApplicationServiceTest {

  JobRepository jobRepository = new FakeJobRepository();
  JobApplicationService jobApplicationService = new JobApplicationService(jobRepository);

  @Test
  void shouldGetJob() {
    Job.JobId jobId = new Job.JobId(UUID.randomUUID());
    JobTitle jobTitle = new JobTitle("Software Engineer");
    JobDetails jobDetails = new JobDetails(
      new JobSalary(BigDecimal.valueOf(50000)),
      new JobDescription("This is a Software developer job description"),
      new JobLocation("Paris", "France")
    );
    Job job = JobProvider.createJob(jobId.jobId());

    Job expectedJob = new Job(jobId, jobTitle, jobDetails, new Job.CandidatureIds(List.of()));

    assertThat(jobApplicationService.create(job)).isEqualTo(expectedJob);
    assertThat(jobApplicationService.getJobBy(jobId)).isEqualTo(expectedJob);
  }
}
