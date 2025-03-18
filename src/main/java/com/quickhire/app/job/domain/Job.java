package com.quickhire.app.job.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.List;
import java.util.UUID;

public record Job(JobId jobId, JobTitle jobTitle, JobDetails jobDetails, CandidatureIds candidatureIds) {
  public Job {
    Assert.notNull("jobId", jobId);
    Assert.notNull("jobTitle", jobTitle);
    Assert.notNull("jobDetails", jobDetails);
  }

  public record JobId(UUID jobId) {
    public JobId {
      Assert.notNull("id", jobId);
    }
  }

  public record CandidatureIds(List<CandidatureId> candidatureIds) {
    public CandidatureIds {
      Assert.notNull("candidatureIds", candidatureIds);
    }
  }
  public record CandidatureId(UUID candidatureId) {
    public CandidatureId {
      Assert.notNull("candidatureId", candidatureId);
    }
  }
}
