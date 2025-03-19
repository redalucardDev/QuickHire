package com.quickhire.app.offer.domain;

import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record Offer(OfferId offerId, OfferStatus offerStatus, CandidatureId candidatureId) {
  public Offer {
    Assert.notNull("offerId", offerId);
    Assert.notNull("offerStatus", offerStatus);
    Assert.notNull("candidatureId", candidatureId);
  }

  public record OfferId(UUID offerId) {
    public OfferId {
      Assert.notNull("offerId", offerId);
    }
  }

  public static record CandidatureId(UUID candidatureId) {
    public CandidatureId {
      Assert.notNull("candidatureId", candidatureId);
    }
  }
}
