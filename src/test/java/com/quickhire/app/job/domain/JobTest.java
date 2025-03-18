package com.quickhire.app.job.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quickhire.app.UnitTest;
import com.quickhire.app.job.providers.JobProvider;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class JobTest {

  @Test
  void shouldGetjob() {
    UUID jobId = UUID.randomUUID();
    Job job = JobProvider.createJob(jobId);

    assertThat(job).isEqualTo(
      new Job(
        new Job.JobId(jobId),
        new JobTitle("Software Engineer"),
        new JobDetails(
          new JobSalary(BigDecimal.valueOf(50000)),
          new JobDescription("This is a Software developer job description"),
          new JobLocation("Paris", "France")
        ),
        new Job.CandidatureIds(List.of())
      )
    );
  }
}
