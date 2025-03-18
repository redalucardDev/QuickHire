package com.quickhire.app.candidature.domain;

import com.quickhire.app.shared.contactInfo.domain.ContactInfo;
import com.quickhire.app.shared.error.domain.Assert;

import java.util.UUID;

public record Candidate(CandidateId candidateId, ContactInfo contactInfo, Situation situation,
                        ResumeId resumeId, ProspectId prospectId) {


  public enum Situation {
    EMPLOYED, STUDENT, SELF_EMPLOYED, UNEMPLOYED
  }

  public record ResumeId(UUID resumeId) {

    public ResumeId {
      Assert.notNull("resumeId", resumeId);
    }
  }

  public record ProspectId(UUID prospectId) {
    public ProspectId {
      Assert.notNull("prospectId", prospectId);
    }
  }

  public record CandidateId(UUID candidateId) {

    public CandidateId {
      Assert.notNull("candidateId", candidateId);
    }
  }
}
