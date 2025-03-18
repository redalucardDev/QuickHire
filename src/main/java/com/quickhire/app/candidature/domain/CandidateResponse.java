package com.quickhire.app.candidature.domain;

import com.quickhire.app.shared.error.domain.Assert;

public record CandidateResponse(String candidateResponse) {

  public CandidateResponse {
    Assert.notNull("candidateResponse", candidateResponse);
  }
}
