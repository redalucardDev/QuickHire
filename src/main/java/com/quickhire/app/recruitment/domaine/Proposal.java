package com.quickhire.app.recruitment.domaine;

import com.quickhire.app.recruitment.domaine.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;

public record Proposal(CandidateId candidateId, JobId jobId, Message message, Date date) {
  public Proposal {
    Assert.notNull("candidateId", candidateId);
    Assert.notNull("jobId", jobId);
    Assert.notNull("message", message);
    Assert.notNull("date", date);
  }

  boolean moreThanADay(DeterministicDateTimeProvider deterministicDateTimeProvider) {
    Assert.notNull("deterministicDateTimeProvider", deterministicDateTimeProvider);
    return date().isMoreThanOneDayApart(deterministicDateTimeProvider.dateTime());
  }
}
