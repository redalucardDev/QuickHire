package com.quickhire.app.recruitment.domaine.events;

import com.quickhire.app.recruitment.domaine.Message;
import com.quickhire.app.recruitment.domaine.application.OfferId;
import com.quickhire.app.recruitment.domaine.job.JobId;
import com.quickhire.app.shared.error.domain.Assert;

public record OfferSendEvent(OfferId offerId, Message offerMessage, JobId jobId) implements RecruitmentEvent {
  public OfferSendEvent {
    Assert.notNull("offerId", offerId);
    Assert.notNull("offerMessage", offerMessage);
    Assert.notNull("jobId", jobId);
  }
}
