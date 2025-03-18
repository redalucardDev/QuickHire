package com.quickhire.app.candidature.providers;

import com.quickhire.app.candidature.domain.Candidate;
import com.quickhire.app.shared.contactInfo.domain.ContactInfo;

public class CandidateProvider {

  public static Candidate createCandidate(Candidate.CandidateId candidateId, Candidate.ResumeId resumeId, Candidate.ProspectId prospectId) {
    return new Candidate(
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
    );
  }
}
