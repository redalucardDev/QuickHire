package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.Message;
import com.quickhire.app.recruitment.domaine.ResumeId;
import com.quickhire.app.recruitment.domaine.interview.InterviewDuration;
import com.quickhire.app.recruitment.domaine.interview.Interviews;
import com.quickhire.app.recruitment.domaine.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;

public class AcceptedApplicationState implements ApplicationState {

  private final Offer offer;

  public AcceptedApplicationState(Message offerMessage, JobId jobId) {
    Assert.notNull("offer", offerMessage);
    offer = new Offer(OfferId.newId(), offerMessage, jobId);
  }

  @Override
  public Application scheduleInterview(LocalDateTime localDateTime, InterviewDuration duration, ResumeId resumeId, Interviews interviews) {
    throw new IllegalStateException("Application is already accepted");
  }

  @Override
  public Offer accept(Message offerMessage, JobId jobId) {
    throw new IllegalStateException("Application is already accepted");
  }

  @Override
  public Application decline(Message declineMessage, ResumeId resumeId) {
    throw new IllegalStateException("Application is already accepted");
  }

  public Offer offer() {
    return offer;
  }
}
