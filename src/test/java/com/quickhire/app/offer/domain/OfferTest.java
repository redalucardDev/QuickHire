package com.quickhire.app.offer.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;

public class OfferTest {

  @Test
  void shouldGetOffer() {
    Offer.OfferId offerId = new Offer.OfferId(UUID.randomUUID());
    OfferStatus offerStatus = OfferStatus.WAITING;
    Offer.CandidatureId candidatureId = new Offer.CandidatureId(UUID.randomUUID());

    Offer offer = new Offer(offerId, offerStatus, candidatureId);

    assertThat(offer).isEqualTo(new Offer(offerId, offerStatus, candidatureId));
  }
}
