package com.quickhire.app.recruitment.domain.application;

import com.quickhire.app.recruitment.domain.Message;
import com.quickhire.app.shared.error.domain.Assert;

public record DeclinedApplication(Message declineMessage) {
  public DeclinedApplication {
    Assert.notNull("declineMessage", declineMessage);
  }
}
