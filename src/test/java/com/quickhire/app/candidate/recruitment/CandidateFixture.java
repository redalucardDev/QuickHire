package com.quickhire.app.candidate.recruitment;

import com.quickhire.app.candidate.recruitment.contact.ContactInfo;
import com.quickhire.app.candidate.recruitment.contact.Email;
import com.quickhire.app.candidate.recruitment.contact.PhoneNumber;
import com.quickhire.app.candidate.recruitment.job.*;
import java.math.BigDecimal;
import java.util.UUID;

public class CandidateFixture {

  static Job createJob() {
    return new Job(
      new JobId(UUID.randomUUID()),
      new Title("jobTitle"),
      new JobDetails(new Salary(BigDecimal.valueOf(52000)), new Description("jobDescription"), new Location("Paris", "France"))
    );
  }

  static Candidate createCandidate() {
    Candidate candidate = Candidate.builder()
      .id(CandidateId.newId())
      .personalInformations(
        new PersonalInformations(
          new FirstName("john"),
          new LastName("doe"),
          new ContactInfo(new Email("john.doe@gmail.com"), new PhoneNumber("+33662887766"))
        )
      );

    return candidate;
  }

  static Candidate createCandidateWithResume() {
    return createCandidate().resume(new Resume(new ResumeId(UUID.randomUUID()), "resume"));
  }
}
