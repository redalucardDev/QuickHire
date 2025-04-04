package com.quickhire.app.recruitment.domain;

import com.quickhire.app.recruitment.domain.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;

public record Proposal(CandidateId candidateId, JobId jobId, Message message, DateTime dateTime) {
  public Proposal {
    Assert.notNull("candidateId", candidateId);
    Assert.notNull("jobId", jobId);
    Assert.notNull("message", message);
    Assert.notNull("date", dateTime);
  }

  boolean moreThanADay(DeterministicDateTimeProvider deterministicDateTimeProvider) {
    Assert.notNull("deterministicDateTimeProvider", deterministicDateTimeProvider);
    return dateTime.isMoreThanOneDayApart(deterministicDateTimeProvider.dateTime());
  }
}
