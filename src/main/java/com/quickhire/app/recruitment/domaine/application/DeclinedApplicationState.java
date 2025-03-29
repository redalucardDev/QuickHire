package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.Message;
import com.quickhire.app.recruitment.domaine.ResumeId;
import com.quickhire.app.recruitment.domaine.interview.InterviewDuration;
import com.quickhire.app.recruitment.domaine.interview.Interviews;
import com.quickhire.app.recruitment.domaine.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;

public record DeclinedApplicationState(Message declineMessage) implements ApplicationState {
  public DeclinedApplicationState {
    Assert.notNull("declineMessage", declineMessage);
  }

  @Override
  public Application scheduleInterview(LocalDateTime localDateTime, InterviewDuration duration, ResumeId resumeId, Interviews interviews) {
    throw new IllegalStateException("Application is already declined");
  }

  @Override
  public Offer accept(Message offerMessage, JobId jobId) {
    throw new IllegalStateException("Application is already declined");
  }

  @Override
  public Application decline(Message declineMessage, ResumeId resumeId) {
    throw new IllegalStateException("Application is already declined");
  }
}
