package com.quickhire.app.job.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class JobTest {

  @Test
  void shouldGetjob() {
    UUID id = UUID.randomUUID();
    Job job = new Job(
      new Job.JobId(id),
      new JobTitle("Software Engineer"),
      new JobDetails(
        new JobSalary(BigDecimal.valueOf(1000)),
        new JobDescription("This is a test job description"),
        new JobLocation("Paris", "France")
      )
    );

    assertThat(job).isEqualTo(
      new Job(
        new Job.JobId(id),
        new JobTitle("Software Engineer"),
        new JobDetails(
          new JobSalary(BigDecimal.valueOf(1000)),
          new JobDescription("This is a test job description"),
          new JobLocation("Paris", "France")
        )
      )
    );
  }
}
