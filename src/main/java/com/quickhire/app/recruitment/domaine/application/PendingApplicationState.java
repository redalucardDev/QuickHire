package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.Message;
import com.quickhire.app.recruitment.domaine.ResumeId;
import com.quickhire.app.recruitment.domaine.interview.InterviewDuration;
import com.quickhire.app.recruitment.domaine.interview.Interviews;
import com.quickhire.app.recruitment.domaine.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;

public record PendingApplicationState(Interviews interviews) implements ApplicationState {
  public PendingApplicationState {
    Assert.notNull("interviews", interviews);
  }

  @Override
  public Application scheduleInterview(LocalDateTime localDateTime, InterviewDuration duration, ResumeId resumeId, Interviews interviews) {
    return Application.builder()
      .applicationId(ApplicationId.newId())
      .jobId(JobId.newId())
      .resumeId(resumeId)
      .state(new PendingApplicationState(interviews))
      .interviews(interviews.schedule(localDateTime, duration));
  }

  @Override
  public Offer accept(Message offerMessage, JobId jobId) {
    return new AcceptedApplicationState(offerMessage, jobId).offer();
  }

  @Override
  public Application decline(Message declineMessage, ResumeId resumeId) {
    return Application.builder()
      .applicationId(ApplicationId.newId())
      .jobId(JobId.newId())
      .resumeId(resumeId)
      .state(new DeclinedApplicationState(declineMessage));
  }
}
