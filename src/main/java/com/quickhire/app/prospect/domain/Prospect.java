package com.quickhire.app.prospect.domain;

import com.quickhire.app.shared.contactInfo.domain.ContactInfo;
import com.quickhire.app.shared.error.domain.Assert;
import java.util.UUID;

public record Prospect(ProspectId prospectId, ContactInfo contactInfo) {
  public record ProspectId(UUID prospectId) {
    public ProspectId {
      Assert.notNull("prospectId", prospectId);
    }
  }
}
