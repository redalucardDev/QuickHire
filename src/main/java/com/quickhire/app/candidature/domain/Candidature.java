package com.quickhire.app.candidature.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;
import java.util.UUID;

record Candidature(
  CandidatureId candidatureId,
  JobId jobId,
  Candidate.CandidateId candidateId,
  CandidateResponse candidateResponse,
  CandidatureStatus status,
  CreatedAt createdAt
) {
  public Candidature {
    Assert.notNull("candidatureId", candidatureId);
    Assert.notNull("jobId", jobId);
    Assert.notNull("candidateId", candidateId);
    Assert.notNull("candidateResponse", candidateResponse);
    Assert.notNull("status", status);
    Assert.notNull("createdAt", createdAt);
  }

  public record JobId(UUID jobId) {
    public JobId {
      Assert.notNull("jobId", jobId);
    }
  }

  public record CandidatureId(UUID candidatureId) {}

  public record CreatedAt(LocalDateTime createdAt) {
    public CreatedAt {
      Assert.notNull("createdAt", createdAt);
    }
  }
}
