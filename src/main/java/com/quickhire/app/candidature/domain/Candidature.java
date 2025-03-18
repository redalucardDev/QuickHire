package com.quickhire.app.candidature.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

record Candidature(CandidatureId candidatureId, JobId jobId, Candidate.CandidateId candidateId, CandidateResponse candidateResponse) {
  public record JobId(UUID jobId) {
    public JobId {
      Assert.notNull("jobId", jobId);
    }
  }

  public record CandidatureId(UUID candidatureId) {}
}
