package com.quickhire.app.candidature.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CandidatureTest {

  @Test
  @Disabled
  public void shouldGetACandidature() {
    Candidate.CandidateId candidateId = new Candidate.CandidateId(UUID.randomUUID());
    Candidature.JobId jobId = new Candidature.JobId(UUID.randomUUID());
    Candidature.CandidatureId candidatureId = new Candidature.CandidatureId(UUID.randomUUID());

    CandidateResponse candidateResponse = new CandidateResponse("I am highly interested in this position");

    Candidature candidature = new Candidature(candidatureId, jobId, candidateId, candidateResponse);

    assertThat(candidature).isEqualTo(new Candidature(candidatureId, jobId, candidateId, candidateResponse));
  }
}
