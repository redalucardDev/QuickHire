package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.Message;
import com.quickhire.app.recruitment.domaine.ResumeId;
import com.quickhire.app.recruitment.domaine.interview.InterviewDuration;
import com.quickhire.app.recruitment.domaine.interview.Interviews;
import com.quickhire.app.recruitment.domaine.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Application {

  private final ApplicationId applicationId;
  private final JobId jobId;
  private final ResumeId resumeId;
  private final Interviews interviews;
  private final ApplicationState state;

  private Application(ApplicationId applicationId, JobId jobId, ResumeId resumeId, Interviews interviews, ApplicationState state) {
    Assert.notNull("jobId", jobId);
    Assert.notNull("resume", resumeId);
    Assert.notNull("interviews", interviews);
    this.applicationId = applicationId;
    this.jobId = jobId;
    this.resumeId = resumeId;
    this.interviews = interviews;
    this.state = state;
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

  public Application scheduleInterview(LocalDateTime localDateTime) {
    return scheduleInterview(localDateTime, InterviewDuration.ONE_HOUR);
  }

  public Application scheduleInterview(LocalDateTime localDateTime, InterviewDuration duration) {
    return state.scheduleInterview(localDateTime, duration, resumeId, interviews);
  }

  public Offer accept(Message offerMessage) {
    if (interviews.size() != 2) {
      throw new IllegalStateException("2 interviews must be scheduled for this application to be accepted");
    }

    return state.accept(offerMessage, jobId);
  }

  public Application decline(Message declineMessage) {
    return state.decline(declineMessage, resumeId);
  }

  public Interviews interviews() {
    return interviews;
  }

  public Application interviews(Interviews interviews) {
    return new Application(applicationId, jobId, resumeId, interviews, state);
  }

  public ApplicationState state() {
    return state;
  }

  public static class ApplicationBuilder implements ApplicationIdBuilder, JobIdBuilder, ResumeIdBuilder, ApplicationStateBuilder {

    private ApplicationId applicationId;
    private JobId jobId;
    private ResumeId resumeId;

    @Override
    public JobIdBuilder applicationId(ApplicationId applicationId) {
      this.applicationId = applicationId;

      return this;
    }

    @Override
    public ApplicationBuilder resumeId(ResumeId resumeId) {
      this.resumeId = resumeId;
      return this;
    }

    @Override
    public ResumeIdBuilder jobId(JobId jobId) {
      this.jobId = jobId;
      return this;
    }

    @Override
    public Application state(ApplicationState state) {
      return new Application(applicationId, jobId, resumeId, new Interviews(new ArrayList<>(2)), state);
    }
  }

  public interface ApplicationIdBuilder {
    JobIdBuilder applicationId(ApplicationId applicationId);
  }

  public interface JobIdBuilder {
    ResumeIdBuilder jobId(JobId jobId);
  }

  public interface ResumeIdBuilder {
    ApplicationStateBuilder resumeId(ResumeId resumeId);
  }

  public interface ApplicationStateBuilder {
    Application state(ApplicationState state);
  }
}
