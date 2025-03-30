package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.recruitment.domaine.application.ApplicationId;
import com.quickhire.app.recruitment.domaine.application.PendingApplication;
import com.quickhire.app.recruitment.domaine.events.DummyEventPublisher;
import com.quickhire.app.recruitment.domaine.events.EventPublisher;
import com.quickhire.app.recruitment.domaine.job.*;
import com.quickhire.app.recruitment.domaine.personalInformations.*;
import java.math.BigDecimal;
import java.util.UUID;

public class RecruitmentFixture {

  private static final EventPublisher eventPublisher = new DummyEventPublisher();

  static Job createJob() {
    return new Job(
      new JobId(UUID.randomUUID()),
      new Title("jobTitle"),
      new JobDetails(new Salary(BigDecimal.valueOf(52000)), new Description("jobDescription"), new Location("Paris", "France"))
    );
  }

  static Candidate createCandidate() {
    return Candidate.builder()
      .id(CandidateId.newId())
      .personalInformations(
        new PersonalInformations(
          new FirstName("john"),
          new LastName("doe"),
          new ContactInfo(new Email("john.doe@gmail.com"), new PhoneNumber("+33662887766"))
        )
      );
  }

  public static PendingApplication createApplicaion() {
    ResumeId resumeId = ResumeId.newId();
    return PendingApplication.builder().applicationId(ApplicationId.newId()).resumeId(resumeId).jobId(JobId.newId());
  }

  static Candidate createCandidateWithResume() {
    return createCandidate().resume(new Resume(new ResumeId(UUID.randomUUID()), "resume"));
  }
}
