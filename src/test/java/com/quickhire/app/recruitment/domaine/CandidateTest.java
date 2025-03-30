package com.quickhire.app.recruitment.domaine;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.quickhire.app.recruitment.domaine.application.PendingApplication;
import com.quickhire.app.recruitment.domaine.job.Job;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class CandidateTest {

  private final DeterministicDateTimeProvider deterministicDateTimeProvider = new DeterministicDateTimeProvider(
    LocalDateTime.of(2025, 3, 22, 0, 0)
  );
  private final Candidate candidate = RecruitmentFixture.createCandidateWithResume();

  @Test
  void shouldApplyForAJob() {
    Job job = RecruitmentFixture.createJob();

    candidate.applyForAJob(job);

    assertThat(candidate.appliedJobs()).extracting(PendingApplication::jobId).containsExactly(job.jobId());
  }

  @Test
  void shouldNotApplyForAJobTwice() {
    Job job = RecruitmentFixture.createJob();

    candidate.applyForAJob(job);

    assertThrows(IllegalStateException.class, () -> candidate.applyForAJob(job), "Candidate already applied for this jobId");
  }

  @Test
  void shouldBeAbleToCancelApplication() {
    Job job = RecruitmentFixture.createJob();
    candidate.applyForAJob(job);
    candidate.declineJob(job);
    assertThat(candidate.appliedJobs()).isEmpty();
  }

  @Test
  void shouldNotApplyToMoreThan3JobsForWhichApplicationStatusIsPending() {
    Candidate candidate = RecruitmentFixture.createCandidateWithResume();

    Job job1 = RecruitmentFixture.createJob();
    Job job2 = RecruitmentFixture.createJob();
    Job job3 = RecruitmentFixture.createJob();
    Job job4 = RecruitmentFixture.createJob();

    candidate.applyForAJob(job1);
    candidate.applyForAJob(job2);
    candidate.applyForAJob(job3);
    assertThatExceptionOfType(IllegalStateException.class)
      .isThrownBy(() -> candidate.applyForAJob(job4))
      .withMessage("Candidate has already pending application for 3 jobs");
  }

  @Test
  void shouldDeclineProspectionMessage() {
    Job job = RecruitmentFixture.createJob();
    candidate.declineJob(job);
  }

  @Test
  void shouldNotApplyForADeclinedJob() {
    Job job = RecruitmentFixture.createJob();
    candidate.declineJob(job);
    assertThrows(IllegalStateException.class, () -> candidate.applyForAJob(job), "Candidate has declined this jobId");
  }

  @Test
  void shouldReceiveProposalWhenMaxProposalsPerDayNotReached() {
    Job job = RecruitmentFixture.createJob();

    DateTime dateTime = new DateTime(LocalDateTime.of(2025, 3, 21, 0, 0));

    candidate.receiveProposal(
      deterministicDateTimeProvider,
      new Proposal(candidate.id(), job.jobId(), new Message("This is a message for a jobId proposal"), dateTime)
    );

    assertThat(candidate.receivedProposals()).containsExactly(
      new Proposal(
        candidate.id(),
        job.jobId(),
        new Message("This is a message for a jobId proposal"),
        new DateTime(LocalDateTime.of(2025, 3, 21, 0, 0))
      )
    );
  }

  @Test
  void shouldNotReceiveProposalWhenMaxProposalsPerDayReached() {
    Job job = RecruitmentFixture.createJob();
    Proposal proposal = new Proposal(
      candidate.id(),
      job.jobId(),
      new Message("This is a message for a jobId proposal"),
      new DateTime(LocalDateTime.of(2025, 3, 21, 0, 0))
    );

    for (int i = 0; i < Candidate.MAX_PROPOSALS_PER_DAY; i++) {
      candidate.receiveProposal(deterministicDateTimeProvider, proposal);
    }
    assertThatThrownBy(() -> candidate.receiveProposal(deterministicDateTimeProvider, proposal))
      .isInstanceOf(IllegalStateException.class)
      .hasMessage("Candidate received 3 proposals for this day which is the maximum");
  }
}
