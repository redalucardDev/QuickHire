package com.quickhire.app.recruitment.domain.application;

import com.quickhire.app.recruitment.domain.Message;
import com.quickhire.app.recruitment.domain.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;

public record Offer(OfferId offerId, Message offerMessage, JobId jobId) {
  public Offer {
    Assert.notNull("offerId", offerId);
    Assert.notNull("offerMessage", offerMessage);
    Assert.notNull("jobId", jobId);
  }
}
