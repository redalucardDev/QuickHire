package com.quickhire.app.recruitment.domaine.application;

import com.quickhire.app.recruitment.domaine.ApplicationStatus;
import com.quickhire.app.recruitment.domaine.Message;
import com.quickhire.app.recruitment.domaine.ResumeId;
import com.quickhire.app.recruitment.domaine.events.EventPublisher;
import com.quickhire.app.recruitment.domaine.events.OfferSendEvent;
import com.quickhire.app.recruitment.domaine.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Application {

  private final ApplicationId applicationId;
  private final JobId jobId;
  private final ResumeId resumeId;
  private ApplicationStatus status;
  private final Interviews interviews;
  private OfferSendEvent offerSentEvent;
  private final EventPublisher eventPublisher;

  public Application(ApplicationId applicationId, JobId jobId, ResumeId resumeId, EventPublisher eventPublisher) {
    Assert.notNull("jobId", jobId);
    Assert.notNull("resume", resumeId);
    Assert.notNull("eventPublisher", eventPublisher);
    this.applicationId = applicationId;
    this.jobId = jobId;
    this.resumeId = resumeId;
    this.status = ApplicationStatus.PENDING;
    this.eventPublisher = eventPublisher;
    this.interviews = new Interviews(new ArrayList<>(2));
  }

  public Application(ApplicationBuilder applicationBuilder) {
    Assert.notNull("jobId", applicationBuilder.jobId);
    Assert.notNull("resume", applicationBuilder.resumeId);
    Assert.notNull("eventPublisher", applicationBuilder.eventPublisher);
    this.applicationId = applicationBuilder.applicationId;
    this.jobId = applicationBuilder.jobId;
    this.resumeId = applicationBuilder.resumeId;
    this.eventPublisher = applicationBuilder.eventPublisher;
    this.status = ApplicationStatus.PENDING;
    this.interviews = new Interviews(new ArrayList<>(2));
  }

  public JobId jobId() {
    return jobId;
  }

  public ResumeId resume() {
    return resumeId;
  }

  public ApplicationStatus status() {
    return status;
  }

  public static ApplicationBuilder builder() {
    return new ApplicationBuilder();
  }

  public void status(ApplicationStatus status) {
    this.status = status;
  }

  public Interviews scheduleInterview(LocalDateTime localDateTime) {
    if (!status.equals(ApplicationStatus.PENDING)) {
      throw new IllegalStateException("Application is not pending");
    }
    return interviews.schedule(localDateTime);
  }

  public Offer accept(Message offerMessage) {
    if (!status.equals(ApplicationStatus.PENDING)) {
      throw new IllegalStateException("Application is not pending");
    }
    if (interviews.size() != 2) {
      throw new IllegalStateException("2 interviews must be scheduled for this application to be accepted");
    }
    status(ApplicationStatus.ACCEPTED);

    Offer offer = new Offer(OfferId.newId(), offerMessage, jobId);

    offerSentEvent = new OfferSendEvent(offer.offerId(), offer.offerMessage(), offer.jobId());
    eventPublisher.publish(offerSentEvent);
    return offer;
  }

  public Application reject() {
    if (!status.equals(ApplicationStatus.PENDING)) {
      throw new IllegalStateException("Application is not pending");
    }
    status(ApplicationStatus.DECLINED);
    return this;
  }

  public OfferSendEvent offerSentEvent() {
    return offerSentEvent;
  }

  public static class ApplicationBuilder implements ApplicationIdBuilder, JobIdBuilder, ResumeIdBuilder, EventPublisherBuilder {

    private ApplicationId applicationId;
    private JobId jobId;
    private ResumeId resumeId;
    private EventPublisher eventPublisher;

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
    public Application eventPublisher(EventPublisher eventPublisher) {
      this.eventPublisher = eventPublisher;
      return new Application(applicationId, jobId, resumeId, eventPublisher);
    }
  }

  public interface ApplicationIdBuilder {
    JobIdBuilder applicationId(ApplicationId applicationId);
  }

  public interface JobIdBuilder {
    ResumeIdBuilder jobId(JobId jobId);
  }

  public interface ResumeIdBuilder {
    EventPublisherBuilder resumeId(ResumeId resumeId);
  }

  public interface EventPublisherBuilder {
    Application eventPublisher(EventPublisher eventPublisher);
  }
}
