package com.quickhire.app.prospection.event;

import com.quickhire.app.message.domain.Message;
import com.quickhire.app.prospect.domain.Prospect;
import com.quickhire.app.shared.error.domain.Assert;

public record MessageToProspectEmitted(Prospect.ProspectId prospectId, Message.MessageId messageId) {
  public MessageToProspectEmitted {
    Assert.notNull("prospectId", prospectId);
    Assert.notNull("messageId", messageId);
  }
}
