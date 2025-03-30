package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.recruitment.domaine.application.ApplicationId;
import com.quickhire.app.recruitment.domaine.application.PendingApplication;
import com.quickhire.app.recruitment.domaine.events.EventPublisher;
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

  private final List<PendingApplication> pendingApplications = new ArrayList<>();
  private final List<JobId> declinedJobs = new ArrayList<>();
  private final List<Proposal> receivedProposals = new ArrayList<>();

  public Candidate(CandidateBuilder candidateBuilder) {
    Assert.notNull("candidateBuilder", candidateBuilder);
    id = candidateBuilder.id;
    personalInformations = candidateBuilder.personalInformations;
    resume = candidateBuilder.resume;
  }

  public static CandidateBuilder builder() {
    return new CandidateBuilder();
  }

  public List<PendingApplication> appliedJobs() {
    return pendingApplications;
  }

  public List<Proposal> receivedProposals() {
    return receivedProposals;
  }

  public CandidateId id() {
    return id;
  }

  public List<PendingApplication> applyForAJob(Job job) {
    Assert.notNull("job", job);
    checkIfCanApplyToJob(job);
    pendingApplications.add(
      PendingApplication.builder().applicationId(ApplicationId.newId()).resumeId(resume.resumeId()).jobId(job.jobId())
    );
    return pendingApplications;
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
    return pendingApplications.stream().map(PendingApplication::jobId).toList().contains(job.jobId());
  }

  private boolean hasAlreadyDeclinedAJob(Job job) {
    return declinedJobs.contains(job.jobId());
  }

  private long getPendingApplicationsCount() {
    return pendingApplications.size();
  }

  public void declineJob(Job job) {
    declinedJobs.add(job.jobId());
    pendingApplications.removeIf(application -> application.jobId().equals(job.jobId()));
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

  public static class CandidateBuilder implements CandidateIdBuilder, PersonalInformationsBuilder {

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
    public Candidate personalInformations(PersonalInformations personalInformations) {
      Assert.notNull("personalInformations", personalInformations);
      this.personalInformations = personalInformations;
      return new Candidate(this);
    }

  }

  public interface CandidateIdBuilder {
    CandidateBuilder id(CandidateId id);
  }

  public interface PersonalInformationsBuilder {
    Candidate personalInformations(PersonalInformations personalInformations);
  }

}
