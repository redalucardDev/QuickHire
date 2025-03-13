package com.quickhire.app.job.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class JobTest {

  @Test
  void shouldGetjob() {
     UUID id = UUID.randomUUID();
     Job job = new Job(new JobId(id)
                     , new JobTitle("Software Engineer")
                     , new JobDetails(new JobSalary(BigDecimal.valueOf(1000))
                     , new JobDescription("This is a test job description"),
                       new JobLocation("Paris", "France")));

    assertThat(job).isEqualTo(new Job(new JobId(id)
                     , new JobTitle("Software Engineer")
                     , new JobDetails(new JobSalary(BigDecimal.valueOf(1000))
                     , new JobDescription("This is a test job description"),
                       new JobLocation("Paris", "France"))));
  }
}
