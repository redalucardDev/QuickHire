package com.quickhire.app.recruitment.domaine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.quickhire.app.recruitment.domaine.job.Job;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class CandidateTest {

  private DeterministicDateTimeProvider deterministicDateTimePovider = new DeterministicDateTimeProvider(
    LocalDateTime.of(2025, 03, 22, 0, 0)
  );
  private final Candidate candidate = CandidateFixture.createCandidateWithResume();

  @Test
  void shouldApplyForAJob() {
    Job job = CandidateFixture.createJob();

    candidate.applyForAJob(job);

    assertThat(candidate.appliedJobs()).extracting(Application::job).containsExactly(job);
  }

  @Test
  void shouldNotApplyForAJobTwice() {
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
    Job job = CandidateFixture.createJob();
    candidate.declineJob(job);
  }

  @Test
  void shouldNotApplyForADeclinedJob() {
    Job job = CandidateFixture.createJob();
    candidate.declineJob(job);
    assertThrows(IllegalStateException.class, () -> candidate.applyForAJob(job), "Candidate has declined this job");
  }

  @Test
  void shouldReceiveProposalWhenMaxProposalsPerDayNotReached() {
    Job job = CandidateFixture.createJob();

    Date Date = new Date(LocalDateTime.of(2025, 03, 21, 0, 0));

    candidate.receiveProposal(
      deterministicDateTimePovider,
      new Proposal(candidate.id(), job.jobId(), new Message("This is a message for a job proposal"), Date)
    );

    assertThat(candidate.receivedProposals()).containsExactly(
      new Proposal(
        candidate.id(),
        job.jobId(),
        new Message("This is a message for a job proposal"),
        new Date(LocalDateTime.of(2025, 03, 21, 0, 0))
      )
    );
  }

  @Test
  void shouldNotReceiveProposalWhenMaxProposalsPerDayReached() {
    Job job = CandidateFixture.createJob();
    Proposal proposal = new Proposal(
      candidate.id(),
      job.jobId(),
      new Message("This is a message for a job proposal"),
      new Date(LocalDateTime.of(2025, 03, 21, 0, 0))
    );

    for (int i = 0; i < Candidate.MAX_PROPOSALS_PER_DAY; i++) {
      candidate.receiveProposal(deterministicDateTimePovider, proposal);
    }
    assertThrows(
      IllegalStateException.class,
      () -> {
        candidate.receiveProposal(deterministicDateTimePovider, proposal);
      },
      "Candidate received 3 proposals for this day which is the maximum"
    );
  }
}
