package com.quickhire.app.recruitment.domain.application;

import com.quickhire.app.recruitment.domain.Message;
import com.quickhire.app.recruitment.domain.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;

public class AcceptedApplication {

  private final Offer offer;

  public AcceptedApplication(Message offerMessage, JobId jobId) {
    Assert.notNull("offer", offerMessage);
    offer = new Offer(OfferId.newId(), offerMessage, jobId);
  }

  public Offer offer() {
    return offer;
  }
}
