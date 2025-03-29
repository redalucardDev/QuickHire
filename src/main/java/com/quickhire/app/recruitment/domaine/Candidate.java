package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.recruitment.domaine.application.Application;
import com.quickhire.app.recruitment.domaine.application.ApplicationId;
import com.quickhire.app.recruitment.domaine.application.PendingApplicationState;
import com.quickhire.app.recruitment.domaine.events.EventPublisher;
import com.quickhire.app.recruitment.domaine.interview.Interviews;
import com.quickhire.app.recruitment.domaine.job.Job;
import com.quickhire.app.recruitment.domaine.job.JobId;
import com.quickhire.app.recruitment.domaine.personalInformations.PersonalInformations;
import com.quickhire.app.shared.error.domain.Assert;
import java.util.ArrayList;
import java.util.List;

public class Candidate {

  private final CandidateId id;

  public static final int MAX_PROPOSALS_PER_DAY = 3;
  private static final int MAX_APPLICATIONS = 3;

  private final PersonalInformations personalInformations;
  private Resume resume;

  private final List<Application> applications = new ArrayList<>();
  private final List<JobId> declinedJobs = new ArrayList<>();
  private final List<Proposal> receivedProposals = new ArrayList<>();
  private final EventPublisher eventPublisher;

  public Candidate(CandidateBuilder candidateBuilder) {
    Assert.notNull("candidateBuilder", candidateBuilder);
    id = candidateBuilder.id;
    personalInformations = candidateBuilder.personalInformations;
    resume = candidateBuilder.resume;
    this.eventPublisher = candidateBuilder.eventPublisher;
  }

  public static CandidateBuilder builder() {
    return new CandidateBuilder();
  }

  public List<Application> appliedJobs() {
    return applications;
  }

  public List<Proposal> receivedProposals() {
    return receivedProposals;
  }

  public CandidateId id() {
    return id;
  }

  public List<Application> applyForAJob(Job job) {
    Assert.notNull("job", job);
    checkIfCanApplyToJob(job);
    applications.add(
      Application.builder()
        .applicationId(ApplicationId.newId())
        .jobId(job.jobId())
        .resumeId(resume.resumeId())
        .state(new PendingApplicationState(new Interviews(new ArrayList<>(2))))
    );
    return applications;
  }

  private void checkIfCanApplyToJob(Job job) {
    if (hasAlreadyAppliedForAJob(job)) {
      throw new IllegalStateException("Candidate already applied for this jobId");
    }

    if (hasAlreadyDeclinedAJob(job)) {
      throw new IllegalStateException("Candidate has declined this jobId");
    }

    if (getPendingApplicationsCount() >= MAX_APPLICATIONS) {
      throw new IllegalStateException("Candidate has already pending application for 3 jobs");
    }
  }

  private boolean hasAlreadyAppliedForAJob(Job job) {
    return applications.stream().map(Application::jobId).toList().contains(job.jobId());
  }

  private boolean hasAlreadyDeclinedAJob(Job job) {
    return declinedJobs.contains(job.jobId());
  }

  private long getPendingApplicationsCount() {
    return applications.stream().filter(application -> application.state() instanceof PendingApplicationState).count();
  }

  public void declineJob(Job job) {
    declinedJobs.add(job.jobId());
  }

  public Candidate resume(Resume resume) {
    this.resume = resume;
    return this;
  }

  public void receiveProposal(DeterministicDateTimeProvider deterministicDateTimeProvider, Proposal proposal) {
    Assert.notNull("deterministicDateTimeProvider", deterministicDateTimeProvider);
    Assert.notNull("proposal", proposal);
    if (
      !receivedProposals.isEmpty() &&
      isLastProposalMoreThanADay(deterministicDateTimeProvider) &&
      receivedProposals.size() >= MAX_PROPOSALS_PER_DAY
    ) {
      throw new IllegalStateException("Candidate received 3 proposals for this day which is the maximum");
    }
    receivedProposals.add(proposal);
  }

  private boolean isLastProposalMoreThanADay(DeterministicDateTimeProvider deterministicDateTimeProvider) {
    Assert.notNull("deterministicDateTimeProvider", deterministicDateTimeProvider);
    Proposal lastProposal = receivedProposals.getLast();
    return lastProposal.moreThanADay(deterministicDateTimeProvider);
  }

  public static class CandidateBuilder implements CandidateIdBuilder, PersonalInformationsBuilder, EventPublisherBuilder {

    public EventPublisher eventPublisher;
    private CandidateId id;
    private Resume resume;
    private PersonalInformations personalInformations;

    @Override
    public CandidateBuilder id(CandidateId id) {
      Assert.notNull("id", id);
      this.id = id;

      return this;
    }

    @Override
    public EventPublisherBuilder personalInformations(PersonalInformations personalInformations) {
      Assert.notNull("personalInformations", personalInformations);
      this.personalInformations = personalInformations;
      return this;
    }

    @Override
    public Candidate eventPublisher(EventPublisher eventPublisher) {
      Assert.notNull("eventPublisher", eventPublisher);
      this.eventPublisher = eventPublisher;
      return new Candidate(this);
    }
  }

  public interface CandidateIdBuilder {
    CandidateBuilder id(CandidateId id);
  }

  public interface PersonalInformationsBuilder {
    EventPublisherBuilder personalInformations(PersonalInformations personalInformations);
  }

  public interface EventPublisherBuilder {
    Candidate eventPublisher(EventPublisher eventPublisher);
  }
}
