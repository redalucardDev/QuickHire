package com.quickhire.app.prospection.event;

import com.quickhire.app.message.domain.MessageId;
import com.quickhire.app.prospect.domain.ProspectId;
import com.quickhire.app.shared.error.domain.Assert;

public record MessageToProspectEmitted(ProspectId prospectId, MessageId messageId)  {

  public MessageToProspectEmitted {
    Assert.notNull("prospectId", prospectId);
    Assert.notNull("messageId", messageId);
  }

}
