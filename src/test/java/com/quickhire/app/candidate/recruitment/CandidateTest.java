package com.quickhire.app.candidate.recruitment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.quickhire.app.candidate.recruitment.job.Job;
import org.junit.jupiter.api.Test;

public class CandidateTest {

  @Test
  void shouldApplyForAJob() {
    Candidate candidate = CandidateFixture.createCandidateWithResume();

    Job job = CandidateFixture.createJob();

    candidate.applyForAJob(job);

    assertThat(candidate.appliedJobs()).extracting(Application::job).containsExactly(job);
  }

  @Test
  void shouldNotApplyForAJobTwice() {
    Candidate candidate = CandidateFixture.createCandidateWithResume();

    Job job = CandidateFixture.createJob();

    candidate.applyForAJob(job);

    assertThrows(IllegalStateException.class, () -> candidate.applyForAJob(job), "Candidate already applied for this job");
  }

  @Test
  void shouldNotApplyToMoreThan3Jobs() {
    Candidate candidate = CandidateFixture.createCandidateWithResume();

    Job job1 = CandidateFixture.createJob();
    Job job2 = CandidateFixture.createJob();
    Job job3 = CandidateFixture.createJob();
    Job job4 = CandidateFixture.createJob();

    candidate.applyForAJob(job1);
    candidate.applyForAJob(job2);
    candidate.applyForAJob(job3);
    assertThrows(IllegalStateException.class, () -> candidate.applyForAJob(job4), "Candidate already applied for 3 jobs");
  }

  @Test
  void shoudDeclineProspectionMessage() {
    Candidate candidate = CandidateFixture.createCandidate();
    Job job = CandidateFixture.createJob();
    candidate.declineJob(job);
  }

  @Test
  void shouldNotApplyForADeclinedJob() {
    Candidate candidate = CandidateFixture.createCandidate();
    Job job = CandidateFixture.createJob();
    candidate.declineJob(job);
    assertThrows(IllegalStateException.class, () -> candidate.applyForAJob(job), "Candidate has declined this job");
  }
}
