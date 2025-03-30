package com.quickhire.app.recruitment.domain.application;

import com.quickhire.app.recruitment.domain.Message;
import com.quickhire.app.recruitment.domain.ResumeId;
import com.quickhire.app.recruitment.domain.interview.InterviewDuration;
import com.quickhire.app.recruitment.domain.interview.Interviews;
import com.quickhire.app.recruitment.domain.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PendingApplication {

  private final ApplicationId applicationId;
  private final JobId jobId;
  private final ResumeId resumeId;
  private final Interviews interviews;

  private PendingApplication(ApplicationId applicationId, JobId jobId, ResumeId resumeId, Interviews interviews) {
    Assert.notNull("jobId", jobId);
    Assert.notNull("resume", resumeId);
    Assert.notNull("interviews", interviews);
    this.applicationId = applicationId;
    this.jobId = jobId;
    this.resumeId = resumeId;
    this.interviews = interviews;
  }

  public JobId jobId() {
    return jobId;
  }

  public ResumeId resume() {
    return resumeId;
  }

  public static ApplicationBuilder builder() {
    return new ApplicationBuilder();
  }

  public PendingApplication scheduleInterview(LocalDateTime localDateTime) {
    return scheduleInterview(localDateTime, InterviewDuration.ONE_HOUR);
  }

  public PendingApplication scheduleInterview(LocalDateTime localDateTime, InterviewDuration duration) {
    return PendingApplication.builder()
      .applicationId(ApplicationId.newId())
      .resumeId(resumeId)
      .jobId(JobId.newId())
      .interviews(interviews.schedule(localDateTime, duration));
  }

  public Offer accept(Message offerMessage) {
    Assert.notNull("offerMessage", offerMessage);
    if (interviews.size() != 2) {
      throw new IllegalStateException("2 interviews must be scheduled for this application to be accepted");
    }
    return new AcceptedApplication(offerMessage, jobId).offer();
  }

  public DeclinedApplication decline(Message declineMessage) {
    return new DeclinedApplication(declineMessage);
  }

  public Interviews interviews() {
    return interviews;
  }

  public PendingApplication interviews(Interviews interviews) {
    return new PendingApplication(applicationId, jobId, resumeId, interviews);
  }

  public static class ApplicationBuilder implements ApplicationIdBuilder, JobIdBuilder, ResumeIdBuilder {

    private ApplicationId applicationId;
    private JobId jobId;
    private ResumeId resumeId;

    @Override
    public ResumeIdBuilder applicationId(ApplicationId applicationId) {
      this.applicationId = applicationId;

      return this;
    }

    @Override
    public JobIdBuilder resumeId(ResumeId resumeId) {
      this.resumeId = resumeId;
      return this;
    }

    @Override
    public PendingApplication jobId(JobId jobId) {
      this.jobId = jobId;
      return new PendingApplication(applicationId, jobId, resumeId, new Interviews(new ArrayList<>(2)));
    }
  }

  public interface ApplicationIdBuilder {
    ResumeIdBuilder applicationId(ApplicationId applicationId);
  }

  public interface ResumeIdBuilder {
    JobIdBuilder resumeId(ResumeId resumeId);
  }

  public interface JobIdBuilder {
    PendingApplication jobId(JobId jobId);
  }
}
