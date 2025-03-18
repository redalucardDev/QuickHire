package com.quickhire.app.candidature.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.quickhire.app.candidature.providers.CandidateProvider;
import com.quickhire.app.shared.contactInfo.domain.ContactInfo;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class CandidateTest {

  @Test
  void shouldGetCandidate() {
    Candidate.CandidateId candidateId = new Candidate.CandidateId(UUID.randomUUID());
    Candidate.ProspectId prospectId = new Candidate.ProspectId(UUID.randomUUID());
    Candidate.ResumeId resumeId = new Candidate.ResumeId(UUID.randomUUID());

    Candidate candidate = CandidateProvider.createCandidate(candidateId, resumeId, prospectId);

    assertThat(candidate).isEqualTo(
      new Candidate(
        candidateId,
        new ContactInfo(
          new ContactInfo.FirstName("john"),
          new ContactInfo.LastName("defo"),
          new ContactInfo.Email("john.defo@gmail.com"),
          new ContactInfo.PhoneNumber("")
        ),
        Candidate.Situation.EMPLOYED,
        resumeId,
        prospectId
      )
    );
  }
}
