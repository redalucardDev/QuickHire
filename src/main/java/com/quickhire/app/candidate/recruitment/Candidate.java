package com.quickhire.app.candidate.recruitment;

import com.quickhire.app.candidate.recruitment.job.Job;
import java.util.ArrayList;
import java.util.List;

public class Candidate {

  private final int MAX_APPLICATIONS = 3;
  private final CandidateId id;
  private final PersonalInformations personalInformations;
  private final List<Application> applications = new ArrayList<>();
  private final List<Job> declinedJobs = new ArrayList<>();
  private Resume resume;

  public Candidate(CandidateBuilder candidateBuilder) {
    id = candidateBuilder.id;
    personalInformations = candidateBuilder.personalInformations;
    resume = candidateBuilder.resume;
  }

  public static CandidateBuilder builder() {
    return new CandidateBuilder();
  }

  public List<Application> appliedJobs() {
    return applications;
  }

  public CandidateId id() {
    return id;
  }

  public void applyForAJob(Job job) {
    checkIfCanApplyToJob(job);

    applications.add(new Application(job, resume));
  }

  private void checkIfCanApplyToJob(Job job) {
    if (applications.stream().anyMatch(application -> application.job().equals(job))) {
      throw new IllegalStateException("Candidate already applied for this job");
    }

    if (declinedJobs.stream().anyMatch(declinedJob -> declinedJob.equals(job))) {
      throw new IllegalStateException("Candidate has declined this job");
    }

    if (applications.size() >= MAX_APPLICATIONS) {
      throw new IllegalStateException("Candidate already applied for 3 jobs");
    }
  }

  public void declineJob(Job job) {
    declinedJobs.add(job);
  }

  public Candidate resume(Resume resume) {
    this.resume = resume;
    return this;
  }

  public static class CandidateBuilder implements CandidateIdBuilder, PersonalInformationsBuilder {

    private CandidateId id;
    private Resume resume;
    private PersonalInformations personalInformations;

    @Override
    public CandidateBuilder id(CandidateId id) {
      this.id = id;

      return this;
    }

    @Override
    public Candidate personalInformations(PersonalInformations personalInformations) {
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
